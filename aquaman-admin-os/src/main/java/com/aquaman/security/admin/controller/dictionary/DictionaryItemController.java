package com.aquaman.security.admin.controller.dictionary;


import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.domain.DictionaryItem;
import com.aquaman.security.admin.entity.query.DictionaryItemQuery;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.StatusEnum;
import com.aquaman.security.admin.service.IDictionaryItemService;
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
@RequestMapping("/dictionary_item")
@Slf4j
public class DictionaryItemController extends BaseController {
    
    @Autowired
    private IDictionaryItemService dictionaryItemService;

    /**
     * 获取字典项列表
     * @param DictionaryItemQuery
     * @return
     */
    @GetMapping
    public ResultVO<List<DictionaryItem>> getByPage(DictionaryItemQuery DictionaryItemQuery){
        IPage<DictionaryItem> page1 = dictionaryItemService.pageByQuery(DictionaryItemQuery);
        ResultVO<List<DictionaryItem>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, page1);
        return resultVO;
    }

    /**
     * 根据ID获取字典项详情
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public ResultVO getById(@PathVariable Long id){
        DictionaryItem DictionaryItem = dictionaryItemService.getById(id);
        ResultVO<DictionaryItem> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, DictionaryItem);
        return resultVO;
    }

    /**
     * 根据字典组ID获取字典项列表
     * @param query
     * @return
     */
    @GetMapping("/group_id/{groupId:\\d+}")
    public ResultVO<List<DictionaryItem>> getByGroupId(DictionaryItemQuery query){
        IPage<DictionaryItem> page1 = dictionaryItemService.findItemByGroupId(query).get();
        ResultVO<List<DictionaryItem>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, page1);
        return resultVO;
    }

    /**
     * 字典项新增
     * @param DictionaryItem
     * @return
     */
    @PostMapping
    public ResultVO save(@Valid DictionaryItem DictionaryItem){
        boolean isSuccess = dictionaryItemService.save(DictionaryItem);
        if(!isSuccess){
            log.error("新增字典项失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

    /**
     * 字典项修改
     * @param DictionaryItem
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public ResultVO update(@Valid DictionaryItem DictionaryItem){
        boolean isSuccess = dictionaryItemService.updateById(DictionaryItem);
        if(!isSuccess){
            log.warn("修改字典项失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

    /**
     * 字典项删除（逻辑）
     * @param id
     * @return
     */
    @DeleteMapping("/{id:\\d+}")
    public ResultVO delete(@PathVariable Long id){
        DictionaryItem DictionaryItem = new DictionaryItem();
        DictionaryItem.setId(id);
        DictionaryItem.setStatus(StatusEnum.STOP.getValue());
        boolean isSuccess = dictionaryItemService.updateById(DictionaryItem);
        if(!isSuccess){
            log.warn("修改字典项失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

}
