package com.nicefish.mapper;

import com.nicefish.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by kimmking on 17/6/26.
 */

@Mapper
public interface RoleMapper extends com.nicefish.gen.RoleMapper{

   Set<String> findRoleNamesForUserName(@Param("userName")String userName);

}
