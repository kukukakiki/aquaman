package com.aquaman.security.admin.controller.file;

import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.properties.file.AquamanUploadFileProperties;
import com.aquaman.security.admin.properties.security.AquamanSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * 附件上传Controller
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/7/30
 * @since 2019/7/30
 */
@RestController
@RequestMapping("/upload")
public class UploadFileController extends BaseController {

    @Autowired
    private AquamanUploadFileProperties uploadFilePropertites;

    @PostMapping("/file")
    public ResultVO<String> file(@RequestParam("file") MultipartFile file) throws FileNotFoundException {
        String projectPath = ResourceUtils.getURL("classpath:").getPath();
        String filePath = uploadFilePropertites.getUploadFile().getPath();

        return null;
    }
}
