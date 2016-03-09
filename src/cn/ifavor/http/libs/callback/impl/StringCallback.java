package cn.ifavor.http.libs.callback.impl;

import cn.ifavor.http.libs.callback.AbstractCallback;


/**
 * Created by Administrator on 2016/2/29.
 */
public abstract class StringCallback extends AbstractCallback<String> {
	@Override
	public String bindData(String s) throws Exception {
		return s;
	}
}
