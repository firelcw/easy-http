package com.github.firelcw.interceptor;


import com.github.firelcw.exception.CodeStatusException;
import com.github.firelcw.model.HttpRequest;
import com.github.firelcw.model.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 错误拦截器
 * @author liaochongwei
 * @date 2020/7/31 13:43
 */
public class ErrorInterceptor implements HttpInterceptor{

    private static final Logger log = LoggerFactory.getLogger(ErrorInterceptor.class);
    @Override
    public boolean preHandle(HttpRequest request) {
        return true;
    }

    @Override
    public void postHandle(HttpRequest request, HttpResponse response) {
        if (response.isOk()) {
            return;
        }
        if (log.isErrorEnabled()) {
            log.error("请求错误=>{}, [{}]:{}",request.getUrl(),response.getStatusCode(),response.getMessage());
        }
        throw new CodeStatusException(response.getStatusCode(), response.getMessage());
    }

    @Override
    public int order() {
        return 1;
    }
}
