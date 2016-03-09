package cn.ifavor.http.base;

import android.app.Activity;
import android.os.Bundle;
import cn.ifavor.http.libs.listener.OnGlobalExceptionListener;

public abstract class BaseActivity extends Activity implements OnGlobalExceptionListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
