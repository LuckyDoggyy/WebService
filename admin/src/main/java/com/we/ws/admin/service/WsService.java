package com.we.ws.admin.service;

import com.we.ws.admin.domain.Service;

import java.util.List;

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

    boolean addNewWS(Service service);

    boolean updateWS(Service service);

    void deleteWS(String sids);
}
