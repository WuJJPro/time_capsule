package com.twt.time_capsule.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.twt.time_capsule.entity.*;
import com.twt.time_capsule.mapper.*;
import com.twt.time_capsule.service.CapsulePoolService;
import com.twt.time_capsule.service.PublicCapsuleService;
import com.twt.time_capsule.utils.APIResponse;
import com.twt.time_capsule.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PublicCapsuleServiceImpl implements PublicCapsuleService {
    private static final int ACTION_LOVE = 1;
    private static final int ACTION_LOVE_CANCEL = 0;
    @Autowired
    PublicCapsuleMapper capsuleMapper;
    @Autowired
    CapsulePoolMapper poolMapper;
    @Autowired
    PublicCapsuleDeletedMapper capsuleDeletedMapper;
    @Autowired
    LoveMapper loveMapper;
    @Autowired
    ReportMapper reportMapper;
    @Override
    public APIResponse addPublicCapsule(PublicCapsule capsule) {
        //判断poolid是否合法
        String uid = StpUtil.getLoginIdAsString();
        String poolId = capsule.getPoolId();
        CapsulePool pool = poolMapper.selectById(poolId);
        if(pool==null){
            return APIResponse.error(ErrorCode.POOL_UN_EXIST);
        }
        //判断这次活动是否已经发表过
        PublicCapsule oriCapsule = capsuleMapper.getCapsule(uid,poolId);
        if(oriCapsule!=null){
            return APIResponse.error(ErrorCode.CAPSULE_EXIST);
        }
        //判断是否超过200字
        if(capsule.getContent().length()>200){
            return APIResponse.error(ErrorCode.WORDS_MAX);
        }

        capsule.setLikeNumber(0);
        capsule.setUid(uid);
        capsuleMapper.insert(capsule);
        return APIResponse.success();
    }

    @Override
    public APIResponse alterPublicCapsule(PublicCapsule capsule) {
        PublicCapsule ori_capsule = capsuleMapper.selectById(capsule.getId());
        boolean flag = false;
        if(capsule.getContent()!=null){
            if(capsule.getContent().length()>200){
                return APIResponse.error(ErrorCode.WORDS_MAX);
            }
            ori_capsule.setContent(capsule.getContent());
            flag = true;
        }
        if(capsule.getMood()!=null){
            ori_capsule.setMood(capsule.getMood());
            flag = true;
        }
        if(flag){
            Date now = new Date();
            ori_capsule.setCreatedAt(now);
            capsuleMapper.updateById(ori_capsule);
            return APIResponse.success();
        }
        return APIResponse.error(ErrorCode.PARAM_ERROR);
    }

    @Override
    public APIResponse deletePublicCapsule(String key) {
        String uid = StpUtil.getLoginIdAsString();
        PublicCapsule capsule = capsuleMapper.selectById(key);
        if(!capsule.getUid().equals(uid)){
            return APIResponse.error(ErrorCode.OTHER_USER_CAPSULE);
        }
        capsuleMapper.deleteById(key);
        PublicCapsuleDeleted capsuleDeleted = new PublicCapsuleDeleted(capsule);
        capsuleDeleted.setAdminUid("self");
        capsuleDeleted.setReason("self");
        capsuleDeletedMapper.insert(capsuleDeleted);
        return APIResponse.success();
    }

    @Override
    public APIResponse lovePublicCapsule(String key,int action) {
        PublicCapsule capsule = capsuleMapper.selectById(key);
        if(capsule==null){
            return APIResponse.error(ErrorCode.PRIVATE_CAPSULE_UNEXIST);
        }
        String uid = StpUtil.getLoginIdAsString();
        if(action==ACTION_LOVE){
            Love love = loveMapper.getLove(uid,key);
            if(love!=null){
                return APIResponse.error(ErrorCode.LOVE_EXIST);
            }
            //添加love记录
            Love newLove = new Love();
            newLove.setUid(uid);
            newLove.setCapsuleKey(key);
            loveMapper.insert(newLove);
            //点赞总数+1
            capsule.loveAdd();
            capsuleMapper.updateById(capsule);
            return APIResponse.success();
        }
        else if(action==ACTION_LOVE_CANCEL){
            Love love = loveMapper.getLove(uid,key);
            if(love==null){
                return APIResponse.error(ErrorCode.LOVE_EXIST);
            }
            //删除love记录
            loveMapper.deleteById(love.getId());
            //点赞总数-1
            capsule.loveCancel();
            capsuleMapper.updateById(capsule);
            return APIResponse.success();
        }
        else{
            return APIResponse.error(ErrorCode.PARAM_ERROR);
        }
    }

    @Override
    public APIResponse reportPublicCapsule(String key,String reason) {
        String uid = StpUtil.getLoginIdAsString();
        PublicCapsule capsule = capsuleMapper.selectById(key);
        if(capsule==null){
            return APIResponse.error(ErrorCode.PRIVATE_CAPSULE_UNEXIST);
        }
        if(reason.length()>50){
            return APIResponse.error(ErrorCode.WORDS_50_MAX);
        }
        Report oriReport = reportMapper.getReport(uid,key);
        if (oriReport != null) {
            return APIResponse.error(ErrorCode.REPORT_EXIST);
        }
        Report report = new Report();
        report.setUid(uid);
        report.setCapsuleKey(key);
        report.setReason(reason);
        reportMapper.insert(report);
        return APIResponse.success();
    }

    @Override
    public APIResponse getPublicCapsule(String key) {
        PublicCapsule capsule = capsuleMapper.selectById(key);
        if(capsule==null){
            return APIResponse.error(ErrorCode.PRIVATE_CAPSULE_UNEXIST);
        }
        return APIResponse.success(capsule);
    }

    @Override
    public APIResponse getList(String poolId) {
        return null;
    }
}
