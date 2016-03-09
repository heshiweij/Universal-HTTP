package cn.ifavor.http.libs;

import java.net.HttpURLConnection;

import android.os.AsyncTask;

/***
 * �����첽ִ���� ���ͣ� �� 1 �����ͣ�doInBackground �Ĳ��� �� 2 �����ͣ�onProgressUpdate �Ĳ��� �� 3
 * �����ͣ�doInBackground �ķ���ֵ �� onPostExecute �Ĳ���
 * 
 * @author Administrator
 * 
 */
public class RequestTask extends AsyncTask<Void, Integer, Object> {

	private Request mRequest;

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
			// ʧ��
			mRequest.getCallback().onFail((Exception) result);
		} else {
			// �ɹ�
			// ˵����
			// 1. ֻҪ���� Exception ���ǳɹ����ɹ����ص�����
			// 2. �ɹ��󷵻ص����ݿ����� String �� T(�� JSONCallback �Զ���ķ���)
			mRequest.getCallback().onSuccess(result);
		}
	}

	@Override
	protected void onProgressUpdate(Integer... values) {

	}
}
