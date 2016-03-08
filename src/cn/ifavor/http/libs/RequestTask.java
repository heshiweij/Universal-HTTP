package cn.ifavor.http.libs;

import android.os.AsyncTask;

/***
 * 请求异步执行类
 * 泛型：
 * 第 1 个泛型：doInBackground 的参数
 * 第 2 个泛型：onProgressUpdate 的参数
 * 第 3 个泛型：doInBackground 的返回值 和 onPostExecute 的参数
 * @author Administrator
 *
 */
public class RequestTask extends AsyncTask<Void, Integer, Object>{
	
	private Request mRequest;
	
	public RequestTask(Request request){
		if (request == null){
			throw new NullPointerException("Request 不能为 null");
		}
		
		this.mRequest = request;
	}
	
	@Override
	protected void onPreExecute() {
		if (mRequest.getCallback() != null){
			mRequest.getCallback().onPre();
		}
	}
	
	@Override
	protected Object doInBackground(Void... params) {
		try {
			return HttpUrlConnectionUtils.execute(mRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return e;
		}
	}
	
	@Override
	protected void onPostExecute(Object result) {
		if (result instanceof String){
			// 成功
			if (mRequest.getCallback() != null){
				mRequest.getCallback().onSuccess((String) result);
			}
		} else if (result instanceof Exception){
			// 失败
			mRequest.getCallback().onFail((Exception) result);
		} 
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		
	}
}
