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
			throw new IllegalStateException("�ļ�����Ϊ null");
		}

		this.mFile = file;
	}

	@Override
	public File parse(HttpURLConnection connection,
			OnProgressUpdateListener listener) throws AppException {
		
		// ����û��Ƿ�����ȡ��
		checkIfCancel();
		
		try {
			int statusCode = connection.getResponseCode();
			if (statusCode == HttpUrlConnectionUtils.HTTP_STATUS_CODE_SUCCESS) {
				InputStream is = connection.getInputStream();
				int current = 0;
				int total = connection.getContentLength();

				// ����û��Ƿ�����ȡ��
				checkIfCancel();
				
				FileOutputStream fos = new FileOutputStream(mFile);
				byte[] buffer = new byte[HttpUrlConnectionUtils.BUFFER_SIZE];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);

					// ����û��Ƿ�����ȡ��
					checkIfCancel();
					
					current += len;

					if (listener != null) {
						listener.onProgressUpdate(current, total);
					}
				}

				// �ر���
				is.close();
				fos.flush();
				fos.close();

				// ����û��Ƿ�����ȡ��
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
		// ����û��Ƿ�����ȡ��
		checkIfCancel();
		
		return mFile;
	}

}
