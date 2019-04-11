package stu.edu.jack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stu.edu.jack.entity.RecordInfo;
import stu.edu.jack.mapper.RecordInfoMapper;

import java.util.List;

/**
 * Created by Asus- on 2019/4/9.
 */
@Service
public class RecordService {

    @Autowired
    private RecordInfoMapper recordInfoMapper;

    public List<RecordInfo> getPaperNameAndTime(String idCardNum){
        List<RecordInfo> recordInfoList = recordInfoMapper.getPaperNameAndTime(idCardNum);
        return recordInfoList;
    }

}
