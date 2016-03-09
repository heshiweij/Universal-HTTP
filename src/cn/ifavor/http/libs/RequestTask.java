package cn.ifavor.http.libs;

import java.net.HttpURLConnection;

import android.os.AsyncTask;

/***
 * 请求异步执行类 泛型： 第 1 个泛型：doInBackground 的参数 第 2 个泛型：onProgressUpdate 的参数 第 3
 * 个泛型：doInBackground 的返回值 和 onPostExecute 的参数
 * 
 * @author Administrator
 * 
 */
public class RequestTask extends AsyncTask<Void, Integer, Object> {

	private Request mRequest;

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
		try {
			HttpURLConnection connection = HttpUrlConnectionUtils
					.execute(mRequest);
			return mRequest.getCallback().parse(connection);

		} catch (Exception e) {
			e.printStackTrace();
			return e;
		}
	}

	@Override
	protected void onPostExecute(Object result) {
		if (result instanceof Exception) {
			// 失败
			mRequest.getCallback().onFail((Exception) result);
		} else {
			// 成功
			// 说明：
			// 1. 只要不是 Exception 就是成功，成功返回的数据
			// 2. 成功后返回的数据可能是 String 和 T(在 JSONCallback 自定义的泛型)
			mRequest.getCallback().onSuccess(result);
		}
	}

	@Override
	protected void onProgressUpdate(Integer... values) {

	}
}
