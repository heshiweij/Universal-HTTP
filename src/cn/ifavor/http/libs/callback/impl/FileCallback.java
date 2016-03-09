package cn.ifavor.http.libs.callback.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

import cn.ifavor.http.libs.HttpUrlConnectionUtils;
import cn.ifavor.http.libs.callback.AbstractCallback;
import cn.ifavor.http.libs.exception.AppException;
import cn.ifavor.http.libs.listener.OnProgressUpdateListener;

public abstract class FileCallback extends AbstractCallback<File> {

	private File mFile;

	public FileCallback(File file) {
		if (file == null) {
			throw new IllegalStateException("文件不能为 null");
		}

		this.mFile = file;
	}

	@Override
	public File parse(HttpURLConnection connection,
			OnProgressUpdateListener listener) throws AppException {
		
		// 检查用户是否主动取消
		checkIfCancel();
		
		try {
			int statusCode = connection.getResponseCode();
			if (statusCode == HttpUrlConnectionUtils.HTTP_STATUS_CODE_SUCCESS) {
				InputStream is = connection.getInputStream();
				int current = 0;
				int total = connection.getContentLength();

				// 检查用户是否主动取消
				checkIfCancel();
				
				FileOutputStream fos = new FileOutputStream(mFile);
				byte[] buffer = new byte[HttpUrlConnectionUtils.BUFFER_SIZE];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);

					// 检查用户是否主动取消
					checkIfCancel();
					
					current += len;

					if (listener != null) {
						listener.onProgressUpdate(current, total);
					}
				}

				// 关闭流
				is.close();
				fos.flush();
				fos.close();

				// 检查用户是否主动取消
				checkIfCancel();
				
				return bindData(mFile.getAbsolutePath());
			} else {
				throw new AppException(connection.getResponseCode(),
						connection.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(AppException.ExceptionType.SERVER, e.getMessage());
		}
	}

	@Override
	public File bindData(String s) throws Exception {
		// 检查用户是否主动取消
		checkIfCancel();
		
		return mFile;
	}

}
