package com.twt.time_capsule.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

  private String id;
  private String uid;
  private long manage;
  private Date createdAt;
  private String email;
  private String password;
  private String salt;
  private long emailState;




}
