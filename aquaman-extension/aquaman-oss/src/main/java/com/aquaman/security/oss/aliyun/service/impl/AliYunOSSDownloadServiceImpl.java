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

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import com.aquaman.security.oss.aliyun.properties.AliYunOSSProperties;
import com.aquaman.security.oss.aliyun.service.AliYunOSSDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019-08-03
 * @since 2019-08-03
 */
@Service
public class AliYunOSSDownloadServiceImpl implements AliYunOSSDownloadService {

    @Autowired
    private OSS aliyunOSSClient;

    @Autowired
    private AliYunOSSProperties properties;


    @Override
    public void streamFileDownload(String fileName, OutputStream out) throws IOException {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = aliyunOSSClient.getObject(properties.getManagement().getBucketName(), fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()))){
            int len = 0;
            while((len = reader.read()) != -1) {
                out.write(len);
                out.flush();
            }
        }
    }
}
