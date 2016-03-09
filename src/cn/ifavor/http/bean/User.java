package cn.ifavor.http.bean;

public class User {
	private String origin;

	private String url;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "User [origin=" + origin + ", url=" + url + "]";
	}
}
