package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yangjin on 18/1/4.
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
    String qiniuUpload(MultipartFile file, String path);
}
