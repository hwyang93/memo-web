package memoWeb.common.utils;

import java.util.Map;

import javax.servlet.http.HttpSession;

import memoWeb.common.constant.CommonConstants;

public class SessionUtil {
	public static void setUserSessionInfo(Map<String,Object> params, HttpSession session) {

		if( session.getAttribute(CommonConstants.SESSION) != null) {
			params.put(CommonConstants.SESSION, session.getAttribute(CommonConstants.SESSION));
		}

	}
}
