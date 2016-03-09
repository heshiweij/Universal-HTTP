package cn.ifavor.http.libs.listener;

/**
 * 进度更新监听器
 * @author Administrator
 *
 */
public interface OnProgressUpdateListener {
	void onProgressUpdate(int current, int total);
}
