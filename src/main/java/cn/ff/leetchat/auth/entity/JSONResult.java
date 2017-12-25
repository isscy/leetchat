package cn.ff.leetchat.auth.entity;


import org.json.JSONObject;

/**
 * 结果集处理类
 */
public class JSONResult {
	public static String fillResultString(Integer status, String message, Object result){
		JSONObject jsonpObject = new JSONObject(){{
			put("status", status);
			put("message", message);
			put("result", result);
		}};
		return jsonpObject.toString();

	}
}
