package com.yang.emos.wx.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.yang.emos.wx.db.dao.TbMeetingDao;
import com.yang.emos.wx.db.dao.TbUserDao;
import com.yang.emos.wx.db.pojo.TbMeeting;
import com.yang.emos.wx.exception.EmosException;
import com.yang.emos.wx.service.MeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@Slf4j
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private TbMeetingDao meetingDao;
    @Autowired
    private TbUserDao userDao;
    @Value("${emos.code}")
    private String code;
    @Value("${workflow.url}")
    private String workflow;
    @Value("${emos.receiveNotify}")
    private String receiveNotify;
    @Override
    public void insertMeeting(TbMeeting entity) {
        int row = meetingDao.insertMeeting(entity);
        if(row != 1){
            throw new EmosException("会议添加失败");
        }
        //开启审批工作流
        //startMeetingWorkflow(entity.getUuid(), entity.getCreatorId().intValue(), entity.getDate(), entity.getStart());
    }

    @Override
    public ArrayList<HashMap> searchMyMeetingListByPage(HashMap param) {
        ArrayList<HashMap> list = meetingDao.searchMyMeetingListByPage(param);
        String date = null;
        ArrayList resultList = new ArrayList();
        HashMap resultMap = null;
        JSONArray array = null;
        for(HashMap map : list){
            String temp = map.get("date").toString();
            if(!temp.equals(date)){
                //这里后面打断点调试一下
                date = temp;
                resultMap = new HashMap();
                resultMap.put("date", date);
                array = new JSONArray();
                resultMap.put("list", array);
                resultList.add(resultMap);
            }
            array.put(map);
        }
        return resultList;
    }
    private void startMeetingWorkflow(String uuid, int creatorId, String date, String start){
        HashMap info = userDao.searchUserInfo(creatorId);
        JSONObject json = new JSONObject();
        json.set("url", receiveNotify);
        json.set("uuid", uuid);
        json.set("openId", info.get("openId"));
        json.set("code", code);
        json.set("date", date);
        json.set("start", start);
        String[] roles = info.get("roles").toString().split("，");
        if(!ArrayUtil.contains(roles, "总经理")){
            Integer managerId = userDao.searchDeptManagerId(creatorId);
            json.set("managerId", managerId);
            Integer gmId = userDao.searchGmId();
            json.set("gmId", gmId);
            boolean bool = meetingDao.searchMeetingMembersInSameDept(uuid);
            json.set("sameDept", bool);
        }
        String url = workflow + "/workflow/startMeetingProcess";
//        HttpResponse resp = HttpRequest.post(url).header("Content-Type", "application/json")
//                .body(json.toString()).execute();
//        if(resp.getStatus() == 200){
//            json = JSONUtil.parseObj(resp.body());
//            String instanceId = json.getStr("instanceId");
//            HashMap param = new HashMap();
//            param.put("uuid", uuid);
//            param.put("instanceId", instanceId);
//            int row = tbMeetingDao.updateMeetingInstanceId(param);
//            if(row != 1){
//                throw new EmosException("保存会议工作流实例ID失败");
//            }
//        }
    }

}
