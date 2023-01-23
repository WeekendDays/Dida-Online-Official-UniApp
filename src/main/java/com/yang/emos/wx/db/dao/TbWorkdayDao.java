package com.yang.emos.wx.db.dao;

import com.yang.emos.wx.db.pojo.TbWorkday;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbWorkdayDao {
    public Integer searchTodayIsWorkday();
}