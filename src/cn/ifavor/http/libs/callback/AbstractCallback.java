package cn.ifavor.http.libs.callback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;

import android.support.v4.view.ViewPager.OnPageChangeListener;

import cn.ifavor.http.libs.HttpUrlConnectionUtils;
import cn.ifavor.http.libs.exception.AppException;
import cn.ifavor.http.libs.listener.OnProgressUpdateListener;

public abstract class AbstractCallback<T> implements ICallback<T>{
	@Override
	public void onPre() {
		
	}
	
	@Override
	public T parse(HttpURLConnection connection) throws AppException {
		return parse(connection, null);
	}
	
	@Override
	public T parse(HttpURLConnection connection, OnProgressUpdateListener listener) throws AppException {
			try {
				int statusCode = connection.getResponseCode();
				if (statusCode == HttpUrlConnectionUtils.HTTP_STATUS_CODE_SUCCESS) {
						InputStream is = connection.getInputStream();
						int current = 0;
						int total = connection.getContentLength();
						
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[HttpUrlConnectionUtils.BUFFER_SIZE];
						int len = 0;
						while ((len = is.read(buffer)) != -1) {
							baos.write(buffer, 0, len);
							
							current += len;
							
							if (listener != null){
								listener.onProgressUpdate(current, total);
							}
						}
						
						// ¹Ø±ÕÁ÷
						is.close();
						baos.flush();
						baos.close();

						String result = new String(baos.toByteArray());
						return bindData(result);
				} else {
					throw new AppException(connection.getResponseCode(),
							connection.getResponseMessage());
				}
			} catch (InterruptedIOException ex){
				ex.printStackTrace();
				throw new AppException(AppException.ExceptionType.TIMEOUT, ex.getMessage());
			} 
			catch (Exception e) {
				e.printStackTrace();
				throw new AppException(AppException.ExceptionType.SERVER, e.getMessage());
			}
	}

	public abstract T bindData(String s) throws Exception;
	
	@Override
	public void onProgress(int current, int total) {
		
	}
}
