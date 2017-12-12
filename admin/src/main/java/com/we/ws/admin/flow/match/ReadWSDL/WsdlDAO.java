package com.we.ws.admin.flow.match.ReadWSDL;

import com.we.ws.admin.flow.match.ServiceGraph.ServiceNode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by xuxyu on 2017/8/23.
 */
public class WsdlDAO {

    GetWsdls gw;

    public WsdlDAO(String url){
        gw = new GetWsdls(url);
    }

    public void readWsdlsToDb() throws Exception{
        WsdlBean wb;
        ArrayList<String> wsdls = gw.getWsdlsList();
        for(int i = 0 ; i < wsdls.size() ; i ++){
            wb = new WsdlBean(wsdls.get(i));
            insertWsdl(wb);
        }
    }

    public static void insertParams(String url, ArrayList<String> params, String table)
        throws Exception{

        int serviceId = searchSidByUrl(url);
        for(int i = 0 ; i < params.size() ; i ++){
            String input = params.get(i);
            String sql = "insert into " + table + " (sid, paramname, state) values" +
                "(?, ?, 0)";
            DbManager.executeUpdate(sql, serviceId, input);
        }
    }







    public static int insertWsdl(WsdlBean wb) throws Exception{
        String sql = "insert into t_service (servicename, remark, wsdlurl, targetnamespace, method, version, state) " +
                "values (?, ?, ?, ?, ?, 2, 0)";
        return DbManager.executeUpdate(sql, wb.getServiceName(), wb.getRemark(), wb.getWsdlUrl(), wb.getTargetNS(),
                    wb.method);
    }

/*    public static int insertParams(WsdlBean wb) throws Exception{
        String sql = "insert into t_serviceparam (sid, paramname, state) values (?, ?, 0)";
        return DbManager.executeUpdate(sql, searchServiceId(wb), );
    }*/

    public static int searchSidByUrl(String url) throws Exception {
        String sql = "select * from t_service where wsdlurl = ?";
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        try {
            conn = DbManager.getConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, url);
            rs = preStmt.executeQuery();
            if(rs.next()){
                return rs.getInt("sid");
            } else{
                return -1;
            }
        } finally {
            if(rs != null)
                rs.close();
            if(preStmt != null)
                preStmt.close();
            if(conn != null)
                conn.close();
        }
    }



}
