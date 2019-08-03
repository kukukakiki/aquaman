/*
MIT License

Copyright (c) 2019 wei wang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.aquaman.security.oss.aliyun.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aquaman.security.oss.aliyun.properties.AliYunOSSProperties;
import com.aquaman.security.oss.aliyun.service.AliYunOSSUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019-08-03
 * @since 2019-08-03
 */
@Service
public class AliYunOSSUploadServiceImpl implements AliYunOSSUploadService {

    @Autowired
    private AliYunOSSProperties properties;

    @Override
    public void networkFileUpload(String fileName, String networkPath) throws IOException {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(properties.getManagement().getEndpoint(),
                properties.getManagement().getAccessKeyId(), properties.getManagement().getAccessKeySecret());
        // 上传文件流
        InputStream inputStream = new URL(networkPath).openStream();
        try {
            ossClient.putObject(properties.getManagement().getBucketName(), properties.getManagement().getOssPath() + fileName, inputStream);
        } catch (OSSException oe) {

        } catch (ClientException ce) {

        } finally {
            // 关闭OSSClient
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    @Override
    public void streamFileUpload(String fileName, InputStream inputStream) throws IOException {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(properties.getManagement().getEndpoint(),
                properties.getManagement().getAccessKeyId(), properties.getManagement().getAccessKeySecret());
        try {
            // 上传文件流
            ossClient.putObject(properties.getManagement().getBucketName(), properties.getManagement().getOssPath() + fileName, inputStream);
        } catch (OSSException oe) {

        } catch (ClientException ce) {

        } finally {
            // 关闭OSSClient
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    @Override
    public void localFileUpload(String fileName, String filePath) throws IOException {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(properties.getManagement().getEndpoint(),
                properties.getManagement().getAccessKeyId(), properties.getManagement().getAccessKeySecret());
        try {
            // 上传本地文件，需要有文件后缀
            ossClient.putObject(properties.getManagement().getBucketName(), properties.getManagement().getOssPath() + fileName, new File(filePath));
        } catch (OSSException oe) {

        } catch (ClientException ce) {

        } finally {
            // 关闭OSSClient
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
