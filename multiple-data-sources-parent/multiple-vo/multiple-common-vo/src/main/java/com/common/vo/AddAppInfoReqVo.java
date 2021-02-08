package com.common.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/2 16:54
 * @Description:
 */
@Data
public class AddAppInfoReqVo {

    @NotBlank
    private String appId;
    @NotBlank
    private String appKey;
}
