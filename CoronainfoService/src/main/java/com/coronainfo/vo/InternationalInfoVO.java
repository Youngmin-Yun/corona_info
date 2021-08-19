package com.coronainfo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class InternationalInfoVO {
    private Integer seq;
    private Date createDt;
    private Integer natDeathCnt;
    private Integer natDefCnt;
    private String nationNm;
    private String areaNm;
}
