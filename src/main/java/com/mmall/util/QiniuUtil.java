package com.mmall.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


/**
 * Created by yangjin on 18/1/5.
 */
public class QiniuUtil {
    private static final Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

    private static String ak = PropertiesUtil.getProperty("qiniu.ak");
    private static String sk = PropertiesUtil.getProperty("qiniu.sk");
    private static String bucket = PropertiesUtil.getProperty("qiniu.bucket");

    public static void uploadFile(File file) {
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(getAk(), getSk());
        String upToken = auth.uploadToken(getBucket());
        String localFilePath = file.getPath();
        String key = file.getName();
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            logger.info("七牛上传结果:{} : {}", putRet.key, putRet.hash);
        } catch (QiniuException e) {
            logger.error("七牛上传错误", e);
        }
    }


    public static String getAk() {
        return ak;
    }

    public static String getSk() {
        return sk;
    }

    public static String getBucket() {
        return bucket;
    }
}
