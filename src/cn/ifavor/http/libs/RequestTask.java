package cn.ifavor.http.libs;

import java.net.HttpURLConnection;

import cn.ifavor.http.libs.exception.AppException;
import cn.ifavor.http.libs.listener.OnProgressUpdateListener;

import android.os.AsyncTask;

/***
 * 请求异步执行类 泛型： 第 1 个泛型：doInBackground 的参数 第 2 个泛型：onProgressUpdate 的参数 第 3
 * 个泛型：doInBackground 的返回值 和 onPostExecute 的参数
 * 
 * @author Administrator
 * 
 */
public class RequestTask extends AsyncTask<Void, Integer, Object> {

	/** 请求对象 */
	private Request mRequest;
	
	/** 当前已重试的次数 */
	private int retryCount = 0;

	public RequestTask(Request request) {
		if (request == null) {
			throw new NullPointerException("Request 不能为 null");
		}

		if (request.getCallback() == null) {
			throw new NullPointerException("Callback 不能为 null");
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

	/** 执行具体的请求 */
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
					
					// Timeout 重试
					if (retryCount < mRequest.getMaxRetryCount()){
						retryCount++;
						System.out.println("第 " + retryCount + " 次重试");
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
			// 失败
			// 说明:
			// 1. 全部的异常先用 getOnGlobalExceptionListener 过滤一遍，看看有没有要处理的
			//     然后交给mRequest.getCallback().onFail();
			// 2. handlerException 返回 true 表示消费了异常，不需要 onFail 处理，否则需要 onFail 处理
			if (mRequest.getOnGlobalExceptionListener() != null){
				if (!mRequest.getOnGlobalExceptionListener().handlerException((AppException) result)){
					mRequest.getCallback().onFail((AppException) result);
				}
			} else {
				mRequest.getCallback().onFail((AppException) result);
			}
			
		} else {
			// 成功
			// 说明：
			// 1. 只要不是 Exception 就是成功
			// 2. 成功后返回的数据可能是 String 和 T(在 JSONCallback 自定义的泛型)
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
