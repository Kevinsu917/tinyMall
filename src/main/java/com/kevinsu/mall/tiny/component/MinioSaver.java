package com.kevinsu.mall.tiny.component;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.kevinsu.mall.tiny.dto.BucketPolicyConfigDto;
import io.minio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MinIO配置
 * @author SuGengTian kevinsu917@126.com
 * @since 2020-12-24-11:54
 */
@Component
public class MinioSaver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioSaver.class) ;

    private MinioClient minioClient ;

    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.bucketNameImage}")
    private String IMAGE_BUCKET;

    @PostConstruct
    private void init(){
        try {
            minioClient = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(accessKey, secretKey).build();
            createBucket(IMAGE_BUCKET);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("MinIoClient init fail ...");
        }
    }

    /**
     * 上传图片文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String uploadImage (MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 设置存储对象名称
        String objectName = sdf.format(new Date()) + "/" + filename;
        // 使用putObject上传一个文件到存储桶中
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(IMAGE_BUCKET)
                .object(objectName)
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE).build();
        minioClient.putObject(putObjectArgs);
        return endpoint + "/" + IMAGE_BUCKET + "/" + objectName;
    }

    /**
     * 创建桶
     * @param bucketName
     */
    private void createBucket(String bucketName){
        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(bucketName).build());

                BucketPolicyConfigDto bucketPolicyConfigDto = createBucketPolicyConfigDto(bucketName);
                SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs.builder()
                        .bucket(bucketName)
                        .config(JSONUtil.toJsonStr(bucketPolicyConfigDto))
                        .build();
                minioClient.setBucketPolicy(setBucketPolicyArgs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("创建桶失败: {}！", e.getMessage());
        }
    }

    private BucketPolicyConfigDto createBucketPolicyConfigDto(String bucketName) {
        BucketPolicyConfigDto.Statement statement = BucketPolicyConfigDto.Statement.builder()
                .Effect("Allow")
                .Principal("*")
                .Action("s3:GetObject")
                .Resource("arn:aws:s3:::"+bucketName+"/*.**").build();
        return BucketPolicyConfigDto.builder()
                .Version("2012-10-17")
                .Statement(CollUtil.toList(statement))
                .build();
    }
}
