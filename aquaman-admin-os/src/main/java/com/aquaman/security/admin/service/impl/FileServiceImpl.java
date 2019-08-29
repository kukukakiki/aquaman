package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.UploadFileInfo;
import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.dto.CurrentLoginUserDTO;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.properties.file.AquamanFileProperties;
import com.aquaman.security.admin.service.IFileService;
import com.aquaman.security.admin.utils.CurrentUserUtil;
import com.aquaman.security.admin.utils.DateUtils;
import com.aquaman.security.common.constant.AquamanConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/7/30
 * @since 2019/7/30
 */
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private AquamanFileProperties uploadFileProperties;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        // 组装文件全路径（配置路径+年月日）
        String fullPath = StringUtils.join(uploadFileProperties.getUpload().getPath()
                , "\\", DateUtils.customDateFormat("yyyyMMdd"), "\\");
        // 文件名称
        String fileName = file.getOriginalFilename();
        // 截取文件后缀
        String fileSuffix = StringUtils.substringAfterLast(fileName, AquamanConstant.POINT_SYMBOL);
        // 系统存档的文件名
        String newFileName = StringUtils.join(DateUtils.currentUnsigned(), AquamanConstant.POINT_SYMBOL, fileSuffix);
        File uploadFile = new File(fullPath);
        // 路径不存在则和创建
        if(!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        fullPath = StringUtils.join(fullPath, newFileName);
        uploadFile = new File(fullPath);
        file.transferTo(uploadFile);
        return fullPath;
    }

    @Override
    public UploadFileInfo uploadFileResultInfo(MultipartFile file) throws IOException {
        // 上传文件服务器全路径
        String uploadFileFullPath = uploadFile(file);
        if(StringUtils.isNotEmpty(uploadFileFullPath)){
            CurrentLoginUserDTO loginUser = CurrentUserUtil.getLoginUserInfo();
            UploadFileInfo uploadFileInfo = new UploadFileInfo();
            // 文件名称,有后最
            String fileName = file.getOriginalFilename();
            uploadFileInfo.setPath(uploadFileFullPath);
            uploadFileInfo.setFileSize(file.getSize());
            uploadFileInfo.setName(StringUtils.substringBeforeLast(fileName, AquamanConstant.POINT_SYMBOL));
            uploadFileInfo.setSuffix(StringUtils.substringAfterLast(fileName, AquamanConstant.POINT_SYMBOL));
            uploadFileInfo.setCreateUserAccount(loginUser.getAccount());
            uploadFileInfo.setCreateUserId(loginUser.getId());
            return uploadFileInfo;
        }
        return null;
    }

    @Override
    public void downloadFile(HttpServletResponse response,  String path, String fileName) throws IOException {
        Assert.notNull(path, "附件路径不能为空");
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        try {
            fileName = "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", fileName);
            response.setContentType("multipart/form-data");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            int len = 0;
            while((len = bis.read()) != -1) {
                out.write(len);
                out.flush();
            }
        }finally {
            if(bis != null) {
                bis.close();
            }
            if(fis != null) {
                fis.close();
            }
        }
    }

    @Override
    public void downloadFile(HttpServletResponse response, UploadFileInfo uploadFileInfo) throws IOException {
        Assert.notNull(uploadFileInfo, "上传附件信息对象不能为空");
        downloadFile(response, uploadFileInfo.getPath(), StringUtils.join(uploadFileInfo.getName(), AquamanConstant.POINT_SYMBOL, uploadFileInfo.getSuffix()));
    }

}
