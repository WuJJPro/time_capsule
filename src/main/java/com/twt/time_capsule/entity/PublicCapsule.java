package com.twt.time_capsule.entity;


import lombok.Data;

import java.util.Date;
@Data
public class PublicCapsule {

  private String id;
  private String uid;
  private long poolId;
  private String title;
  private String content;
  private Date createdAt;
  private long mood;
  private long likeNumber;




}
