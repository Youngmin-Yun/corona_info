package com.coronainfo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CoronaAgeAndGenVO {
    private Integer seq;
    private Integer confCase;
    private Integer confCaseRate;
    private Date createDt;
    private Integer criticalRate;
    private Integer death;
    private Integer deathRate;
    private String gubun;
}
