package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 请填写类注释
 *
 * @author wei.wang
 * @since 2019年07月30日
 */
public interface FileService {

    /**
     * 附件上传
     * @param file
     * @return
     */
    boolean uploadFile(MultipartFile file) throws IOException;

    /**
     * 附件下载
     * @param path
     * @return
     */
    ResultVO downloadFile(String path);
}
