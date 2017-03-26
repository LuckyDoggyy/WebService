package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.Service;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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

    List<Service> listService(@Param("sid")String sid,@Param("servicename")String serviceName,@Param("pageSize")int pageSize,@Param("offset")int offset);

    int countListService(@Param("sid")String sid,@Param("servicename")String serviceName);

    @Insert("insert into T_Service (servicename,remark, url, wsdlurl, targetnamespace, method, version) " +
            "values (#{serviceName},#{remark},#{url},#{wsdlUrl},#{targetNamespace},#{method},#{version})")
    int insertWS(Service service);

    @Update("update T_Service set servicename=#{serviceName}, remark=#{remark},url=#{url}, wsdlurl=#{wsdlUrl}, " +
            "targetnamespace=#{targetNamespace}, method=#{method} where sid=#{sid}")
    int updateWS(Service service);

    int delete(@Param("sids")String[] sids);
}
