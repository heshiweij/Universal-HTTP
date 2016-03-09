package cn.ifavor.http.libs.callback.impl;

import org.json.JSONArray;

import cn.ifavor.http.libs.callback.AbstractCallback;

/**
 * Created by Administrator on 2016/2/29.
 */
public abstract class JSONArrayCallback extends AbstractCallback<JSONArray> {
	@Override
	public JSONArray bindData(String s) throws Exception {
		return  new JSONArray(s);
	}
}
