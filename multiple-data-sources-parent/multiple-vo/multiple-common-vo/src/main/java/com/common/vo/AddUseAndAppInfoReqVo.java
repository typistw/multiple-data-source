package com.common.vo;

import lombok.Data;

import javax.validation.Valid;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/3 18:07
 * @Description:
 */
@Data
public class AddUseAndAppInfoReqVo {

    @Valid
    private AddUserReqVo addUserReqVo;
    @Valid
    private AddAppInfoReqVo addAppInfoReqVo;

}
