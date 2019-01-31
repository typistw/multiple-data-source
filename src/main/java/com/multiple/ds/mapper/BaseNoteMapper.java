package com.multiple.ds.mapper;

import com.multiple.ds.entity.BaseNote;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: jinsheng.wei
 * @Date: 2018/11/22 9:33
 * @Description:
 */
public interface BaseNoteMapper {

    int insert(BaseNote baseNote);

    BaseNote queryByNoteId(@Param("noteId") long noteId);
}
