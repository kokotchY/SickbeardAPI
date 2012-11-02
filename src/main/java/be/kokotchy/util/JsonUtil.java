package be.kokotchy.util;

import org.json.simple.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: canas
 * Date: 11/2/12
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonUtil {

    public static String getString(JSONObject jsonObject, String key) {
        if (jsonObject.containsKey(key)) {
            return jsonObject.get(key).toString();
        } else {
            return null;
        }
    }

    public static boolean getBoolean(JSONObject jsonObject, String key) {
        if (jsonObject.containsKey(key)) {

            Object value = jsonObject.get(key);
            if (value instanceof Boolean) {
                return Boolean.parseBoolean(value.toString());
            } else {
                long longValue = (Long) value;
                return longValue == 1;
            }
        } else {
            return false;
        }
    }

    public static long getLong(JSONObject jsonObject, String key) {
        if (jsonObject.containsKey(key)) {
            Object value = jsonObject.get(key);
            if (value instanceof Long) {
                return (Long) value;
            } else {
                return Long.parseLong(value.toString());
            }
        } else {
            return -1;
        }
    }
}
