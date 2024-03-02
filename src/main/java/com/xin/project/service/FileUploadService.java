package com.xin.project.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    /**
     * 文件上传
     * @param multipartFile 文件
     * @return 文件路径
     */
    String fileUpload(MultipartFile multipartFile);
}
