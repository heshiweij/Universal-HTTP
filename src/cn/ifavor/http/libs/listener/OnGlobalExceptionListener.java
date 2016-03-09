package cn.ifavor.http.libs.listener;

import cn.ifavor.http.libs.exception.AppException;

/**
 * È«¾ÖÒì³£¼àÌýÆ÷
 * @author Administrator
 *
 */
public interface OnGlobalExceptionListener {
	boolean handlerException(AppException ex);
}
