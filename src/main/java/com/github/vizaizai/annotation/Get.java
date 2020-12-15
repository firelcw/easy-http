package com.github.vizaizai.annotation;


import com.github.vizaizai.interceptor.HttpInterceptor;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Get {
    String value() default "";
    /**
     * @return 拦截器
     */
    Class<? extends HttpInterceptor>[] interceptors() default {};
    /**
     * @return 重试次数，-1=禁用重试
     */
    int retries() default 0;
    /**
     * @return 重试间隔时间（ms）
     */
    int interval() default 0;
}
