package cn.ifavor.http.libs.callback;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;

import cn.ifavor.http.libs.HttpUrlConnectionUtils;
import cn.ifavor.http.libs.exception.AppException;
import cn.ifavor.http.libs.exception.AppException.ExceptionType;
import cn.ifavor.http.libs.listener.OnProgressUpdateListener;

public abstract class AbstractCallback<T> implements ICallback<T>{
	private boolean cancel;
	
	protected boolean isCancel(){
		return cancel;
	}
	
	@Override
	public void onPre() {
		
	}
	
	@Override
	public T parse(HttpURLConnection connection) throws AppException {
		return parse(connection, null);
	}
	
	@Override
	public T parse(HttpURLConnection connection, OnProgressUpdateListener listener) throws AppException {
		// ����Ƿ�ȡ��
		checkIfCancel();
		
			try {
				int statusCode = connection.getResponseCode();
				if (statusCode == HttpUrlConnectionUtils.HTTP_STATUS_CODE_SUCCESS) {
						InputStream is = connection.getInputStream();
						int current = 0;
						int total = connection.getContentLength();
						
						// ����Ƿ�ȡ��
						checkIfCancel();
						
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[HttpUrlConnectionUtils.BUFFER_SIZE];
						int len = 0;
						while ((len = is.read(buffer)) != -1) {
							baos.write(buffer, 0, len);
							
							// ����Ƿ�ȡ��
							checkIfCancel();
							
							current += len;
							
							if (listener != null){
								listener.onProgressUpdate(current, total);
							}
						}
						
						// �ر���
						is.close();
						baos.flush();
						baos.close();

						// ����Ƿ�ȡ��
						checkIfCancel();
						
						String result = new String(baos.toByteArray());
						return bindData(result);
				} else {
					throw new AppException(connection.getResponseCode(),
							connection.getResponseMessage());
				}
			} catch (InterruptedIOException ex){
				ex.printStackTrace();
				throw new AppException(AppException.ExceptionType.TIMEOUT,"SocketTimeout Socket ��ȡ��ʱ");
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
	
	/** ����Ƿ�ȡ�� */
	protected void checkIfCancel() throws AppException{
		if (isCancel()){
			throw new AppException(ExceptionType.CANCEL, "����������ʱ�������û�ȡ��");
		}
	}
	
	@Override
	public void cancel() {
		cancel = true;
	}
}
