package com.aquaman.security.admin.properties.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/7/30
 * @since 2019/7/30
 */
@ConfigurationProperties(prefix = "aquaman.file")
@Data
public class AquamanFileProperties {

    UploadParameter upload = new UploadParameter();
}
