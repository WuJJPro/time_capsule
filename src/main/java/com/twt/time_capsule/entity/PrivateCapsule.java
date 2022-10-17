package com.twt.time_capsule.entity;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class PrivateCapsule {

  private String id;
  @JsonIgnore
  private String uid;
  private String title;
  private String content;
  private Date openTime;
  private Date createdAt;
  @TableField(typeHandler = JacksonTypeHandler.class)
  private JSONArray record;
  @TableField(typeHandler = JacksonTypeHandler.class)
  private JSONArray picture;
  private long success;


}
