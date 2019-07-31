package com.aquaman.security.admin.mapper;

import com.aquaman.security.admin.entity.domain.UploadFileInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wei wang
 * @since 2019-07-31
 */
public interface UploadFileInfoMapper extends BaseMapper<UploadFileInfo> {

    /**
     * 插入数据并返回ID
     * @param uploadFileInfo
     * @return
     */
    Long insertAndGetId(UploadFileInfo uploadFileInfo);

}
