package stu.edu.jack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import stu.edu.jack.entity.RecordInfo;
import stu.edu.jack.service.RecordService;

import java.util.List;

/**
 * Created by Asus- on 2019/4/9.
 */
@Controller
@RequestMapping(value = {"/record"})
public class RecordController {

    @Autowired
    private RecordService recordService;

    @ResponseBody
    @RequestMapping(value = {"/easyInfo"}, method = RequestMethod.GET)
    public List<RecordInfo> getPaperNameAndTime(@RequestParam("idCardNum") String idCardNum) {
        List<RecordInfo> recordInfoList = recordService.getPaperNameAndTime(idCardNum);
        return recordInfoList;
    }
}
