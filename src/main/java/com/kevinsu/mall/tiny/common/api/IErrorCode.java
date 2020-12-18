package com.kevinsu.mall.tiny.common.api;

/**
 * 封装API的错误码
 * @author SuGengTian kevinsu917@126.com
 * @since 2020-12-16-09:35
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}