package com.twt.time_capsule.entity;


import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.Date;
@Data
public class PrivateCapsuleFinished {

  private String id;
  private String uid;
  private String title;
  private String content;
  private Date openTime;
  private Date createdAt;
  private JSONArray record;
  private long success;
  private JSONArray picture;



}
