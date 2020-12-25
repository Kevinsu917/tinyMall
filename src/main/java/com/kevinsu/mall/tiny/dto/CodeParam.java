package com.kevinsu.mall.tiny.dto;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author SuGengTian kevinsu917@126.com
 * @since 2020-12-25-10:09
 */
@Data
public class CodeParam {

    @Size(min = 10, message = "请填写正确的电话号码")
    String telephone;
    String authCode;
}
