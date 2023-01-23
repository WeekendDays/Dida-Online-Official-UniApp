package com.yang.emos.wx.db.dao;

import com.yang.emos.wx.db.pojo.TbCheckin;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface TbCheckinDao {
    public Integer haveCheckin(Map<String, Object> map);

}