package com.coronainfo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CoronaSidoVO {
    private Integer seq;
    private Integer deathCnt;
    private Integer defCnt;
    private String gubun;
    private Integer incDec;
    private Integer isolClearCnt;
    private Date createDt;
    private Integer isolIngCnt;
    private Integer localOccCnt;
    private Integer overFlowCnt;

    private String strDeathCnt;
    private String strDefCnt;
    private String strIncDec;
    private String strIsolClearCnt;
    private String strIsolIngCnt;
    private String strLocalOccCnt;
    private String strOverFlowCnt;
}
