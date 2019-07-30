package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.properties.file.AquamanUploadFileProperties;
import com.aquaman.security.admin.service.FileService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/7/30
 * @since 2019/7/30
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private AquamanUploadFileProperties uploadFileProperties;

    @Override
    public ResultVO uploadFile(MultipartFile file) {
        String path = uploadFileProperties.getUploadFile().getPath();
        String fileName = file.getOriginalFilename();
        // TODO 未完成,需要需要增加校验,存放的完整路径等等
        return null;
    }

    @Override
    public ResultVO downloadFile(String path) {
        return null;
    }
}
