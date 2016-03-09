package cn.ifavor.http.libs.exception;

public class AppException extends Exception{
	private static final long serialVersionUID = 1L;
	
	/** 异常类型枚举：超时、 服务器错误、客户端错误、主动取消请求 */
	public enum ExceptionType { TIMEOUT, SERVER, CLIENT , CANCEL}

	/** 状态码 */
	private int status;
	
	/** 异常类型 */
	private ExceptionType type;
	
	public AppException(ExceptionType type, String responseMessage){
		super(responseMessage);
		
		this.type = type;
	}
	
	public AppException(int status, String responseMessage) {
		super(responseMessage);
		this.status = status;
		
		type = ExceptionType.SERVER; 
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ExceptionType getType() {
		return type;
	}

	public void setType(ExceptionType type) {
		this.type = type;
	}

}
