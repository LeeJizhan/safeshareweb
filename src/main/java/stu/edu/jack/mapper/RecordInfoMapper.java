package stu.edu.jack.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import stu.edu.jack.entity.RecordInfo;

import java.util.List;

@Mapper
@Component
public interface RecordInfoMapper {

    List<RecordInfo> getPaperNameAndTime(@Param("idCardNum") String idCardNum);

}