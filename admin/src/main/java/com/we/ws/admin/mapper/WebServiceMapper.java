package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.ServiceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-05
 */
@Mapper
public interface WebServiceMapper {

    List<ServiceInfo> listService(@Param("sid")String sid,@Param("name")String name,@Param("pageSize")int pageSize,@Param("offset")int offset);

    int countListService(@Param("sid")String sid,@Param("name")String name);
}
