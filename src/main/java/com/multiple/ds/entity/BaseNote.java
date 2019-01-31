package com.multiple.ds.entity;

import lombok.Data;

/**
 * @Auther: jinsheng.wei
 * @Date: 2018/11/21 18:26
 * @Description:
 */
@Data
public class BaseNote {
    private Long noteId;
    private Integer providerId;
    private String subject;
    private String content;
    private Long sendTime;
}
