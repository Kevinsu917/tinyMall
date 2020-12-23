package com.kevinsu.mall.tiny.service;

import com.kevinsu.mall.tiny.common.api.CommonResult;
import com.kevinsu.mall.tiny.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台订单管理Service
 * @author SuGengTian kevinsu917@126.com
 * @since 2020-12-23-16:17
 */
public interface OmsPortalOrderService {

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);
}