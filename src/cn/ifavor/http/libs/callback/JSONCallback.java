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
		// ���ݷ��ͻ�ȡ�� Class<T> ����
		Class<T> objClass = getObjClass();
		
		// ���� JSON
		return JSONHelp.json2Bean(s, objClass);
	}
	
	/** ���ݷ��ͻ�ȡ�� Class<T> ���� */
	private Class<T> getObjClass(){
		Type genType = getClass().getGenericSuperclass(); 
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments(); 
		Class<T> objClass = (Class<T>) params[0]; 
		
		return objClass;
	}
}
