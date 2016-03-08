package cn.ifavor.http.libs;

import java.util.Map;

public class Request {
	/** 请求方法枚举 */
	public enum RequestMethod{GET,POST,PUT,DELETE};
	
	/** 请求地址 */
	private String url;
	
	/** 请求头信息 */
	private Map<String, String> headers;
	
	/** 请求报文体 */
	private String content;
	
	/** 请求方法 */
	private RequestMethod method;

	public Request(String url, RequestMethod method){
		this.url =url;
		this.method = method;
	}
	
	public Request(String url){
		this(url, RequestMethod.GET);
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
}
