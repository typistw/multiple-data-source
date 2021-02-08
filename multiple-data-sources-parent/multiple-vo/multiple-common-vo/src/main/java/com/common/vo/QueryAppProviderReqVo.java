package com.common.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/2 15:39
 * @Description:
 */
@Data
public class QueryAppProviderReqVo {

    @NotBlank
    String appId;
}
