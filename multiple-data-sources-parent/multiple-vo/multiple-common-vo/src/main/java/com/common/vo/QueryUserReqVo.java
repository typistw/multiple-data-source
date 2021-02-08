package com.common.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/17 17:30
 * @Description:
 */
@Data
public class QueryUserReqVo {

    @NotNull
    private Integer page;
    private String mobile;
    private String nickName;

}
