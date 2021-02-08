package com.dynamic.note;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multiple.ds.annotation.SelectDataSource;
import com.multiple.ds.constant.DataSourceConstant;
import com.multiple.ds.entity.BaseNote;
import com.multiple.ds.service.NoteDevService;
import com.multiple.ds.service.NoteTestService;
import com.multiple.ds.service.impl.NoteDevServiceImpl;
import com.multiple.ds.service.impl.NoteTestServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 *  无效
 * @Auther: jinsheng.wei
 * @Date: 2018/11/22 11:19
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBaseNote {

    private ObjectMapper objectMapper = new ObjectMapper();

    BaseNote note;

    @Autowired
    private NoteDevServiceImpl noteDevService;

    @Autowired
    private NoteTestServiceImpl noteTestService;


    @Before
    public void init(){
        note = new BaseNote();
        note.setProviderId(111);
        note.setContent("dynamic datasource storage");
        note.setSendTime(System.currentTimeMillis());
    }

    @Test
    @SelectDataSource(DataSourceConstant.DS_DEV)
    public void devQueryAndInsert() throws JsonProcessingException {
        note.setSubject("dev");
        long devNoteId = 9;

        Assert.assertEquals(1,noteDevService.baseInsert(note));
        BaseNote devNote = noteDevService.queryDevByNoteId(devNoteId);
        System.out.println(objectMapper.writeValueAsString(devNote));
    }



    @Test
    @SelectDataSource(DataSourceConstant.DS_TEST)
    public void testQueryAndInsert() throws IOException {
        note.setSubject("simple");

        long testNodeId = 22;
        Assert.assertEquals(1,noteTestService.baseInsert(note));
        BaseNote testNote = noteTestService.queryTestByNoteId(testNodeId);
        System.out.println(objectMapper.writeValueAsString(testNote));
    }

}
