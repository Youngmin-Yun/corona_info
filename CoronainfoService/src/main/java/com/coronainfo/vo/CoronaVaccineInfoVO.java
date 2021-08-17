package com.coronainfo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CoronaVaccineInfoVO {
    private Integer seq;
    private Integer accFirstCnt;
    private Integer accSecondCnt;
    private Date regDt;
    private Integer firstCnt;
    private Integer secondCnt;
    private String sido;

    private String dt;
    private String region;

    private String firstshot;
    private String secondshot;
}
