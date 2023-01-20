package com.yang.emos.wx.db.dao;

import com.yang.emos.wx.db.pojo.TbUser;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Mapper
public interface TbUserDao {
    public boolean haveRootUser();

    public int insert(Map<String,Object> param);

    public Integer searchIdByOpenId(String openId);

    public Set<String> searchUserPermissions(int userId);
}