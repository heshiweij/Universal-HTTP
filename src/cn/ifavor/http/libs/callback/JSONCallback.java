package cn.ifavor.http.libs.callback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;

import cn.ifavor.http.libs.HttpUrlConnectionUtils;
import cn.ifavor.http.libs.tools.JsonHelp;

public abstract class JSONCallback<T> implements ICallback<T>{

	@Override
	public void onPre() {
		
	}

	@Override
	public T parse(HttpURLConnection connection) throws Exception {
		int statusCode = connection.getResponseCode();
		if (statusCode == HttpUrlConnectionUtils.HTTP_STATUS_CODE_SUCCESS) {
				InputStream is = connection.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[HttpUrlConnectionUtils.BUFFER_SIZE];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				
				// 关闭流
				is.close();
				baos.flush();
				baos.close();

				String res = new String(baos.toByteArray());
				
				// 根据泛型获取其 Class<T> 对象
				// 解析 JSON
				return JsonHelp.json2Bean(res, getObjClass());
			}
		
		return null;
	}
	
	/** 根据泛型获取其 Class<T> 对象 */
	private Class<T> getObjClass(){
		Type genType = getClass().getGenericSuperclass(); 
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments(); 
		Class<T> objClass = (Class<T>) params[0]; 
		
		return objClass;
	}
}
