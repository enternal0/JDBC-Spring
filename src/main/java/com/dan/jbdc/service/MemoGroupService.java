package com.dan.jbdc.service;

import com.dan.jbdc.dao.MemoGroupDao;
import com.dan.jbdc.entity.MemoGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataSource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;


/**
 * 业务逻辑层
 */
@Component
public class MemoGroupService{
    @Autowired
  private MemoGroupDao memoGroupDao;

    //根据id查询便签组
    public List<MemoGroup> queryMemoGroup(Integer id){
        return memoGroupDao.queryMemoGroup(id);
    }

    //插入一个便签组
    public int insertMemoGroup(MemoGroup memoGroup){
        return memoGroupDao.insetMemoGroup(memoGroup);
    }

    //修改便签组的名称
    public int updateMemoGroup(int id, String name){
        return memoGroupDao.updateMemoGroup(id,name);
    }

    //根据时间范围查询便签信息
    public List<MemoGroup> queryMemoGroupByCreatedTime(Date startTime, Date endTime){
        return memoGroupDao.queryMemoGroupByCreatedTime(startTime,endTime);
    }

    //根据Id删除便签组
    public int deleteMemoGroup(int id){
        return  memoGroupDao.deleteMemoGroup(id);
    }
}

