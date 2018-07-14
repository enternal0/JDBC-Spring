package com.dan.jbdc.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: secondriver
 * Created: 2018/6/18
 */

@Component
public class DaoComponent {

    private final DataSource dataSource;

    @Autowired
    public DaoComponent(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    //加载驱动
    //创建连接
    //----- 命令
    //----- 参数
    //----- 结果集合
    //异常捕获
    //资源关闭

//    private final String driverClass;
//
//    private final String url;
//
//    public DaoComponent(String driverClass, String url) {
//        this.driverClass = driverClass;
//        this.url = url;
//        try {
//            Class.forName(this.driverClass);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 获取连接
     *
     * @return
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建预编译命令
     *
     * @param connection
     * @param sql
     * @return
     */
    public PreparedStatement getPreparedStatement(Connection connection, String sql) {
        if (connection == null) {
            return null;
        }
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * delete update insert
     *
     * @param statement
     * @return
     */
    public int executeUpdate(PreparedStatement statement) {
        if (statement == null) {
            return 0;
        }
        try {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * select
     *
     * @param statement
     * @return
     */
    public ResultSet executeQuery(PreparedStatement statement) {
        if (statement == null) {
            return null;
        }
        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 资源释放
     *
     * @param resultSet
     * @param statement
     * @param connection
     */
    public void destroyResource(ResultSet resultSet,
                                PreparedStatement statement,
                                Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

