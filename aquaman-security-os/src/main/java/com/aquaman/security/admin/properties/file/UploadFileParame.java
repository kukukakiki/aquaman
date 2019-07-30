package com.aquaman.security.admin.properties.file;

import lombok.Data;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/7/30
 * @since 2019/7/30
 */
@Data
public class UploadFileParame {

    private String path;

    private int maxSize;
}
