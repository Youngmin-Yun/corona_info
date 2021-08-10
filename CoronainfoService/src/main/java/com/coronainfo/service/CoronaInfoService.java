package com.coronainfo.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.coronainfo.mapper.CoronaInfoMapper;
import com.coronainfo.vo.CoronaInfoVO;
import com.coronainfo.vo.CoronaSidoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoronaInfoService {
    @Autowired
    CoronaInfoMapper mapper;
    public void insertCoronaInfo(CoronaInfoVO vo){
        mapper.insertCoronaInfo(vo);
    }
    public CoronaInfoVO selectTodayCoronaInfo(){
        // Calendar start = Calendar.getInstance();
        // Calendar end = Calendar.getInstance();
        
        // start.set(Calendar.HOUR_OF_DAY, 10);
        // start.set(Calendar.MINUTE, 30);
        // start.set(Calendar.SECOND,0);

        // 2021-08-11 01:00:00 - 접속시간
        // 2021-08-11 10:30:00 - 세팅값
        // 현재시간이 세팅값보다 이전이라면, 전 날의 데이터를 뽑아주고
        // 현재시간이 세팅값보다 나중이라면, 오늘 데이터를 뽑아준다.
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(now);
        CoronaInfoVO data = mapper.selectCoronaInfoByDate(date);
        Integer accExamCnt = data.getAccExamCnt();
        Integer decideCnt = data.getDecideCnt();

        DecimalFormat dFormatter = new DecimalFormat("###,###,###");
        String strAccExamCnt = dFormatter.format(accExamCnt);
        String strDecideCnt = dFormatter.format(decideCnt);

        data.setStrAccExamCnt(strAccExamCnt);
        data.setStrDecideCnt(strDecideCnt);
        
        return data;
    }
    public void insertCoronaSido(CoronaSidoVO vo){
        mapper.insertCoronaSido(vo);
    }
    public CoronaSidoVO selectTodayCoronaSido(){
        // Calendar start = Calendar.getInstance();
        // Calendar end = Calendar.getInstance();
        
        // start.set(Calendar.HOUR_OF_DAY, 10);
        // start.set(Calendar.MINUTE, 30);
        // start.set(Calendar.SECOND,0);

        // 2021-08-11 01:00:00 - 접속시간
        // 2021-08-11 10:30:00 - 세팅값
        // 현재시간이 세팅값보다 이전이라면, 전 날의 데이터를 뽑아주고
        // 현재시간이 세팅값보다 나중이라면, 오늘 데이터를 뽑아준다.
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(now);
        CoronaSidoVO data = mapper.selectCoronaSidoByDate(date);
        Integer accExamCnt = data.getAccExamCnt();
        Integer decideCnt = data.getDecideCnt();

        DecimalFormat dFormatter = new DecimalFormat("###,###,###");
        String strAccExamCnt = dFormatter.format(accExamCnt);
        String strDecideCnt = dFormatter.format(decideCnt);

        data.setStrAccExamCnt(strAccExamCnt);
        data.setStrDecideCnt(strDecideCnt);
        
        return data;
    }
}
