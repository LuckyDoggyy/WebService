package com.we.ws.admin.service;

import com.we.ws.admin.domain.ServiceOwner;

import java.util.List;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-02-05
 */
public interface ServiceOwnerService {
    List<ServiceOwner> listServerByOwner(String uid);

    boolean deleteForUser(String uid,String sids) throws Exception;

    boolean addServiceForUser(String uid,String sids) throws Exception;
}
