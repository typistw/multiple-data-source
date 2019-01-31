package com.multiple.ds.controller;

import com.multiple.ds.annotation.SelectDataSource;
import com.multiple.ds.constant.DataSourceConstant;
import com.multiple.ds.entity.BaseNote;
import com.multiple.ds.service.impl.NoteDevServiceImpl;
import com.multiple.ds.service.impl.NoteTestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: jinsheng.wei
 * @Date: 2018/11/22 17:50
 * @Description:
 */
@RestController
@RequestMapping(value = "/note")
@Slf4j
public class BaseController {

    private static BaseNote note;
    static {
        note = new BaseNote();
    }

    @Autowired
    private NoteTestServiceImpl noteTestService;

    @Autowired
    private NoteDevServiceImpl noteDevService;

    @RequestMapping(value = "/dev")
    @SelectDataSource(DataSourceConstant.DS_DEV)
    public String devInsertAndQuery(){

        long devNoteId = 9;
        note.setProviderId(111);
        note.setContent("dev dynamic datasource storage");
        note.setSubject("dev");
        note.setSendTime(System.currentTimeMillis());
        int result= noteDevService.baseInsert(note);

        BaseNote note = noteDevService.queryDevByNoteId(devNoteId);
        log.info("dev query result: {}", note);

        return String.valueOf(result);
    }

    @RequestMapping(value = "/test")
    @SelectDataSource(DataSourceConstant.DS_TEST)
    public String testInsertAndQuery(){

        long devNoteId = 22;
        note.setProviderId(111);
        note.setContent("simple dynamic datasource storage");
        note.setSubject("simple");
        note.setSendTime(System.currentTimeMillis());
        int result= noteTestService.baseInsert(note);

        BaseNote note = noteTestService.queryTestByNoteId(devNoteId);
        log.info("simple query result: {}", note);

        return String.valueOf(result);
    }
}
