package cn.ifavor.http.libs.exception;

public class AppException extends Exception{
	private static final long serialVersionUID = 1L;

	/** ×´Ì¬Âë */
	private int status;
	
	public AppException(String responseMessage){
		super(responseMessage);
	}
	
	public AppException(int status, String responseMessage) {
		super(responseMessage);
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
