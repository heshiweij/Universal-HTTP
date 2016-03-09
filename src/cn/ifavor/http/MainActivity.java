package cn.ifavor.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
import cn.ifavor.http.libs.callback.impl.FileCallback;
import cn.ifavor.http.libs.callback.impl.JSONCallback;
import cn.ifavor.http.libs.callback.impl.JSONObjectCallback;
import cn.ifavor.http.libs.callback.impl.StringCallback;
import cn.ifavor.http.libs.exception.AppException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.sax.TextElementListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button mBtnRequest;
	private TextView mTvProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTvProgress = (TextView) findViewById(R.id.tv_progress);
		
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
//							onSubThreadJSONArrayCallback();
//							onSubThreadProgressUpdate();
//							onSubThreadFillCallback();
							onSubThreadAppException();
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
	/*private void onSubThreadJsonCallback() {
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
	*/
	
	/** 测试 StringCallback */
	/*
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
	*/
	
	/** 测试 StringCallback */
	/*
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
	*/
	
	/** 测试 ProgressUpdate 进度更新 */
	/*
	private void onSubThreadProgressUpdate() {
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
		request.setEnableProgressUpdate(true);
		
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
			
			@Override
			public void onProgress(int current, int total) {
				System.out.println("进度更新执行...");
				System.out.println(current + " : " + total);
			}
		});
		
		// 执行请求
		RequestTask task = new RequestTask(request);
		task.execute();
	}
	*/
	
	/** 测试 FileCallback 和 文件下载进度更新 */
	/*
	private void onSubThreadFillCallback() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("user-agent", "heshiwei");
		headers.put("age", "0");
		headers.put("Content-Type", "application/json");
		String content = "{\"name\":\"hsw\"}";
		
		Request request = new Request();
		request.setUrl("http://dlsw.baidu.com/sw-search-sp/soft/bf/35013/Baidu_Setup_2348_2.3.0.1694_10000010.1456900451.exe");
		request.setMethod(RequestMethod.GET);
		request.setHeaders(headers);
		request.setContent(content);
		request.setEnableProgressUpdate(true);
		
		File file = new File(Environment.getExternalStorageDirectory() , "bizhi.jpg");
		
		// 设置 callback
		request.setCallback(new FileCallback(file) {
			
			@Override
			public void onSuccess(File result) {
				System.out.println(result.getAbsolutePath());
			}
			
			@Override
			public void onFail(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
			@Override
			public void onProgress(int current, int total) {
				System.out.println("进度更新执行...");
				System.out.println(current + " : " + total);
				mTvProgress.setText((current / (float)total) * 100 + "%");
			}
		});
		
		// 执行请求
		RequestTask task = new RequestTask(request);
		task.execute();
	}
	*/
	
	private void onSubThreadAppException(){
		Request request = new Request();
		request.setUrl("sshttp://httpbin.org/get");
		request.setMethod(RequestMethod.POST);
		request.setEnableProgressUpdate(true);
		
		// 设置 callback
		request.setCallback(new StringCallback() {
			
			@Override
			public void onSuccess(String result) {
				System.out.println("成功回调");
			}
			
			@Override
			public void onFail(AppException ex) {
				System.out.println(ex.getMessage());
			}
		});
		
		// 执行请求
		RequestTask task = new RequestTask(request);
		task.execute();
	}
}
