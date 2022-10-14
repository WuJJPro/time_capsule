package com.twt.time_capsule.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PublicCapsuleDeleted {

  private String id;
  private String oriId;
  private String uid;
  private String poolId;
  private String content;
  private Date createdAt;
  private long mood;
  private long likeNumber;
  private Date deletedAt;
  private String adminUid;
  private String reason;

  public PublicCapsuleDeleted(PublicCapsule capsule) {
    this.oriId = capsule.getId();
    this.uid = capsule.getUid();
    this.poolId = capsule.getPoolId();
    this.content = capsule.getContent();
    this.createdAt = capsule.getCreatedAt();
    this.mood = capsule.getMood();
    this.likeNumber = capsule.getLikeNumber();
  }
}
