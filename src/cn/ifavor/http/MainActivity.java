package cn.ifavor.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.JsonObject;

import cn.ifavor.http.bean.User;
import cn.ifavor.http.libs.HttpUrlConnectionUtils;
import cn.ifavor.http.libs.Request;
import cn.ifavor.http.libs.RequestTask;
import cn.ifavor.http.libs.Request.RequestMethod;
import cn.ifavor.http.libs.callback.ICallback;
import cn.ifavor.http.libs.callback.JSONCallback;
import cn.ifavor.http.libs.callback.JSONObjectCallback;
import cn.ifavor.http.libs.callback.StringCallback;

import android.app.Activity;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button mBtnRequest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mBtnRequest = (Button) findViewById(R.id.bt_request);
		mBtnRequest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
//						try {
////							testGet();
////							testPost();
////							testExecute();
//							onSubThreadExecute();
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
						
						try {
//							onSubThreadExecute();
//							onSubThreadJsonCallback();
//							onSubThreadStringCallback();
							onSubThreadJSONArrayCallback();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}).start();
			}
		});
	}

	/** 测试 get */
/*	private void testGet() throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("user-agent", "heshiwei");
		headers.put("age", "0");
		
		Request request = new Request();
		request.setUrl("http://httpbin.org/get");
		request.setMethod(RequestMethod.GET);
		request.setHeaders(headers);
		
		String res = HttpUrlConnectionUtils.get(request);
		System.out.println(res);
	}*/

	/** 测试 post  */
/*	private void testPost() throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("user-agent", "heshiwei");
		headers.put("age", "0");
		headers.put("Content-Type", "application/json");
		String content = "{\"name\":\"hsw\"}";
		
//		Request request = new Request("http://httpbin.org/post", RequestMethod.POST);
		Request request = new Request();
		request.setUrl("http://httpbin.org/post");
		request.setMethod(RequestMethod.POST);
		request.setHeaders(headers);
		request.setContent(content);
		
		String res = HttpUrlConnectionUtils.post(request);
		System.out.println(res);
	}*/
	
	/** 测试 execute  */
	/*private void testExecute() throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("user-agent", "heshiwei");
		headers.put("age", "0");
		headers.put("Content-Type", "application/json");
		String content = "{\"name\":\"hsw\"}";
		
//		Request request = new Request("http://httpbin.org/post", RequestMethod.POST);
		Request request = new Request();
		request.setUrl("http://httpbin.org/post");
		request.setMethod(RequestMethod.POST);
		request.setHeaders(headers);
		request.setContent(content);
		
//		String res = HttpUrlConnectionUtils.post(request);
		String res = HttpUrlConnectionUtils.execute(request);
		System.out.println(res);
	}*/
	
	/** 测试在子线程运行 */
	/*private void onSubThreadExecute() throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("user-agent", "heshiwei");
		headers.put("age", "0");
		headers.put("Content-Type", "application/json");
		String content = "{\"name\":\"hsw\"}";
		
		Request request = new Request();
		request.setUrl("http://httpbin.org/post");
		request.setMethod(RequestMethod.POST);
		request.setHeaders(headers);
		request.setContent(content);
		
		// 设置 callback
		request.setCallback(new ICallback<String>() {
			
			@Override
			public void onPre() {
				System.out.println("onPre");
			}
			
			@Override
			public void onSuccess(String result) {
				System.out.println(result);
			}
			
			@Override
			public void onFail(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});
		
		// 执行请求
		RequestTask task = new RequestTask(request);
		task.execute();
	}*/
	
	/** 测试 JsonCallback */
	private void onSubThreadJsonCallback() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("user-agent", "heshiwei");
		headers.put("age", "0");
		headers.put("Content-Type", "application/json");
		String content = "{\"name\":\"hsw\"}";
		
		Request request = new Request();
		request.setUrl("http://httpbin.org/post");
		request.setMethod(RequestMethod.POST);
		request.setHeaders(headers);
		request.setContent(content);
		
		// 设置 callback
		request.setCallback(new JSONCallback<User>(){
			
			@Override
			public void onSuccess(User result) {
				System.out.println(result);
			}
			
			@Override
			public void onFail(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
		});
		
		// 执行请求
		RequestTask task = new RequestTask(request);
		task.execute();
	}
	
	/** 测试 StringCallback */
	private void onSubThreadStringCallback() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("user-agent", "heshiwei");
		headers.put("age", "0");
		headers.put("Content-Type", "application/json");
		String content = "{\"name\":\"hsw\"}";
		
		Request request = new Request();
		request.setUrl("http://httpbin.org/post");
		request.setMethod(RequestMethod.POST);
		request.setHeaders(headers);
		request.setContent(content);
		
		// 设置 callback
		request.setCallback(new StringCallback() {
			
			@Override
			public void onSuccess(String result) {
				System.out.println(result);
			}
			
			@Override
			public void onFail(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});
		
		// 执行请求
		RequestTask task = new RequestTask(request);
		task.execute();
	}
	
	/** 测试 StringCallback */
	private void onSubThreadJSONArrayCallback() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("user-agent", "heshiwei");
		headers.put("age", "0");
		headers.put("Content-Type", "application/json");
		String content = "{\"name\":\"hsw\"}";
		
		Request request = new Request();
		request.setUrl("http://httpbin.org/post");
		request.setMethod(RequestMethod.POST);
		request.setHeaders(headers);
		request.setContent(content);
		
		// 设置 callback
		request.setCallback(new JSONObjectCallback() {
			
			@Override
			public void onSuccess(JSONObject result) {
				String origin = result.optString("origin");
				String url = result.optString("url");
				
				System.out.println("origin:"+origin);
				System.out.println("url:"+url);
			}
			
			@Override
			public void onFail(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});
		
		// 执行请求
		RequestTask task = new RequestTask(request);
		task.execute();
	}
}
