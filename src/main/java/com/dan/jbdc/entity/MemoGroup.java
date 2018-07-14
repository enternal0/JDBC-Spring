package com.dan.jbdc.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Author: secondriver
 * Created: 2018/6/18
 */

@Data
@Component
public class MemoGroup {


    private Integer id;

    private String name;

    private Date createdTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

