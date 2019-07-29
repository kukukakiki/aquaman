package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.Log;
import com.aquaman.security.admin.mapper.LogMapper;
import com.aquaman.security.admin.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wei wang
 * @since 2019-07-28
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
