package com.we.ws.common.util;

/**
 * Created by twogoods on 16/10/15.
 */
public class CommonUtil {

    public static int calOffset(int pageNo,int pageSize){
        int offset=(pageNo-1)*pageSize;
        return offset>0?offset:0;
    }

}
