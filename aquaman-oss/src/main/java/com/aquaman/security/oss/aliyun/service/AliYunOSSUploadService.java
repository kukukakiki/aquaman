package com.aquaman.security.oss.aliyun.service;

import java.io.IOException;
import java.io.InputStream;

public interface AliYunOSSUploadService {


    /**
     * 网络上传
     * @param networkPath
     * @throws IOException
     */
    void networkFileUpload(String fileName, String networkPath) throws IOException;


    /**
     * 流式上传
     * @param inputStream
     * @throws IOException
     */
    void streamFileUpload(String fileName, InputStream inputStream) throws IOException;


    /**
     * 本地文件上传
     * @param filePath
     * @throws IOException
     */
    void localFileUpload(String fileName, String filePath) throws IOException;
}
