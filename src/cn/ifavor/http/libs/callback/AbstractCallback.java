package cn.ifavor.http.libs.callback;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

import cn.ifavor.http.libs.HttpUrlConnectionUtils;

public abstract class AbstractCallback<T> implements ICallback<T>{
	@Override
	public void onPre() {
		
	}
	
	@Override
	public T parse(HttpURLConnection connection) throws Exception {
		int statusCode = connection.getResponseCode();
		if (statusCode == HttpUrlConnectionUtils.HTTP_STATUS_CODE_SUCCESS) {
				InputStream is = connection.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[HttpUrlConnectionUtils.BUFFER_SIZE];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				
				// ¹Ø±ÕÁ÷
				is.close();
				baos.flush();
				baos.close();

				String result = new String(baos.toByteArray());
				return bindData(result);
		}
		
		return null;
	}

	public abstract T bindData(String s) throws Exception;
}
