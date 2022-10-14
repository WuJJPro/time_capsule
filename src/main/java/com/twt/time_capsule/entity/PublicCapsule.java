package com.twt.time_capsule.entity;


import lombok.Data;

import java.util.Date;
@Data
public class PublicCapsule {

  private String id;
  private String uid;
  private String poolId;
  private String content;
  private Date createdAt;
  private Integer mood;
  private Integer likeNumber;




}
