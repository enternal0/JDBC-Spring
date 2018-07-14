package com.dan.jbdc.dao.impl;

import com.dan.jbdc.dao.DaoComponent;
import com.dan.jbdc.entity.MemoGroup;
import com.dan.jbdc.dao.MemoGroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: secondriver
 * Created: 2018/6/18
 */
@Repository
public class MemoGroupDaoImpl implements MemoGroupDao {

    private final DaoComponent daoComponent;

    @Autowired
    public MemoGroupDaoImpl(DaoComponent daoComponent) {
        this.daoComponent = daoComponent;
    }

    @Override
    public int insetMemoGroup(MemoGroup memoGroup) {
        if (memoGroup == null || memoGroup.getName() == null || memoGroup.getCreatedTime() == null) {
            return 0;
        }
        Connection connection = this.daoComponent.getConnection();
        String sql = "insert into memo_group (name,created_time) values (?,?)";
        PreparedStatement statement = this.daoComponent.getPreparedStatement(connection, sql);
        if (statement == null) {
            return 0;
        }
        try {
            statement.setString(1, memoGroup.getName());
            statement.setDate(2,
                    new java.sql.Date(memoGroup.getCreatedTime().getTime()
                    ));

            return this.daoComponent.executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.daoComponent.destroyResource(null, statement, connection);
        }
        return 0;
    }

    @Override
    public int updateMemoGroup(int id, String name) {
        if (name == null) {
            return 0;
        }
        Connection connection = this.daoComponent.getConnection();
        String sql = "update memo_group set name=? where id=?";
        PreparedStatement statement = this.daoComponent.getPreparedStatement(connection, sql);
        try {
            statement.setString(1, name);
            statement.setInt(2, id);

            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            this.daoComponent.destroyResource(null, statement, connection);
        }

        return 0;
    }

    @Override
    public List<MemoGroup> queryMemoGroup(int id) {
        List<MemoGroup> memoGroups = new ArrayList<>();
        Connection connection = this.daoComponent.getConnection();
        String sql = "select id, name,created_time,modify_time from memo_group where id =? ";
        PreparedStatement statement = this.daoComponent.getPreparedStatement(connection, sql);
        ResultSet resultSet = null;
        try {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            memoGroups = this.handleResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.daoComponent.destroyResource(resultSet, statement, connection);
        }
        return memoGroups;
    }

    @Override
    public List<MemoGroup> queryMemoGroupByCreatedTime(Date startTime, Date endTime) {
        List<MemoGroup> memoGroups = new ArrayList<>();
        Connection connection = this.daoComponent.getConnection();
        String sql = "select id, name,created_time,modify_time from memo_group where created_time between ? and ? ";
        PreparedStatement statement = this.daoComponent.getPreparedStatement(connection, sql);
        ResultSet resultSet = null;
        try {
            statement.setDate(1, new java.sql.Date(startTime.getTime()));

            statement.setDate(2, new java.sql.Date(endTime.getTime()));
            resultSet = statement.executeQuery();
            memoGroups = this.handleResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.daoComponent.destroyResource(resultSet, statement, connection);
        }
        return memoGroups;
    }

    @Override
    public int deleteMemoGroup(int id) {
        Connection connection = this.daoComponent.getConnection();
        String sql = "delete from  memo_group  where id=?";
        PreparedStatement statement = this.daoComponent.getPreparedStatement(connection, sql);
        try {
            statement.setInt(1, id);
            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            this.daoComponent.destroyResource(null, statement, connection);
        }
        return 0;
    }


    private List<MemoGroup> handleResultSet(ResultSet resultSet) throws SQLException {
        List<MemoGroup> memoGroups = new ArrayList<>();
        if (resultSet != null) {

            while (resultSet.next()) {
                MemoGroup memoGroup = new MemoGroup();
                memoGroup.setId(resultSet.getInt("id"));
                memoGroup.setName(resultSet.getString("name"));
                memoGroup.setCreatedTime(resultSet.getDate("created_time"));
                memoGroup.setModifyTime(resultSet.getTimestamp("modify_time"));
                memoGroups.add(memoGroup);
            }
        }
        return memoGroups;
    }
}
