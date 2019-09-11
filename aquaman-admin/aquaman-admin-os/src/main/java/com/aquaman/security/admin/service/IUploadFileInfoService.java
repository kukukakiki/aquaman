package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.domain.UploadFileInfo;
import com.aquaman.security.admin.entity.query.UploadFileInfoQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wei wang
 * @since 2019-07-31
 */
public interface IUploadFileInfoService extends IAquamanService<UploadFileInfo> {

    /**
     * 上传并保存附件
     * @param file
     * @return
     */
    Long uploadAndSave(MultipartFile file);

    void downloadFileById(HttpServletResponse response, Long id);

}
