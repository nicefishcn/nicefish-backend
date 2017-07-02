package com.nicefish.mapper;

import com.nicefish.gen.UserMapperGen;
import com.nicefish.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kimmking on 17/6/26.
 */

@Mapper
public interface UserMapper extends UserMapperGen {

   User findByUserNameAndPassword(@Param("userName")String userName,@Param("password")String password);

   User findByUserName(@Param("userName")String userName);

    List<User> findAll();
}
