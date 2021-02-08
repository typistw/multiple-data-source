package com.common.api.filter;

import com.common.http.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(filterName = "HttpServletResponseFilter", urlPatterns = "/")
@Order(10000)
public class HttpServletResponseFilter implements Filter {

    private String[] EXCLUDE_URL = {".html",".js",".css","swagger-resources","api-docs", "swagger-resources", ".ico", ".woff", ".gif"};

	private final static Logger logger = LoggerFactory.getLogger(HttpServletResponseFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	HttpServletRequest req = (HttpServletRequest) servletRequest;
    	String uri = req.getRequestURI();

    	boolean hasExclude = false;
    	for(String key : EXCLUDE_URL){
    	    if(uri.endsWith(key)){
    	        hasExclude = true;
    	        break;
            }
        }

        if(hasExclude){
             filterChain.doFilter(servletRequest, servletResponse);
        }else{
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            // 包装响应对象 resp 并缓存响应数据
            ResponseWrapper wrapper = new ResponseWrapper(resp);
            filterChain.doFilter(req, wrapper);
            wrapper.reWriteResponse(wrapper, (HttpServletResponse) servletResponse);

            String response = new String(wrapper.getResponseData(), "utf-8");
            response= response.replaceAll("\\s*", "");
            // 响应日志输出
            logger.info("response = {}", response);
        }

    }

    @Override
    public void destroy() {

    }
 
}
