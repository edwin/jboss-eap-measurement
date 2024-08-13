package com.edw.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * <pre>
 *  com.edw.service.DirectSQLService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 13 Aug 2024 14:59
 */
public class DirectSQLService {

    public void initiateData() {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        try {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/test_db";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myUrl, "root", "password");

            for (int i = 0; i < 500; i++) {
                String uuid = UUID.randomUUID().toString();
                String sql = "insert into tb_testing (id, username) " +
                        "values (null, ?)";

                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString (1, uuid);

                preparedStmt.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if(preparedStmt != null) {
                try {
                    preparedStmt.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public List<Map> select() {
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet resultSet = null;

        List<Map> list = new ArrayList<Map>();

        try {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/test_db";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myUrl, "root", "password");

            String sql = "select * from tb_testing";

            preparedStmt = conn.prepareStatement(sql);
            resultSet = preparedStmt.executeQuery();

            while (resultSet.next()){
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
            if(conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if(preparedStmt != null) {
                try {
                    preparedStmt.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return list;
    }

}
