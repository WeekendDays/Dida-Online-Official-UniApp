package com.yang.emos.wx.db.dao;

import cn.hutool.core.lang.hash.Hash;
import com.yang.emos.wx.db.pojo.TbCheckin;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface TbCheckinDao {
    public Integer haveCheckin(Map<String, Object> map);
    public void insert(TbCheckin tbCheckin);
    public HashMap searchTodayCheckin(int userId);
    public long searchCheckinDays(int userId);
    public ArrayList<HashMap> searchWeekCheckin(HashMap param);
}