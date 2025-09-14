package cn.homyit.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Random;

/**
 * @program:
 * @description: cos 文件上传工具类
 * @author: Answer
 * @create: 2024/6/12 18:12
 **/
@Component
public class TencentCOSUploadFileUtil {
    // 访问域名
    public static final String URL = "https://cos.homyit2023.online";
    // 存储桶名称
    private static final String BUCKET_NAME = "waimai-1330680464";
    //secretId 秘钥id
    private static final String SECRET_ID = "AKIDKTP7cP4VjjV3NTwCG7kEVYQ9evzpSrSr";
    //SecretKey 秘钥
    private static final String SECRET_KEY = "FG5h5HG3d6MOEqFRPBk5C5kAQUL2Vknf";
    // 自定义文件夹名称，COS会根据这个路径自动创建对应文件夹
    private static final String PREFIX = "xiaochengxu/";
    // 创建COS 凭证
    private static final COSCredentials credentials = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);
    // 配置 COS 区域 就购买时选择的区域
    private static final ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));

    public static String uploadFile(MultipartFile file) {
        // 创建 COS 客户端连接
        COSClient cosClient = new COSClient(credentials, clientConfig);
        String fileName = file.getOriginalFilename();
        try {
            String substring = fileName.substring(fileName.lastIndexOf("."));
            File localFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), substring);
            file.transferTo(localFile);
            // 同一时间上传多个就重名了，所以增加随机数
            Random random = new Random();
            fileName = PREFIX + random.nextLong(1000000000) + substring;

            PutObjectRequest objectRequest = new PutObjectRequest(BUCKET_NAME, fileName, localFile);
            //        // 设置元数据，包括 Content-Disposition
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setHeader("Content-Disposition", "inline");
            objectRequest.setMetadata(metadata);
            // 将文件上传至 COS
            cosClient.putObject(objectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cosClient.shutdown();
        }
        // 给前端返回图片访问地址（用于读操作）和文件名(用于删除文件）
        return URL + "/" + fileName;
    }
//public static String uploadFile(MultipartFile file) {
//    // 创建 COS 客户端连接
//    COSClient cosClient = new COSClient(credentials, clientConfig);
//    String fileName = file.getOriginalFilename();
//    try {
//        String substring = fileName.substring(fileName.lastIndexOf("."));
//        File localFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), substring);
//        file.transferTo(localFile);
//        // 同一时间上传多个就重名了，所以增加随机数
//        Random random = new Random();
//        fileName = PREFIX + random.nextInt(10000) + substring;
//
//        // 创建上传对象的请求
//        PutObjectRequest objectRequest = new PutObjectRequest(BUCKET_NAME, fileName, localFile);
//
//        // 设置元数据，包括 Content-Disposition
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, StandardCharsets.UTF_8) + "\"");
//        objectRequest.setMetadata(metadata);
//
//        // 将文件上传至 COS
//        cosClient.putObject(objectRequest);
//    } catch (Exception e) {
//        e.printStackTrace();
//    } finally {
//        cosClient.shutdown();
//    }
//    // 给前端返回图片访问地址（用于读操作）和文件名(用于删除文件）
//    return URL + "/" + fileName;
//}


    public static Boolean deleteFile(String name) {
        try {
            COSClient cosClient = new COSClient(credentials, clientConfig);
            System.out.println("1 = " + 1);
            // 指定要删除的 bucket 和路径
            cosClient.deleteObject(BUCKET_NAME, name);
            // 关闭客户端(关闭后台线程)
            cosClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // 下载文件
    public static COSClient downloadFile() {
        return new COSClient(credentials, clientConfig);
    }

    public static String getBucketName() {
        return BUCKET_NAME;
    }
}
