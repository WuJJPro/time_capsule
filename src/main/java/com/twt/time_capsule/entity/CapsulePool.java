package com.twt.time_capsule.entity;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CapsulePool {

  private String id;
  private String title;
  @TableField(typeHandler = JacksonTypeHandler.class)
  private JSONArray picture;
  private long state;
  @JsonIgnore
  private Date createdAt;
  private Date startTime;
  private Date endTime;




}
