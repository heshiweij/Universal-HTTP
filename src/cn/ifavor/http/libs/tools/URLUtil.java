package cn.ifavor.http.libs.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/3/1.
 */
public class URLUtil {

    /**
     * �ж� URL ��ַ�Ƿ�Ϸ�
     * @param url
     * @return true �Ϸ���false �Ƿ�
     */
    public static boolean isNetworkUrl(String url) {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher(url);
        return matcher.matches();
    }
}
