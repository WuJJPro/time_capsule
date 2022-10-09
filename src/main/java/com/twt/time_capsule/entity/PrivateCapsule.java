package com.twt.time_capsule.entity;


import lombok.Data;

import java.util.Date;
@Data
public class PrivateCapsule {

  private String id;
  private long uid;
  private String title;
  private String content;
  private Date openTime;
  private Date createdAt;
  private String record;
  private String picture;

}
