package com.dan.jbdc.dao;

import com.dan.jbdc.entity.MemoGroup;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Author: secondriver
 * Created: 2018/6/18
 */
public interface MemoGroupDao {

    /**
     * 插入一个便签组
     *
     * @param memoGroup
     * @return
     */
    int insetMemoGroup(MemoGroup memoGroup);

    /**
     * 修改便签组的名称
     *
     * @param id
     * @param name
     * @return
     */
    int updateMemoGroup(int id, String name);

    /**
     * 根据Id查询便签组信息
     *
     * @param id
     * @return
     */
    List<MemoGroup> queryMemoGroup(int id);

    /**
     * 根据时间范围查询便签信息
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<MemoGroup> queryMemoGroupByCreatedTime(Date startTime, Date endTime);

    /**
     * 根据Id删除便签组
     *
     * @param id
     * @return
     */
    int deleteMemoGroup(int id);
}


