package com.mmall.util;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

/**
 * Created by yangjin on 18/1/5.
 */
public class CommonUtil {
    public static File multipartFileToFile(MultipartFile multipartFile) {
        CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        return fi.getStoreLocation();
    }
}
