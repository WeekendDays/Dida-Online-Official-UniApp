package com.yang.emos.wx.service;

import com.yang.emos.wx.db.pojo.TbMeeting;

import java.util.ArrayList;
import java.util.HashMap;

public interface MeetingService {
    public void insertMeeting(TbMeeting entity);
    public ArrayList<HashMap> searchMyMeetingListByPage(HashMap param);
}
