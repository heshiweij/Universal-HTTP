package cn.ifavor.http.libs.callback;

import java.net.HttpURLConnection;

public interface ICallback<T> {
	void onPre();
	
	void onSuccess(T result);
	
	void onFail(Exception ex);

	T parse(HttpURLConnection connection) throws Exception;
}
