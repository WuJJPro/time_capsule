package com.twt.time_capsule.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Report {

  private String id;
  private String CapsuleKey;
  private String uid;
  private String reason;
  private Date createdAt;




}
