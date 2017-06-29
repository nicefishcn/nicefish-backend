package com.nicefish.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by kimmking on 17/6/26.
 */

@Mapper
public interface PermissionMapper extends com.nicefish.gen.PermissionMapper{

    Set<String> findPermissions(@Param("rolesNames")List<String> roleNames);

}
