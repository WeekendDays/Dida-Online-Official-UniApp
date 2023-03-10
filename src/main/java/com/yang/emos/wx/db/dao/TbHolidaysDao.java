package com.yang.emos.wx.db.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface TbHolidaysDao {
    public Integer searchTodayIsHoliday();

    public ArrayList<String> searchHolidaysInRange(HashMap param);
}