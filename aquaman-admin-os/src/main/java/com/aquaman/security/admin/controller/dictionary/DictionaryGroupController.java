package com.aquaman.security.admin.controller.dictionary;


import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.domain.DictionaryGroup;
import com.aquaman.security.admin.entity.query.DictionaryGroupQuery;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.DeleteEnum;
import com.aquaman.security.admin.enums.StatusEnum;
import com.aquaman.security.admin.service.IDictionaryGroupService;
import com.aquaman.security.common.enums.ResultCodeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wei wang
 * @since 2019-07-27
 */
@RestController
@RequestMapping("/dictionary_group")
@Slf4j
public class DictionaryGroupController extends BaseController {

    @Autowired
    private IDictionaryGroupService dictionaryGroupService;

    /**
     * 获取字典组列表
     * @param dictionaryGroupQuery
     * @return
     */
    @GetMapping
    public ResultVO<List<DictionaryGroup>> getByPage(DictionaryGroupQuery dictionaryGroupQuery){
        IPage<DictionaryGroup> page1 = dictionaryGroupService.pageByQuery(dictionaryGroupQuery);
        ResultVO<List<DictionaryGroup>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, page1);
        return resultVO;
    }

    /**
     * 根据ID获取字典组详情
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public ResultVO getById(@PathVariable Long id){
        DictionaryGroup DictionaryGroup = dictionaryGroupService.getById(id);
        ResultVO<DictionaryGroup> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, DictionaryGroup);
        return resultVO;
    }

    /**
     * 字典组新增
     * @param DictionaryGroup
     * @return
     */
    @PostMapping
    public ResultVO save(@Valid DictionaryGroup DictionaryGroup){
        boolean isSuccess = dictionaryGroupService.save(DictionaryGroup);
        if(!isSuccess){
            log.error("新增字典组失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

    /**
     * 字典组修改
     * @param DictionaryGroup
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public ResultVO update(@Valid DictionaryGroup DictionaryGroup){
        boolean isSuccess = dictionaryGroupService.updateById(DictionaryGroup);
        if(!isSuccess){
            log.warn("修改字典组失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

    /**
     * 字典组删除（逻辑）
     * @param id
     * @return
     */
    @DeleteMapping("/{id:\\d+}")
    public ResultVO delete(@PathVariable Long id){
        DictionaryGroup dictionaryGroup = new DictionaryGroup();
        dictionaryGroup.setId(id);
        dictionaryGroup.setIsDeleted(DeleteEnum.YES.getValue());
        boolean isSuccess = dictionaryGroupService.updateById(dictionaryGroup);
        if(!isSuccess){
            log.warn("修改字典组失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

}
