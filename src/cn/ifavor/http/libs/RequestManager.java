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

	/** 执行请求 */
	public void performRequest(Request request) {
		if (request == null) {
			throw new RuntimeException("请求对象 request 不能为 null");
		}

		mRequests.add(request);

		RequestTask task = new RequestTask(request);
		task.execute();
	}

	/** 取消带指定标记的请求 */
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

	/** 取消所有请求 */
	public void cancelAll() {
		for (int i = 0; i < mRequests.size(); i++) {
			Request request = mRequests.get(i);
			if (!request.isCancel()) {
				request.setCancel(true);
			}
		}
	}

}
