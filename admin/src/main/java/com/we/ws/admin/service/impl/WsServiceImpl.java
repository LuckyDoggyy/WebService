package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.ServiceInfo;
import com.we.ws.admin.mapper.WebServiceMapper;
import com.we.ws.admin.service.WsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-05
 */
@Component
public class WsServiceImpl implements WsService{

    @Autowired
    private WebServiceMapper webServiceMapper;

    @Override
    public List<ServiceInfo> listService(String sid, String name, int pageSize, int offset) {
        return webServiceMapper.listService(sid,name,pageSize,offset);
    }

    @Override
    public int countListService(String sid, String name) {
        return webServiceMapper.countListService(sid,name);
    }
}
