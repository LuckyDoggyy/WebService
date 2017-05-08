package com.we.ws.admin.mapper;

import com.we.ws.admin.domain.Service;
import com.we.ws.admin.domain.ServiceParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-05
 */
@Mapper
public interface WebServiceMapper {

    List<Service> listService(@Param("sid") String sid, @Param("servicename") String serviceName, @Param("pageSize") int pageSize, @Param("offset") int offset);

    int countListService(@Param("sid") String sid, @Param("servicename") String serviceName);

    @Select("select * from T_Service where sid=#{sid}")
    Service getServiceBySid(String sid);

    @Insert("insert into T_Service (servicename,remark, url, wsdlurl, targetnamespace, method,output, version) " +
            "values (#{serviceName},#{remark},#{url},#{wsdlUrl},#{targetNamespace},#{method},#{output},#{version})")
    int insertWS(Service service);

    int insertWSReturnKey(Service service);

    @Update("update T_Service set servicename=#{serviceName}, remark=#{remark},url=#{url}, wsdlurl=#{wsdlUrl}, " +
            "targetnamespace=#{targetNamespace}, method=#{method},output=#{output} where sid=#{sid}")
    int updateWS(Service service);

    int delete(@Param("sids") String[] sids);

    @Select("select * from T_ServiceParam where sid=#{sid} and state=0")
    List<ServiceParam> listParams(String sid);

    int batchInsertParam(@Param("params") List<ServiceParam> params, @Param("allsid") long sid);

    @Update("update T_ServiceParam set paramname=#{paramName},remark=#{remark} where autoid=#{autoid}")
    int updateParam(ServiceParam param);

    @Delete("delete from T_ServiceParam where sid=#{sid}")
    int deleteAllParamBuySid(long sid);

    @Update("update T_ServiceParam set state=1 where autoid=#{autoid}")
    int deleteParam(String autoid);

    @Select("select sid as value,remark as name from T_Service")
    List<Map<String, Object>> getWsOption();


}
