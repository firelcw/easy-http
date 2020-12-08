package com.github.vizaizai.parser;

import com.github.vizaizai.util.Utils;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 接口解析
 * @author liaochongwei
 * @date 2020/7/30 15:24
 */
public class InterfaceParser {
    /**
     * 目标接口
     */
    private Class<?> target;
    /**
     * 接口级别的headers
     */
    private Map<String,String> headers;

    public InterfaceParser() {
    }

    public InterfaceParser(Class<?> target) {
        this.target = target;
        this.parse();
    }
    public void parse(Class<?> target) {
        this.target = target;
        this.parse();
    }

    private void parse() {
        Annotation[] annotations = this.target.getAnnotations();
        this.headers = Utils.getHeaders(annotations);
    }

    public Class<?> getTarget() {
        return target;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
