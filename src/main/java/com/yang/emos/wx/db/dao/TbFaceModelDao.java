package com.yang.emos.wx.db.dao;

import com.yang.emos.wx.db.pojo.TbFaceModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbFaceModelDao {
    public String searchFaceModel(int userId);
    public void insert(TbFaceModel faceModel);
    public int deleteFaceModel(int userId);
}