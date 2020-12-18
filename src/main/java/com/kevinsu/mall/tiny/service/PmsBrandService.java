package com.kevinsu.mall.tiny.service;

import com.kevinsu.mall.tiny.mbg.model.PmsBrand;

import java.util.List;

/**
 * @author SuGengTian kevinsu917@126.com
 * @since 2020-12-16-09:44
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}