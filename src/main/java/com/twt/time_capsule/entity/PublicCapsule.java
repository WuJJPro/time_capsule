package com.twt.time_capsule.entity;


public class PublicCapsule {

  private long id;
  private long uid;
  private long poolId;
  private String title;
  private String content;
  private String picture;
  private String record;
  private java.sql.Timestamp createdAt;
  private long informTime;
  private long likeNumber;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public long getPoolId() {
    return poolId;
  }

  public void setPoolId(long poolId) {
    this.poolId = poolId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }


  public String getRecord() {
    return record;
  }

  public void setRecord(String record) {
    this.record = record;
  }


  public java.sql.Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(java.sql.Timestamp createdAt) {
    this.createdAt = createdAt;
  }


  public long getInformTime() {
    return informTime;
  }

  public void setInformTime(long informTime) {
    this.informTime = informTime;
  }


  public long getLikeNumber() {
    return likeNumber;
  }

  public void setLikeNumber(long likeNumber) {
    this.likeNumber = likeNumber;
  }

}
