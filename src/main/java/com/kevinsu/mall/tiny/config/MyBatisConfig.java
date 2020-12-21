package com.kevinsu.mall.tiny.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * @author SuGengTian kevinsu917@126.com
 * @since 2020-12-18-09:49
 */
@Configuration
@MapperScan({"com.kevinsu.mall.tiny.mbg.mapper","com.kevinsu.mall.tiny.dao"})
public class MyBatisConfig {
}
