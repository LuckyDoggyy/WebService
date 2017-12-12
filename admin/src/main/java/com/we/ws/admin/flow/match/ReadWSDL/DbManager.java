package com.we.ws.admin.flow.match.ReadWSDL;

import com.mysql.jdbc.Driver;

import java.sql.*;

/**
 * Created by xuxyu on 2017/8/23.
 */
public class DbManager {

    public static Connection getConnection() throws SQLException{
        return getConnection("webservice","root","111111");
    }

    public static Connection getConnection(String dbName, String userName,
                  String password) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/" + dbName +
                "?userUnicode=true&characterEncoding=utf-8&useSSL=true";
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(url, userName, password);
    }

    public static void setParams(PreparedStatement preStmt, Object ... params)
                    throws SQLException{
        if(params == null || params.length == 0)
            return;
        for(int i =0 ; i < params.length ; i ++){
            Object param = params[i];
            if(param == null)
                preStmt.setNull(i + 1, Types.NULL);
            else if(param instanceof String)
                preStmt.setString(i + 1 , (String) param);
            else if(param instanceof Integer)
                preStmt.setInt(i + 1 , (Integer) param);
            else if(param instanceof Double)
                preStmt.setDouble(i + 1  , (Double) param);
            else if(param instanceof Long)
                preStmt.setLong(i + 1 , (Long) param);
        }
    }

    public static int executeUpdate(String sql) throws SQLException{
        return executeUpdate(sql, new Object[]{});
    }

    public static int executeUpdate(String sql, Object ... params)
            throws SQLException{
        Connection conn = null;
        PreparedStatement preStmt = null;
        try{
            conn = getConnection();
            preStmt = conn.prepareStatement(sql);
            setParams(preStmt,params);
            return preStmt.executeUpdate();
        }finally{
            if(preStmt != null) preStmt.close();
            if(conn != null) conn.close();
        }
    }
}
