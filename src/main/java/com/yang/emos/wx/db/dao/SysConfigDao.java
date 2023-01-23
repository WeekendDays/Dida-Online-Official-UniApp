package com.yang.emos.wx.db.dao;

import com.yang.emos.wx.db.pojo.SysConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysConfigDao {
    public List<SysConfig> selectAllParam();
}