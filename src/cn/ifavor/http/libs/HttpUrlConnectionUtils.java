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
	public static final int CONNTENT_TIME_SECONDS = 15;

	/** ��ȡ��ʱʱ��(��) */
	public static final int READ_TIME_SECONDS = 5;

	/** ��ȡ�������Ĵ�С */
	public static final int BUFFER_SIZE = 2 * 1024;

	/** HTTP ��Ӧ״̬��-�ɹ� */
	public static final int HTTP_STATUS_CODE_SUCCESS = 200;

	public static HttpURLConnection execute(Request request) throws Exception {
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
	public static HttpURLConnection get(Request request) throws Exception {
		URL url = new URL(request.getUrl());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setConnectTimeout(CONNTENT_TIME_SECONDS * 1000);
		conn.setReadTimeout(READ_TIME_SECONDS * 1000);
		// һ�������� conn.setDoOutput(true) �����Զ���� post
		// conn.setDoOutput(true);

		// ���ͷ��Ϣ
		addHeaders(request.getHeaders(), conn);

		return conn;

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
	public static HttpURLConnection post(Request request) throws Exception {
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

		return conn;
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
