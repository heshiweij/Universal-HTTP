package cn.ifavor.http.libs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.ifavor.http.libs.Request.RequestMethod;

import android.text.TextUtils;

public class HttpUrlConnectionUtils {
	/** ���ӳ�ʱʱ��(��) */
	private static final int CONNTENT_TIME_SECONDS = 15;

	/** ��ȡ��ʱʱ��(��) */
	private static final int READ_TIME_SECONDS = 5;

	/** ��ȡ�������Ĵ�С */
	private static final int BUFFER_SIZE = 2 * 1024;

	/** HTTP ��Ӧ״̬��-�ɹ� */
	private static final int HTTP_STATUS_CODE_SUCCESS = 200;

	public static String execute(Request request) throws Exception {
		if (request == null) {
			throw new IllegalStateException("���������Ϊ��");
		}

		if (TextUtils.isEmpty(request.getUrl())) {
			throw new IllegalStateException("����URL����Ϊ��");
		}

		RequestMethod requestMethod = request.getMethod();
		switch (requestMethod) {
		case DELETE:
		case GET:
			return get(request);
		case PUT:
		case POST:
			return post(request);
		}

		return null;
	}

	/**
	 * get ����
	 * 
	 * @param sUrl
	 *            ��ַ
	 * @param headers
	 *            ͷ��Ϣ����
	 * @return
	 * @throws Exception
	 */
	public static String get(Request request) throws Exception {
		URL url = new URL(request.getUrl());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setConnectTimeout(CONNTENT_TIME_SECONDS * 1000);
		conn.setReadTimeout(READ_TIME_SECONDS * 1000);
		// һ�������� conn.setDoOutput(true) �����Զ���� post
		// conn.setDoOutput(true);

		// ���ͷ��Ϣ
		addHeaders(request.getHeaders(), conn);

		int statusCode = conn.getResponseCode();

		if (statusCode == HTTP_STATUS_CODE_SUCCESS) {
			InputStream is = conn.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}

			String res = new String(baos.toByteArray());
			return res;
		}

		return null;
	}

	/**
	 * ���ͷ��Ϣ
	 * 
	 * @param headers
	 * @param conn
	 */
	private static void addHeaders(Map<String, String> headers,
			HttpURLConnection conn) {

		if (headers == null || headers.size() <= 0) {
			return;
		}

		Set<Map.Entry<String, String>> entrySet = headers.entrySet();
		StringBuilder sb = new StringBuilder();
		for (Iterator<Map.Entry<String, String>> it = entrySet.iterator(); it
				.hasNext();) {
			Entry<String, String> next = it.next();
			String key = next.getKey();
			String value = next.getValue();

			conn.addRequestProperty(key, value);
		}
	}

	/**
	 * post ����
	 * 
	 * @param sUrl
	 *            ��ַ
	 * @param headers
	 *            ͷ��Ϣ����
	 * @return
	 * @throws Exception
	 */
	public static String post(Request request) throws Exception {
		URL url = new URL(request.getUrl());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("POST");
		conn.setConnectTimeout(CONNTENT_TIME_SECONDS * 1000);
		conn.setReadTimeout(READ_TIME_SECONDS * 1000);

		// ���ÿɶ��Ϳ�д�� post ������ص�
		conn.setDoInput(true);
		conn.setDoInput(true);

		// ���ͷ��Ϣ
		addHeaders(request.getHeaders(), conn);

		// ��ӱ�������
		addContent(conn, request);

		int statusCode = conn.getResponseCode();

		if (statusCode == HTTP_STATUS_CODE_SUCCESS) {
			InputStream is = conn.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}

			String res = new String(baos.toByteArray());
			return res;
		}

		return null;
	}

	/**
	 * ��ӱ�������
	 * 
	 * @param conn
	 * @param request
	 * @throws IOException
	 */
	private static void addContent(HttpURLConnection conn, Request request)
			throws IOException {
		if (request == null || TextUtils.isEmpty(request.getContent())) {
			return;
		}

		OutputStream os = conn.getOutputStream();
		os.write(request.getContent().getBytes());
	}
}
