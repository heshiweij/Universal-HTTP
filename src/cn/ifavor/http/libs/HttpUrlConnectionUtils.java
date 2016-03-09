package cn.ifavor.http.libs;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.client.utils.URIUtils;

import android.text.TextUtils;
import cn.ifavor.http.libs.Request.RequestMethod;
import cn.ifavor.http.libs.exception.AppException;
import cn.ifavor.http.libs.tools.URLUtil;

public class HttpUrlConnectionUtils {
	/** ���ӳ�ʱʱ��(��) */
	public static final int CONNTENT_TIME_SECONDS = 15;

	/** ��ȡ��ʱʱ��(��) */
	public static final int READ_TIME_SECONDS = 5;

	/** ��ȡ�������Ĵ�С */
	public static final int BUFFER_SIZE = 2 * 1024;

	/** HTTP ��Ӧ״̬��-�ɹ� */
	public static final int HTTP_STATUS_CODE_SUCCESS = 200;

	public static HttpURLConnection execute(Request request) throws AppException {
		if (request == null) {
			throw new AppException(AppException.ExceptionType.SERVER, "���������Ϊ��");
		}

		if (TextUtils.isEmpty(request.getUrl())) {
			throw new AppException(AppException.ExceptionType.CLIENT, "����URL����Ϊ��");
		}
		
		if (!URLUtil.isNetworkUrl(request.getUrl())){
			throw new AppException(AppException.ExceptionType.CLIENT, request.getUrl() + "���ǺϷ��� URL");
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
	public static HttpURLConnection get(Request request) throws AppException {
		try {
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
		} catch (InterruptedIOException ex){
			throw new AppException(AppException.ExceptionType.TIMEOUT, ex.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(AppException.ExceptionType.CLIENT, e.getMessage());
		}
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
	public static HttpURLConnection post(Request request) throws AppException {
		try {
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
		}  catch (InterruptedIOException ex){
			throw new AppException(AppException.ExceptionType.TIMEOUT, ex.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(AppException.ExceptionType.CLIENT, e.getMessage());
		}
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
