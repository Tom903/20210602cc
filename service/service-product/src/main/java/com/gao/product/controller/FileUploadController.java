package com.gao.product.controller;

import com.gao.common.result.Result;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("admin/product")
public class FileUploadController {

    @Value("${fileServer.url}")
    private String fileServerUrl;

    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file) throws IOException, MyException {
        /**
         * 1.读取tracker.conf,初始化
         * 2.创建TrackerClient
         * 3.创建TrackerServer
         * 4.创建stroageClient
         * 5.上传
         * 6.将上传完的url返回
         */
        //读取文件
        String configFile = this.getClass().getResource("/tracker.conf").getFile();
        String path = null;
        if (!StringUtils.isEmpty(configFile)) {
            //init方法初始化
            ClientGlobal.init(configFile);
            //创建TrackerClient
            TrackerClient trackerClient = new TrackerClient();
            //创建TrackerServer
            TrackerServer trackerServer = trackerClient.getConnection();
            //创建stroageClient
            StorageClient1 storageClient1 = new StorageClient1();

            //上传
            //第一个参数表示上传文件的字节数组，第二个参数后缀名
            String filename = file.getOriginalFilename();
            String extName = FilenameUtils.getExtension(filename);

            path = storageClient1.upload_appender_file1(file.getBytes(), extName, null);
            System.out.println(fileServerUrl + path);
        }
        return Result.ok(fileServerUrl + path);
    }
}
