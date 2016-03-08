package cn.ifavor.http.libs.callback;

public interface ICallback {
	void onPre();
	
	void onSuccess(String result);
	
	void onFail(Exception ex);
}
