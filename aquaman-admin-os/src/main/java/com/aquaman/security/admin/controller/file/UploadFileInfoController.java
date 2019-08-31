package com.aquaman.security.admin.controller.file;


import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.domain.UploadFileInfo;
import com.aquaman.security.admin.entity.query.UploadFileInfoQuery;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.service.IUploadFileInfoService;
import com.aquaman.security.common.enums.ResultCodeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wei wang
 * @since 2019-07-31
 */
@RestController
@RequestMapping("/upload_file_info")
public class UploadFileInfoController extends BaseController {

    @Autowired
    private IUploadFileInfoService uploadFileInfoService;

    /**
     * 获取附件列表
     * @param query
     * @return
     */
    @GetMapping
    public ResultVO<List<UploadFileInfo>> getByPage(UploadFileInfoQuery query){
        IPage<UploadFileInfo> page1 = uploadFileInfoService.pageByQuery(query);
        ResultVO<List<UploadFileInfo>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, page1);
        return resultVO;
    }

    /**
     * 上传附件并保存附件信息表
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResultVO<Long> uploadAndSave(@RequestParam("file")MultipartFile file) {
        Long id = uploadFileInfoService.uploadAndSave(file);
        ResultVO<Long> resultVO = new ResultVO<>(ResultCodeEnum.SUCCESS, id);
        return resultVO;
    }

    /**
     * 根据附件信息表ID,下载附件
     * @param id
     */
    @GetMapping("/download/{id:\\d+}")
    public void download(HttpServletResponse response, @PathVariable Long id) {
        uploadFileInfoService.downloadFileById(response, id);

    }
}
