package com.kevinsu.mall.tiny.dao;

import com.kevinsu.mall.tiny.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义Dao
 * @author SuGengTian kevinsu917@126.com
 * @since 2020-12-22-11:37
 */
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
