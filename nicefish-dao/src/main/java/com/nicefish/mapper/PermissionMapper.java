package com.nicefish.mapper;

import com.nicefish.gen.PermissionMapperGen;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by kimmking on 17/6/26.
 */

@Mapper
public interface PermissionMapper extends PermissionMapperGen {

    Set<String> findPermissions(@Param("list")List<String> roleNames);

}
