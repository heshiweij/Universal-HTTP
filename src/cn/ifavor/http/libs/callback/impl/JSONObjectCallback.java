package cn.ifavor.http.libs.callback.impl;

import org.json.JSONObject;

import cn.ifavor.http.libs.callback.AbstractCallback;

/**
 * Created by Administrator on 2016/2/29.
 */
public abstract class JSONObjectCallback extends AbstractCallback<JSONObject> {
	
	@Override
	public JSONObject bindData(String s) throws Exception {
		return new JSONObject(s);
	}
	
}
