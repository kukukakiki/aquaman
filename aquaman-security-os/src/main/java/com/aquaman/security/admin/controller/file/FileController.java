package com.aquaman.security.admin.controller.file;

import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 附件上传Controller
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/7/30
 * @since 2019/7/30
 */
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

    @Autowired
    private IFileService fileService;

    @PostMapping("/upload")
    public ResultVO<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.uploadFile(file);
        return null;
    }
}
