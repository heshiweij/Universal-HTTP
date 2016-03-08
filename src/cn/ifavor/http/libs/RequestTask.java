package cn.ifavor.http.libs;

import android.os.AsyncTask;

/***
 * �����첽ִ����
 * ���ͣ�
 * �� 1 �����ͣ�doInBackground �Ĳ���
 * �� 2 �����ͣ�onProgressUpdate �Ĳ���
 * �� 3 �����ͣ�doInBackground �ķ���ֵ �� onPostExecute �Ĳ���
 * @author Administrator
 *
 */
public class RequestTask extends AsyncTask<Void, Integer, Object>{
	
	private Request mRequest;
	
	public RequestTask(Request request){
		if (request == null){
			throw new NullPointerException("Request ����Ϊ null");
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
			// �ɹ�
			if (mRequest.getCallback() != null){
				mRequest.getCallback().onSuccess((String) result);
			}
		} else if (result instanceof Exception){
			// ʧ��
			mRequest.getCallback().onFail((Exception) result);
		} 
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		
	}
}
