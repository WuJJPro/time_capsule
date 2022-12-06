package com.twt.time_capsule.controller;

import com.twt.time_capsule.entity.PrivateCapsule;
import com.twt.time_capsule.mapper.PrivateCapsuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Demo {
    @Autowired
    PrivateCapsuleMapper capsuleMapper;
    @GetMapping("test")
    public String demo(){
        List<PrivateCapsule> privateCapsuleList = capsuleMapper.timeTask();
        return "A";
    }
}
