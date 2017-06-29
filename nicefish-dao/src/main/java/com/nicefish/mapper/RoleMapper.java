package com.nicefish.mapper;

import com.nicefish.gen.RoleMapperGen;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * Created by kimmking on 17/6/26.
 */

@Mapper
public interface RoleMapper extends RoleMapperGen {

   Set<String> findRoleNamesForUserName(@Param("userName")String userName);

}
