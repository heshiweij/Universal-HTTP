package cn.ifavor.http.libs.callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by Administrator on 2016/2/29.
 */
public abstract class JSONArrayCallback extends AbstractCallback<JSONArray> {
	@Override
	public JSONArray bindData(String s) throws Exception {
		return  new JSONArray(s);
	}
}
