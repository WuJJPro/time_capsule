package com.twt.time_capsule.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PublicCapsuleDeleted {

  private String id;
  private String oriId;
  private String uid;
  private String poolId;
  private String title;
  private String content;
  private Date createdAt;
  private long mood;
  private long likeNumber;
  private Date deletedAt;
  private String adminUid;
  private String reason;




}
