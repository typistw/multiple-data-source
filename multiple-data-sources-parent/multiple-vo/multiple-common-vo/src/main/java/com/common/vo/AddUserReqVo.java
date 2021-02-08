package com.common.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/3 17:51
 * @Description:
 */
@Data
public class AddUserReqVo {

    @NotBlank
    private String mobile;
}
