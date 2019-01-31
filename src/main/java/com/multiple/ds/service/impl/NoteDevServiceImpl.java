package com.multiple.ds.service.impl;

import com.multiple.ds.entity.BaseNote;
import com.multiple.ds.service.NoteDevService;
import com.multiple.ds.service.base.BaseNoteService;
import org.springframework.stereotype.Service;

/**
 * @Auther: jinsheng.wei
 * @Date: 2018/11/22 11:13
 * @Description:
 */
@Service
public class NoteDevServiceImpl extends BaseNoteService implements NoteDevService {

    @Override
    public BaseNote queryDevByNoteId(long id) {
        return this.baseNoteMapper.queryByNoteId(id);
    }
}
