package cn.ifavor.http.libs.callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import cn.ifavor.http.libs.tools.JSONHelp;

public abstract class JSONCallback<T> extends AbstractCallback<T>{

	@Override
	public void onPre() {
		
	}

	@Override
	public T bindData(String s) {
		// 根据泛型获取其 Class<T> 对象
		Class<T> objClass = getObjClass();
		
		// 解析 JSON
		return JSONHelp.json2Bean(s, objClass);
	}
	
	/** 根据泛型获取其 Class<T> 对象 */
	private Class<T> getObjClass(){
		Type genType = getClass().getGenericSuperclass(); 
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments(); 
		Class<T> objClass = (Class<T>) params[0]; 
		
		return objClass;
	}
}
