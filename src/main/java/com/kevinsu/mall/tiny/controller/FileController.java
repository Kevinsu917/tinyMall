package com.kevinsu.mall.tiny.controller;

import com.kevinsu.mall.tiny.common.api.CommonResult;
import com.kevinsu.mall.tiny.component.MinioSaver;
import com.kevinsu.mall.tiny.dto.FileUploadDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件存储管理Controller
 *
 * @author SuGengTian kevinsu917@126.com
 * @since 2020-12-24-14:28
 */
@Api(tags = "FileController", description = "文件对象存储管理")
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    MinioSaver minioSaver;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult upload(@RequestParam("file") MultipartFile file) {
        try {
            String url = minioSaver.uploadImage(file);
            FileUploadDto fileUploadDto = new FileUploadDto();
            fileUploadDto.setName(url.substring(url.lastIndexOf("/"), url.length()));
            fileUploadDto.setUrl(url);
            return CommonResult.success(fileUploadDto);
        } catch (Exception e) {
            LOGGER.info("文件存储失败: {}！", e.getMessage());
        }
        return CommonResult.failed();
    }


}
