package com.coronainfo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CoronaAgeAndGenVO {
    private Integer seq;
    private Integer confCase;
    private Double confCaseRate;
    private Date createDt;
    private Double criticalRate;
    private Integer death;
    private Double deathRate;
    private String gubun;
}
