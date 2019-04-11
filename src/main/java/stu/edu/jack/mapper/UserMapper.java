package stu.edu.jack.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import stu.edu.jack.entity.User;

@Mapper
@Component
public interface UserMapper {

    User userLogin(@Param("idCardNum") String idCardNum, @Param("password") String password);

    int addUser(@Param("username") String username,
                @Param("password") String password,
                @Param("idCardNumber") String idCardNumber,
                @Param("realName") String realName,
                @Param("phoneNumber") String phoneNumber);

    User checkUsername(@Param("username") String username);

    User checkIDCard(@Param("idCard") String idCard);

    User checkPhoneNumber(@Param("phoneNumber") String phoneNumber);

    User getUserInfo(@Param("username") String username);
}