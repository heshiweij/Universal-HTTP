package cn.ifavor.http.libs.listener;

import cn.ifavor.http.libs.exception.AppException;

/**
 * ȫ���쳣������
 * @author Administrator
 *
 */
public interface OnGlobalExceptionListener {
	boolean handlerException(AppException ex);
}
