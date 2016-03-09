package cn.ifavor.http.libs.callback;

import java.net.HttpURLConnection;

import cn.ifavor.http.libs.exception.AppException;
import cn.ifavor.http.libs.listener.OnProgressUpdateListener;

public interface ICallback<T> {
	void onPre();
	
	void onSuccess(T result);
	
	void onFail(AppException ex);

	T parse(HttpURLConnection connection) throws AppException;
	
	T parse(HttpURLConnection connection, OnProgressUpdateListener listener) throws AppException;
	
	void onProgress(int current, int total);
}
