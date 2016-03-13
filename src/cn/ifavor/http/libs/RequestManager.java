package cn.ifavor.http.libs;

import java.util.ArrayList;
import java.util.List;

public class RequestManager {
	private static RequestManager mInstance;

	private List<Request> mRequests = new ArrayList<Request>();

	private RequestManager(){
		
	}
	
	public static RequestManager getInstance() {
		synchronized (RequestManager.class) {
			if (mInstance == null) {
				synchronized (RequestManager.class) {
					if (mInstance == null) {
						mInstance = new RequestManager();
					}
				}
			}
		}

		return mInstance;
	}

	/** ִ������ */
	public void performRequest(Request request) {
		if (request == null) {
			throw new RuntimeException("������� request ����Ϊ null");
		}

		mRequests.add(request);

		RequestTask task = new RequestTask(request);
		task.execute();
	}

	/** ȡ����ָ����ǵ����� */
	public void cancelRequest(String tag) {
		if (tag == null || tag.length() <= 0) {
			return;
		}

		for (int i = 0; i < mRequests.size(); i++) {
			Request request = mRequests.get(i);
			if (tag.equals(request.getTag()) && !request.isCancel()) {
				request.setCancel(true);
			}
		}
	}

	/** ȡ���������� */
	public void cancelAll() {
		for (int i = 0; i < mRequests.size(); i++) {
			Request request = mRequests.get(i);
			if (!request.isCancel()) {
				request.setCancel(true);
			}
		}
	}

}
