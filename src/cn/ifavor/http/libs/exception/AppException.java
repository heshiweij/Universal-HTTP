package cn.ifavor.http.libs.exception;

public class AppException extends Exception{
	private static final long serialVersionUID = 1L;
	
	/** �쳣����ö�٣���ʱ�� ���������󡢿ͻ��˴�������ȡ������ */
	public enum ExceptionType { TIMEOUT, SERVER, CLIENT , CANCEL}

	/** ״̬�� */
	private int status;
	
	/** �쳣���� */
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
