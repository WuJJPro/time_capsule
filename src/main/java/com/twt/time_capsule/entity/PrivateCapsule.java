package com.twt.time_capsule.entity;


public class PrivateCapsule {

  private long id;
  private long uid;
  private String title;
  private String content;
  private java.sql.Date openTime;
  private java.sql.Timestamp createdAt;
  private String record;
  private long informTime;


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


  public java.sql.Date getOpenTime() {
    return openTime;
  }

  public void setOpenTime(java.sql.Date openTime) {
    this.openTime = openTime;
  }


  public java.sql.Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(java.sql.Timestamp createdAt) {
    this.createdAt = createdAt;
  }


  public String getRecord() {
    return record;
  }

  public void setRecord(String record) {
    this.record = record;
  }


  public long getInformTime() {
    return informTime;
  }

  public void setInformTime(long informTime) {
    this.informTime = informTime;
  }

}
