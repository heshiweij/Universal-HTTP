package cn.ifavor.http.libs.callback.impl;

import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;

import cn.ifavor.http.libs.callback.AbstractCallback;

import android.util.Xml;

/**
 * Created by Administrator on 2016/2/29.
 */
public abstract class XMLPullCallback extends AbstractCallback<XmlPullParser> {
	@Override
	public XmlPullParser bindData(String s) throws Exception {
		// ���� XML
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(new StringReader(s));
		return parser;
	}

}
