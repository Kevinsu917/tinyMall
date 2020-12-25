package com.kevinsu.mall.tiny.controller;

import com.kevinsu.mall.tiny.common.api.CommonResult;
import com.kevinsu.mall.tiny.dto.CodeParam;
import com.kevinsu.mall.tiny.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author SuGengTian kevinsu917@126.com
 * @since 2020-12-18-15:02
 */
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@Controller
@RequestMapping("/sso")
public class UmsMemberController {

    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone){
        return memberService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestBody @Validated CodeParam codeParam, BindingResult result) {
        System.out.println(result.toString());
        return memberService.verifyAuthCode(codeParam.getTelephone(), codeParam.getAuthCode());
    }
}
