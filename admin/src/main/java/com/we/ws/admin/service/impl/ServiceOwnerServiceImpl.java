package com.we.ws.admin.service.impl;

import com.we.ws.admin.domain.ServiceOwner;
import com.we.ws.admin.mapper.ServiceOwnerMapper;
import com.we.ws.admin.service.ServiceOwnerService;
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
public class ServiceOwnerServiceImpl implements ServiceOwnerService{

    @Autowired
    private ServiceOwnerMapper serviceOwnerMapper;
    @Override
    public List<ServiceOwner> listServerByOwner(String uid) {
        return serviceOwnerMapper.listServiceByowner(uid);
    }

    @Override
    public boolean deleteForUser(String uid, String sids) {
        serviceOwnerMapper.deleteServiceForUser(uid,sids.split(","));
        return true;
    }

    @Override
    public boolean addServiceForUser(String uid, String sids) {
        String[] ids=sids.split(",");
        for(String id:ids){
            if(serviceOwnerMapper.checkExist(uid,id)<1){
                serviceOwnerMapper.insertForUser(uid,id);
            }
        }
        return true;
    }
}
