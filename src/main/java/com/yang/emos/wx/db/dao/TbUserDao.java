package com.yang.emos.wx.db.dao;

import cn.hutool.core.lang.hash.Hash;
import com.yang.emos.wx.db.pojo.TbUser;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface TbUserDao {
    public boolean haveRootUser();

    public int insert(Map<String,Object> param);

    public Integer searchIdByOpenId(String openId);

    public Set<String> searchUserPermissions(int userId);

    public TbUser searchById(int userId);

    public HashMap searchNameAndDept(int userId);

    public String searchUserHiredate(int userId);

    public HashMap searchUserSummary(int userId);

    public ArrayList<HashMap> searchUserGroupByDept(String keyword);

    public ArrayList<HashMap> searchMembers(List param);

    public HashMap searchUserInfo(int userId);

    public int searchDeptManagerId(int id);

    public int searchGmId();

    public List<HashMap> selectUserPhotoAndName(List param);

    public String searchMemberEmail(int id);
}