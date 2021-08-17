package com.coronainfo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class VaccineCenterVO {
    private Integer seq;
    private String address;
    private String centerName;
    private String centerType;
    private Date createdAt;
    private String facilityName;
    private String lat;
    private String lng;
    private String org;
    private String phoneNumber;
    private String sido;
    private String sigungu;
}
