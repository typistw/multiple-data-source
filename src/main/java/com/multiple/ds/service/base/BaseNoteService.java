package com.multiple.ds.service.base;

import com.multiple.ds.entity.BaseNote;
import com.multiple.ds.mapper.BaseNoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: jinsheng.wei
 * @Date: 2018/11/22 9:58
 * @Description:
 */
@Service
public class BaseNoteService {

    @Autowired
    protected BaseNoteMapper baseNoteMapper;

    public int baseInsert(BaseNote baseNote) {
        return baseNoteMapper.insert(baseNote);
    }
}
