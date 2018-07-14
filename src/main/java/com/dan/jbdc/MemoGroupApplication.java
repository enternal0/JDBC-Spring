package com.dan.jbdc;

import com.dan.jbdc.entity.MemoGroup;
import com.dan.jbdc.service.MemoGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 *  * Author: secondriver
 *  * Created: 2018/6/23
 */
public class MemoGroupApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemoGroupApplication.class);

    public static void main(String[] args) {

        System.out.println("Hello JDBC");
        ApplicationContext context=new ClassPathXmlApplicationContext("application-context.xml");

        MemoGroupService memoGroupService=context.getBean(MemoGroupService.class);

        //根据id查询便签组
//        List<MemoGroup> memoGroups=memoGroupService.queryMemoGroup(4);
//
//        LOGGER.info("QueryMemoGroup Result {} . ",memoGroups);
//
//        //插入一个便签组
//        MemoGroup memoGroup= (MemoGroup) context.getBean("memoGroup");
//        //因为id是自增，故不能改,所以不受影响
//        memoGroup.setId(5);
//        memoGroup.setName("English");
//        memoGroup.setCreatedTime(new Date());
//        memoGroup.setModifyTime(new Date());
//
//        int effect=memoGroupService.insertMemoGroup(memoGroup);
//
//        LOGGER.info("insertMemoGroup Result {} .",effect);
//
//        //修改便签组的名称
//        int effect1=memoGroupService.updateMemoGroup(4,"Alea");
//
//        LOGGER.info("updateMemoGroup Result {} .",effect1 );

        //根据时间范围查询便签信息
        List<MemoGroup> memoGroups1=memoGroupService.queryMemoGroupByCreatedTime(new Date(2018-07-04) ,new Date(2018-07-12));

        LOGGER.info("queryMemoGroupByCreatedTime Result {} ." ,memoGroups1);

        //根据Id删除便签组
//        int effect2=memoGroupService.deleteMemoGroup(7);
//
//        LOGGER.info("memoGroupService Result {} ." ,effect2);
    }


}
