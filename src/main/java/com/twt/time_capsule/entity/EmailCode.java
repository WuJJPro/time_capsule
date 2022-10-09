package com.twt.time_capsule.entity;


import lombok.Data;

import java.util.Date;
@Data
public class EmailCode {

  private String id;
  private String email;
  private String code;
  private Date endTime;




}
