package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.UploadFileInfo;
import com.aquaman.security.admin.mapper.UploadFileInfoMapper;
import com.aquaman.security.admin.service.IFileService;
import com.aquaman.security.admin.service.IUploadFileInfoService;
import com.aquaman.security.oss.aliyun.service.AliYunOSSUploadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wei wang
 * @since 2019-07-31
 */
@Service
public class UploadFileInfoServiceImpl extends ServiceImpl<UploadFileInfoMapper, UploadFileInfo> implements IUploadFileInfoService {

    @Autowired
    private IFileService fileService;

    @Autowired
    private UploadFileInfoMapper uploadFileInfoMapper;

    @Override
    public Long uploadAndSave(MultipartFile file) {
        try {
            UploadFileInfo uploadFileInfo = fileService.uploadFileResultInfo(file);
            uploadFileInfoMapper.insert(uploadFileInfo);
            return uploadFileInfo.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void downloadFileById(HttpServletResponse response, Long id) {
        UploadFileInfo uploadFileInfo = uploadFileInfoMapper.selectById(id);
        try {
            fileService.downloadFile(response, uploadFileInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
