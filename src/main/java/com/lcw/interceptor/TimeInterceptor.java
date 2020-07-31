package com.lcw.interceptor;


import com.lcw.model.HttpRequest;
import com.lcw.model.HttpRequestConfig;
import com.lcw.model.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间拦截器
 * @author liaochongwei
 * @date 2020/7/31 14:06
 */

public class TimeInterceptor implements HttpInterceptor{
    private static final Logger log = LoggerFactory.getLogger(TimeInterceptor.class);
    private long startTime;

    @Override
    public boolean preHandle(HttpRequest request, HttpRequestConfig config) {
        startTime = System.currentTimeMillis();
        return true;
    }

    @Override
    public void postHandle(HttpRequest request, HttpResponse response) {
        long endTime = System.currentTimeMillis();
        log.info("请求 => {},耗时为{}毫秒",request.getUrl(), endTime - startTime);
    }

    @Override
    public int order() {
        return 0;
    }
}
