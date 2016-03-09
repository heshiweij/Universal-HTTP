package cn.ifavor.http.libs.callback;

import java.net.HttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/2/29.
 */
public abstract class JSONObjectCallback extends AbstractCallback<JSONObject> {
	
	@Override
	public JSONObject bindData(String s) throws Exception {
		return new JSONObject(s);
	}
	
}
