package cn.ifavor.http.libs;

import java.net.HttpURLConnection;

import cn.ifavor.http.libs.exception.AppException;
import cn.ifavor.http.libs.listener.OnProgressUpdateListener;

import android.os.AsyncTask;

/***
 * �����첽ִ���� ���ͣ� �� 1 �����ͣ�doInBackground �Ĳ��� �� 2 �����ͣ�onProgressUpdate �Ĳ��� �� 3
 * �����ͣ�doInBackground �ķ���ֵ �� onPostExecute �Ĳ���
 * 
 * @author Administrator
 * 
 */
public class RequestTask extends AsyncTask<Void, Integer, Object> {

	/** ������� */
	private Request mRequest;
	
	/** ��ǰ�����ԵĴ��� */
	private int retryCount = 0;

	public RequestTask(Request request) {
		if (request == null) {
			throw new NullPointerException("Request ����Ϊ null");
		}

		if (request.getCallback() == null) {
			throw new NullPointerException("Callback ����Ϊ null");
		}

		this.mRequest = request;
	}

	@Override
	protected void onPreExecute() {
		if (mRequest.getCallback() != null) {
			mRequest.getCallback().onPre();
		}
	}

	@Override
	protected Object doInBackground(Void... params) {
		return request();
	}

	/** ִ�о�������� */
	private Object request(){
		try {
			HttpURLConnection connection = HttpUrlConnectionUtils
					.execute(mRequest);
		
			if (mRequest.isEnableProgressUpdate()){
				return mRequest.getCallback().parse(connection, new OnProgressUpdateListener() {
					
					@Override
					public void onProgressUpdate(int current, int total) {
						publishProgress(current, total);
					}
				});
			} else {
				return mRequest.getCallback().parse(connection);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof AppException ){
				AppException appException = (AppException) e;
				if (appException.getType() == AppException.ExceptionType.TIMEOUT){
					
					// Timeout ����
					if (retryCount < mRequest.getMaxRetryCount()){
						retryCount++;
						System.out.println("�� " + retryCount + " ������");
						return request();
					}
				}
			}
			
			return new AppException(AppException.ExceptionType.SERVER, e.getMessage());
		} 
	}

	@Override
	protected void onPostExecute(Object result) {
		if (result instanceof Exception) {
			// ʧ��
			// ˵��:
			// 1. ȫ�����쳣���� getOnGlobalExceptionListener ����һ�飬������û��Ҫ�����
			//     Ȼ�󽻸�mRequest.getCallback().onFail();
			// 2. handlerException ���� true ��ʾ�������쳣������Ҫ onFail ����������Ҫ onFail ����
			if (mRequest.getOnGlobalExceptionListener() != null){
				if (!mRequest.getOnGlobalExceptionListener().handlerException((AppException) result)){
					mRequest.getCallback().onFail((AppException) result);
				}
			}
			
		} else {
			// �ɹ�
			// ˵����
			// 1. ֻҪ���� Exception ���ǳɹ�
			// 2. �ɹ��󷵻ص����ݿ����� String �� T(�� JSONCallback �Զ���ķ���)
			mRequest.getCallback().onSuccess(result);
		}
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		int current = values[0];
		int total = values[1];
		
		mRequest.getCallback().onProgress(current, total);
	}
}
