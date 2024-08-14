package com.edw.service;

import javax.annotation.Resource;
import javax.inject.Singleton;
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

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.transaction.TransactionMode;

/**
 * <pre>
 *  com.edw.service.CacheSQLService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 14 Aug 2024 13:15
 */
@Singleton
public class CacheSQLService {

    private DataSource ds = null;

    private Cache cache;

    public CacheSQLService () {
        try {
            Context initCxt =  new InitialContext();
            ds = (DataSource) initCxt.lookup("java:/test_dbDS");

            EmbeddedCacheManager embeddedCacheManager = new DefaultCacheManager();
            cache = embeddedCacheManager.createCache("tb_testing", new org.infinispan.configuration.cache.ConfigurationBuilder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Map> select() {

        List<Map> list = new ArrayList<Map>();

        if(!cache.isEmpty()) {
            list.addAll((List)cache.get("data"));
            return list;
        }

        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet resultSet = null;

        try {
            conn = ds.getConnection();

            String sql = "select * from tb_testing";

            preparedStmt = conn.prepareStatement(sql);
            resultSet = preparedStmt.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String username = resultSet.getString(2);

                list.add(new HashMap() {{
                    put("id", id);
                    put("username", username);
                }});

                // add this data to cache
                cache.put("data", list);
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
