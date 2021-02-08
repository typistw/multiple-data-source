package com.common.api.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.http.BodyReaderRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseInterceptor {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	/**
	 * 统一exinclude
	 */
	protected boolean exclude(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String referer = request.getHeader("Referer");
		String requestUrl = request.getParameter("requestUrl");
		if (StringUtils.isEmpty(requestUrl) && StringUtils.isNotEmpty(referer)) {
			requestUrl = referer;
		}
		request.setAttribute("requestUrl", requestUrl);
		StringBuffer url = request.getRequestURL();
		String[] excuteurl={".html",".js",".css","swagger-resources","api-docs"};
		for(String key:excuteurl) {
			if(url.toString().endsWith(key)) {
				return true;
			}
		}
		return false;
	}

	/**
     *  获取 body json 对象
	 * @param request
	 * @return
	 * @throws IOException
	 */
	protected JSONObject getRequestJSONVo(HttpServletRequest request) throws IOException {
		BodyReaderRequestWrapper wrappedRequest = new BodyReaderRequestWrapper(request);
		String params = wrappedRequest.getBodyString(request);
		return  JSON.parseObject(params);
	}
}
