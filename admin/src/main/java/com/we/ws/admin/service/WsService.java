package com.we.ws.admin.service;

import com.we.ws.admin.domain.Service;
import com.we.ws.admin.domain.ServiceParam;
import com.we.ws.common.data.Pair;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-05
 */
public interface WsService {
    List<Service> listService(String sid, String name, int pageSize, int offset);

    int countListService(String sid, String name);

    boolean addNewWS(Service service, String serviceParams);

    boolean updateWS(Service service, String serviceParams);

    void deleteWS(String sids);

    List<ServiceParam> listParams(String sid);

    int batchInsertParam(List<ServiceParam> params, long sid);

    int updateParam(ServiceParam param);

    int deleteParam(String autoid);

    List<Map<String,Object>> getWsOption();

}
