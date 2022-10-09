package com.twt.time_capsule.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CapsulePool {

  private String id;
  private String title;
  private String picture;
  private long active;
  private Date createdAt;
  private Date startTime;
  private Date endTime;




}
