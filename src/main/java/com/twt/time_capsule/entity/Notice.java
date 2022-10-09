package com.twt.time_capsule.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {

  private String id;
  private String uid;
  private String content;
  private String type;
  private Date createdAt;



}
