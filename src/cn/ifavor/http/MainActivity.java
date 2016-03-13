package cn.ifavor.http;

import java.io.File;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cn.ifavor.http.base.BaseActivity;
import cn.ifavor.http.libs.Request;
import cn.ifavor.http.libs.Request.RequestMethod;
import cn.ifavor.http.libs.RequestManager;
import cn.ifavor.http.libs.RequestTask;
import cn.ifavor.http.libs.callback.impl.FileCallback;
import cn.ifavor.http.libs.callback.impl.StringCallback;
import cn.ifavor.http.libs.exception.AppException;

public class MainActivity extends BaseActivity implements OnClickListener {

	private Button mBtnRequest;
	private TextView mTvProgress;
	private Button mBtnCancel;
	private Request request;
	private RequestManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTvProgress = (TextView) findViewById(R.id.tv_progress);
		
		mBtnCancel = (Button) findViewById(R.id.bt_cancel);
		mBtnCancel.setOnClickListener(this);
		
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
//							onSubThreadAppException();
//							onSubThreadonGlobalExceptionListener();
//							onSubThreadonRetryCount();
//							onSubThreadCancel();
//							onSubThreadCancelLoadString();
//							onSubThreadCancelRetry();
							onSubThreadTextRequestManager();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}).start();
			}
		});
	}

	/** ���� get */
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

	/** ���� post  */
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
	
	/** ���� execute  */
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
	
	/** ���������߳����� */
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
		
		// ���� callback
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
		
		// ִ������
		RequestTask task = new RequestTask(request);
		task.execute();
	}*/
	
	/** ���� JsonCallback */
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
		
		// ���� callback
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
		
		// ִ������
		RequestTask task = new RequestTask(request);
		task.execute();
	}
	*/
	
	/** ���� StringCallback */
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
		
		// ���� callback
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
		
		// ִ������
		RequestTask task = new RequestTask(request);
		task.execute();
	}
	*/
	
	/** ���� StringCallback */
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
		
		// ���� callback
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
		
		// ִ������
		RequestTask task = new RequestTask(request);
		task.execute();
	}
	*/
	
	/** ���� ProgressUpdate ���ȸ��� */
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
		
		// ���� callback
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
				System.out.println("���ȸ���ִ��...");
				System.out.println(current + " : " + total);
			}
		});
		
		// ִ������
		RequestTask task = new RequestTask(request);
		task.execute();
	}
	*/
	
	/** ���� FileCallback �� �ļ����ؽ��ȸ��� */
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
		
		// ���� callback
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
				System.out.println("���ȸ���ִ��...");
				System.out.println(current + " : " + total);
				mTvProgress.setText((current / (float)total) * 100 + "%");
			}
		});
		
		// ִ������
		RequestTask task = new RequestTask(request);
		task.execute();
	}
	*/
	
	/** ���� AppException */
	/*
	private void onSubThreadAppException(){
		Request request = new Request();
		request.setUrl("sshttp://httpbin.org/get");
		request.setMethod(RequestMethod.POST);
		request.setEnableProgressUpdate(true);
		
		// ���� callback
		request.setCallback(new StringCallback() {
			
			@Override
			public void onSuccess(String result) {
				System.out.println("�ɹ��ص�");
			}
			
			@Override
			public void onFail(AppException ex) {
				System.out.println(ex.getMessage());
			}
		});
		
		// ִ������
		RequestTask task = new RequestTask(request);
		task.execute();
	}
	
	*//** ���� onGlobalExceptionListener ȫ���쳣 *//*
	private void onSubThreadonGlobalExceptionListener(){
		Request request = new Request();
		request.setUrl("http://httpbin.org/get");
		request.setMethod(RequestMethod.POST);
		request.setEnableProgressUpdate(true);
		
		request.setOnGlobalExceptionListener(this);
		
		// ���� callback
		request.setCallback(new StringCallback() {
			
			@Override
			public void onSuccess(String result) {
				System.out.println("�ɹ��ص�");
			}
			
			@Override
			public void onFail(AppException ex) {
				System.out.println("onFail: "+ ex.getMessage());
			}
		});
		
		// ִ������
		RequestTask task = new RequestTask(request);
		task.execute();
	}*/

	@Override
	public boolean handlerException(AppException ex) {
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		builder.setMessage(ex.getMessage());
		AlertDialog create = builder.create();
		create.show();
		return false;
	}
	
	/** ���� AppException */
	/*private void onSubThreadAppException(){
		Request request = new Request();
		request.setUrl("sshttp://httpbin.org/get");
		request.setMethod(RequestMethod.POST);
		request.setEnableProgressUpdate(true);
		
		// ���� callback
		request.setCallback(new StringCallback() {
			
			@Override
			public void onSuccess(String result) {
				System.out.println("�ɹ��ص�");
			}
			
			@Override
			public void onFail(AppException ex) {
				System.out.println(ex.getMessage());
			}
		});
		
		// ִ������
		RequestTask task = new RequestTask(request);
		task.execute();
	}*/
	
	/** ���� RetryCount Timeout ���� */
	/*private void onSubThreadonRetryCount(){
		Request request = new Request();
		request.setUrl("http://httpbin.org/delay/10");
		request.setMethod(RequestMethod.GET);
		request.setEnableProgressUpdate(true);
		
		// �������Դ���
		request.setMaxRetryCount(3);
		
		// ���� callback
		request.setCallback(new StringCallback() {
			
			@Override
			public void onSuccess(String result) {
				System.out.println("�ɹ��ص�");
			}
			
			@Override
			public void onFail(AppException ex) {
				System.out.println("onFail: "+ ex.getMessage());
			}
		});
		
		// ִ������
		RequestTask task = new RequestTask(request);
		task.execute();
	}*/
	
	/** ���� ����ȡ�� ���ش��ļ� */
	private void onSubThreadCancelDownload(){
		if (request == null){
			request = new Request();
		}
		
		request.setUrl("http://dlsw.baidu.com/sw-search-sp/soft/bf/35013/Baidu_Setup_2348_2.3.0.1694_10000010.1456900451.exe");
		request.setMethod(RequestMethod.GET);
		request.setEnableProgressUpdate(true);
		
		// �������Դ���
		request.setMaxRetryCount(3);
		
		File file = new File(Environment.getExternalStorageDirectory(), "baidu.exe");
		
		// ���� callback
		request.setCallback(new FileCallback(file) {
			
			@Override
			public void onSuccess(File result) {
				
			}
			
			@Override
			public void onFail(AppException ex) {
				
			}
			
			@Override
			public void onProgress(int current, int total) {
				System.out.println("���ȸ���ִ��...");
				System.out.println(current + " : " + total);
				mTvProgress.setText((current / (float)total) * 100 + "%");
			}
		});
		
		// ִ������
		RequestTask task = new RequestTask(request);
		task.execute();
	}

	/** ��������ȡ�� �����ַ���  */
	private void onSubThreadCancelLoadString(){
		if (request == null){
			request = new Request();
		}
		
		request.setUrl("http://httpbin.org/get");
		request.setMethod(RequestMethod.GET);
		request.setEnableProgressUpdate(true);
		
		// �������Դ���
		request.setMaxRetryCount(0);
		
		// ���� callback
		request.setCallback(new StringCallback() {
			
			@Override
			public void onSuccess(String result) {
				System.out.println(result);
			}
			
			@Override
			public void onFail(AppException ex) {
				
			}
		});
		
		// ִ������
		RequestTask task = new RequestTask(request);
		task.execute();
	}
	
	/** ��������ȡ�� ����  */
	private void onSubThreadCancelRetry(){
		if (request == null){
			request = new Request();
		}
		
		request.setUrl("http://httpbin.org/delay/10");
		request.setMethod(RequestMethod.GET);
		request.setEnableProgressUpdate(true);
		
		// �������Դ���
		request.setMaxRetryCount(5);
		
		// ���� callback
		request.setCallback(new StringCallback() {
			
			@Override
			public void onSuccess(String result) {
				System.out.println(result);
			}
			
			@Override
			public void onFail(AppException ex) {
				
			}
		});
		
		// ִ������
		RequestTask task = new RequestTask(request);
		task.execute();
		
	}
	
	@Override
	public void onClick(View v) {
//		request.setCancel(true);
		if (manager != null){
			manager.cancelRequest("main");
		}
	}
	
	private void onSubThreadTextRequestManager() {
		request = new Request();
		
		request.setUrl("http://httpbin.org/get");
		request.setMethod(RequestMethod.GET);
		request.setEnableProgressUpdate(true);
		
		// �������Դ���
		request.setMaxRetryCount(0);
		request.setTag("main");
		
		// ���� callback
		request.setCallback(new StringCallback() {
			
			@Override
			public void onSuccess(String result) {
				System.out.println(result);
			}
			
			@Override
			public void onFail(AppException ex) {
				
			}
		});

		manager = RequestManager.getInstance();
		manager.performRequest(request);
	}
}
