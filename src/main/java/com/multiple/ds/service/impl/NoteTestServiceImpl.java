package com.multiple.ds.service.impl;

import com.multiple.ds.entity.BaseNote;
import com.multiple.ds.service.NoteTestService;
import com.multiple.ds.service.base.BaseNoteService;
import org.springframework.stereotype.Service;


/**
 * @Auther: jinsheng.wei
 * @Date: 2018/11/22 11:17
 * @Description:
 */
@Service
public class NoteTestServiceImpl extends BaseNoteService implements NoteTestService {

    @Override
    public BaseNote queryTestByNoteId(long id) {
        return this.baseNoteMapper.queryByNoteId(id);
    }
}
