package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.properties.file.AquamanFileProperties;
import com.aquaman.security.admin.service.FileService;
import com.aquaman.security.admin.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/7/30
 * @since 2019/7/30
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private AquamanFileProperties uploadFileProperties;

    @Override
    public boolean uploadFile(MultipartFile file) throws IOException {
        // 组装文件全路径（配置路径+年月日）
        String fullPath = StringUtils.join(uploadFileProperties.getUpload().getPath()
                , "/", DateUtils.customDateFormat("yyyyMMdd"), "/");
        // 文件名称
        String fileName = file.getOriginalFilename();
        // 截取文件后缀
        String fileSuffix = StringUtils.substringAfterLast(fileName, ".");
        // 系统存档的文件名
        String newFileName = StringUtils.join(DateUtils.currentUnsigned(), ".", fileSuffix);
        File uploadFile = new File(fullPath);
        if(!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        uploadFile = new File(fullPath + newFileName);
        file.transferTo(uploadFile);
        return true;
    }

    @Override
    public ResultVO downloadFile(String path) {
        return null;
    }

}
