package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by twogoods on 17/2/19.
 */
@Mapper
public interface UserRoleMapper {
    int addRoles(@Param("rids")String[] rids,@Param("uid")String uid);

    int removeRoles(@Param("autoids")String[] autoids);

    List<UserRole> getRoles(@Param("uid")String uid);
}
