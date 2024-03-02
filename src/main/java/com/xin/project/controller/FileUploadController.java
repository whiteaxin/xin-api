package com.xin.project.controller;
import com.xin.apicommon.common.BaseResponse;
import com.xin.apicommon.common.ResultUtils;
import com.xin.project.service.FileUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Resource
    private FileUploadService fileUploadService ;

    @PostMapping(value = "/upload")
    public BaseResponse<Map<String, Object>> fileUploadService(@RequestParam(value = "file") MultipartFile multipartFile) {
        String fileUrl = fileUploadService.fileUpload(multipartFile) ;
        Map<String, Object> result = new HashMap<>(2);
        result.put("url", fileUrl);
        return ResultUtils.success(result);
    }

}