package cn.ifavor.http.libs;

import java.util.Map;

import cn.ifavor.http.libs.callback.ICallback;

public class Request {
	/** 请求方法枚举 */
	public enum RequestMethod {
		GET, POST, PUT, DELETE
	};

	/** 请求地址 */
	private String url;

	/** 请求头信息 */
	private Map<String, String> headers;

	/** 请求报文体 */
	private String content;

	/** 请求方法 */
	private RequestMethod method;

	/** 请求结果回调 */
	private ICallback callback;

	private boolean isEnableProgressUpdate;

	public Request() {
		url = null;
		method = RequestMethod.GET;
	}

	public Request(String url) {
		this(url, RequestMethod.GET);
	}

	public Request(String url, RequestMethod method) {
		this(url, method, null);
	}

	public Request(String url, RequestMethod method, ICallback callback) {
		this(url, method, callback, false);
	}

	public Request(String url, RequestMethod method, ICallback callback,
			boolean isEnableProgressUpdate) {
		this.url = url;
		this.method = method;
		this.callback = callback;
		this.isEnableProgressUpdate = isEnableProgressUpdate;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public RequestMethod getMethod() {
		return method;
	}

	public void setMethod(RequestMethod method) {
		this.method = method;
	}

	public ICallback getCallback() {
		return callback;
	}

	public void setCallback(ICallback callback) {
		this.callback = callback;
	}

	public boolean isEnableProgressUpdate() {
		return isEnableProgressUpdate;
	}

	public void setEnableProgressUpdate(boolean isEnableProgressUpdate) {
		this.isEnableProgressUpdate = isEnableProgressUpdate;
	}

}
