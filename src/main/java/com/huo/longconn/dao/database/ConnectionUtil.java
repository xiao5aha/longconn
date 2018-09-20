package com.huo.longconn.dao.database;

import com.huo.longconn.utils.ConfigUtil;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {
    private static DataSource dataSource ;
    static {
        init();
    }

    public static Connection getConnection() throws SQLException {
        if(dataSource == null) {
            synchronized (ConnectionUtil.class) {
                if(dataSource == null) {
                    init();
                }
            }
        }
        return dataSource.getConnection() ;
    }

    private static void init() {
        String driver = ConfigUtil.getString("mysql.driver", "com.mysql.jdbc.Driver");
        String url = ConfigUtil.getString("mysql.url");
        String userName = ConfigUtil.getString("mysql.userName");
        String password = ConfigUtil.getString("mysql.password") ;
        int maxIdle = ConfigUtil.getInt("mysql.maxIdle",20);
        int minIdle = ConfigUtil.getInt("mysql.minIdle",20);
        int initialSize = ConfigUtil.getInt("mysql.initialSize",20);
        int maxActive = ConfigUtil.getInt("mysql.maxActive",20);
        PoolProperties p = new PoolProperties();
        p.setUrl(url);
        p.setDriverClassName(driver);
        p.setUsername(userName);
        p.setPassword(password);
        p.setMaxIdle(maxIdle);
        p.setMinIdle(minIdle);
        p.setInitialSize(initialSize);
        p.setMaxActive(maxActive);
        dataSource = new DataSource(p);
    }
}