package com.kevinsu.mall.tiny.service;

import com.kevinsu.mall.tiny.common.api.CommonResult;

/**
 * 会员管理Service
 * @author SuGengTian kevinsu917@126.com
 * @since 2020-12-18-15:06
 */
public interface UmsMemberService {

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}