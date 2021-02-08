package com.common.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auther: jinsheng.wei
 * @Date: 2020/7/2 16:19
 * @Description:
 */
@Data
public class QueryUserByMobileReqVo {

    @Valid
    private List<Mobile> mobileList;

    @Data
    public class Mobile{
        @NotBlank
        private String mobile;
    }

}
