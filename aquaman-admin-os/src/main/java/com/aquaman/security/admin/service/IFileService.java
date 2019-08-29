package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.domain.UploadFileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请填写类注释
 *
 * @author wei.wang
 * @since 2019年07月30日
 */
public interface IFileService {

    /**
     * 附件上传
     * @param file
     * @return 文件路径
     */
    String uploadFile(MultipartFile file) throws IOException;

    /**
     * 附件上传
     * @param file
     * @return {@link UploadFileInfo}
     * @throws IOException
     */
    UploadFileInfo uploadFileResultInfo(MultipartFile file) throws IOException;

    /**
     * 附件下载
     * @param response
     * @param path
     * @param fileName
     * @return
     */
    void downloadFile(HttpServletResponse response, String path, String fileName) throws IOException;

    /**
     * 附件下载
     * @param response
     * @param uploadFileInfo
     * @return
     */
    void downloadFile(HttpServletResponse response, UploadFileInfo uploadFileInfo) throws IOException;
}
