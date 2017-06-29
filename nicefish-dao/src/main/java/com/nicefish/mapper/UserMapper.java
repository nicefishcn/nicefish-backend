package com.nicefish.mapper;

import com.nicefish.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by kimmking on 17/6/26.
 */

@Mapper
public interface UserMapper extends com.nicefish.gen.UserMapper{

   User findByUserNameAndPassword(@Param("userName")String userName,@Param("password")String password);

   User findByUserName(@Param("userName")String userName);

}
