package com.github.firelcw.hander;

/**
 * 处理器
 * @author liaochongwei
 * @date 2020/8/31 15:58
 */
public interface Handler<T> {

    T execute();
}
