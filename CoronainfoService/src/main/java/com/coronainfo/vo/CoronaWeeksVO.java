package com.coronainfo.vo;

import lombok.Data;

@Data
public class CoronaWeeksVO {
    private String date;
    private Integer defCnt;

    private Integer accFirstCnt;
    private Integer accSecondCnt;
    private Integer firstCnt;
    private Integer secondCnt;
    private String sido;
}
