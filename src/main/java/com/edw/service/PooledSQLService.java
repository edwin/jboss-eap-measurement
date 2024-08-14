package com.edw.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  com.edw.service.PooledSQLService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 14 Aug 2024 9:20
 */

public class PooledSQLService {

    private DataSource ds = null;

    public PooledSQLService() {
        try {
            Context initCxt = new InitialContext();
            ds = (DataSource) initCxt.lookup("java:/test_dbDS");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Map> select() {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet resultSet = null;

        List<Map> list = new ArrayList<Map>();

        for (int i = 1; i <= 500; i++) {
            try {
                conn = ds.getConnection();

                String sql = "select * from tb_testing where id = ?";

                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setInt(1, i);
                resultSet = preparedStmt.executeQuery();

                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    String username = resultSet.getString(2);

                    list.add(new HashMap() {{
                        put("id", id);
                        put("username", username);
                    }});
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (preparedStmt != null) {
                    try {
                        preparedStmt.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

        return list;
    }

}
