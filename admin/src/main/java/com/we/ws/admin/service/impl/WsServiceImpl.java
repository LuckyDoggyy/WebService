package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.Service;
import com.we.ws.admin.domain.ServiceParam;
import com.we.ws.admin.mapper.WebServiceMapper;
import com.we.ws.admin.service.WsService;
import com.we.ws.common.data.Pair;
import com.we.ws.common.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-05
 */
@Component
public class WsServiceImpl implements WsService {

    @Autowired
    private WebServiceMapper webServiceMapper;

    @Override
    public List<Service> listService(String sid, String name, int pageSize, int offset) {
        return webServiceMapper.listService(sid, "%" + name + "%", pageSize, offset);
    }

    @Override
    public int countListService(String sid, String name) {
        //TODO like
        return webServiceMapper.countListService(sid, name);
    }

    @Override
    public boolean addNewWS(Service service, String serviceParams) {
        if (!StringUtils.isEmpty(serviceParams)) {
            List<ServiceParam> list = JsonUtils.listFromJson(serviceParams, ServiceParam.class);
            if (list.size() > 0) {
                webServiceMapper.insertWSReturnKey(service);
                webServiceMapper.batchInsertParam(list, service.getSid());
                return true;
            }
        }
        webServiceMapper.insertWS(service);
        return true;
    }

    @Override
    public boolean updateWS(Service service, String serviceParams) {
        if (!StringUtils.isEmpty(serviceParams)) {
            List<ServiceParam> list = JsonUtils.listFromJson(serviceParams, ServiceParam.class);
            if (list.size() > 0) {
                webServiceMapper.deleteAllParamBuySid(service.getSid());
                webServiceMapper.batchInsertParam(list, service.getSid());
            }
        }
        return webServiceMapper.updateWS(service) == 1;
    }

    @Override
    public void deleteWS(String sids) {
        webServiceMapper.delete(sids.split(","));
    }

    @Override
    public List<ServiceParam> listParams(String sid) {
        return webServiceMapper.listParams(sid);
    }

    @Override
    public int batchInsertParam(List<ServiceParam> params, long sid) {
        return webServiceMapper.batchInsertParam(params, sid);
    }

    @Override
    public int updateParam(ServiceParam param) {
        return webServiceMapper.updateParam(param);
    }

    @Override
    public int deleteParam(String autoid) {
        return webServiceMapper.deleteParam(autoid);
    }
}
