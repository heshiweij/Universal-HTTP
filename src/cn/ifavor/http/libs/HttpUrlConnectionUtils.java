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
	/** 连接超时时间(秒) */
	private static final int CONNTENT_TIME_SECONDS = 15;

	/** 读取超时时间(秒) */
	private static final int READ_TIME_SECONDS = 5;

	/** 读取缓存区的大小 */
	private static final int BUFFER_SIZE = 2 * 1024;

	/** HTTP 响应状态码-成功 */
	private static final int HTTP_STATUS_CODE_SUCCESS = 200;

	public static String execute(Request request) throws Exception {
		if (request == null) {
			throw new IllegalStateException("请求对象不能为空");
		}

		if (TextUtils.isEmpty(request.getUrl())) {
			throw new IllegalStateException("请求URL不能为空");
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
	 * get 请求
	 * 
	 * @param sUrl
	 *            地址
	 * @param headers
	 *            头信息集合
	 * @return
	 * @throws Exception
	 */
	public static String get(Request request) throws Exception {
		URL url = new URL(request.getUrl());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setConnectTimeout(CONNTENT_TIME_SECONDS * 1000);
		conn.setReadTimeout(READ_TIME_SECONDS * 1000);
		// 一旦设置了 conn.setDoOutput(true) 请求将自动变成 post
		// conn.setDoOutput(true);

		// 添加头信息
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
	 * 添加头信息
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
	 * post 请求
	 * 
	 * @param sUrl
	 *            地址
	 * @param headers
	 *            头信息集合
	 * @return
	 * @throws Exception
	 */
	public static String post(Request request) throws Exception {
		URL url = new URL(request.getUrl());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("POST");
		conn.setConnectTimeout(CONNTENT_TIME_SECONDS * 1000);
		conn.setReadTimeout(READ_TIME_SECONDS * 1000);

		// 设置可读和可写是 post 请求的特点
		conn.setDoInput(true);
		conn.setDoInput(true);

		// 添加头信息
		addHeaders(request.getHeaders(), conn);

		// 添加报文内容
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
	 * 添加报文内容
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
