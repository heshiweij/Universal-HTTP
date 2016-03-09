package cn.ifavor.http.libs.callback;

import java.net.HttpURLConnection;
import cn.ifavor.http.libs.listener.OnProgressUpdateListener;

public interface ICallback<T> {
	void onPre();
	
	void onSuccess(T result);
	
	void onFail(Exception ex);

	T parse(HttpURLConnection connection) throws Exception;
	
	T parse(HttpURLConnection connection, OnProgressUpdateListener listener) throws Exception;
	
	void onProgress(int current, int total);
}
