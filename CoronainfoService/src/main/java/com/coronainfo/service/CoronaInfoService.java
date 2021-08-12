package com.coronainfo.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.coronainfo.mapper.CoronaInfoMapper;
import com.coronainfo.vo.CoronaAgeAndGenVO;
import com.coronainfo.vo.CoronaInfoVO;
import com.coronainfo.vo.CoronaSidoVO;
import com.coronainfo.vo.CoronaVaccineInfoVO;

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

    public List<CoronaSidoVO> selectTodayCoronaSido(){
        Calendar now = Calendar.getInstance();
        Calendar standard = Calendar.getInstance();
        standard.set(Calendar.HOUR_OF_DAY, 10);
        standard.set(Calendar.MINUTE, 30);
        standard.set(Calendar.SECOND, 10);

        if(now.getTimeInMillis() < standard.getTimeInMillis()){
            // 현재 접속시간이 기준시간보다 이전일때
            // 하루 전 날의 날짜로 변경하고 정보를 가져온다.
            now.add(Calendar.DATE, -1);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dt = formatter.format(now.getTime());
        return mapper.selectCoronaSidoByDate(dt);
    }

    public List<CoronaSidoVO> selectTodayCoronaSidoByDate(String date){
        return mapper.selectCoronaSidoByDate(date);
    }

    public void insertAgeAndGen(CoronaAgeAndGenVO vo){
        mapper.insertCoronaAgeGen(vo);
    }
    public List<CoronaAgeAndGenVO> selectTodayCoronaAge(){
        Calendar now = Calendar.getInstance();
        Calendar standard = Calendar.getInstance();
        standard.set(Calendar.HOUR_OF_DAY, 15);
        standard.set(Calendar.MINUTE, 00);
        standard.set(Calendar.SECOND, 00);

        if(now.getTimeInMillis() < standard.getTimeInMillis()){
            now.add(Calendar.DATE, -1);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dt = formatter.format(now.getTime());
        return mapper.selectCoronaAgeByDate(dt);
    }
    public List<CoronaAgeAndGenVO> selectCoronaAgeByDate(String date){
        return mapper.selectCoronaAgeByDate(date);
    }
    public List<CoronaAgeAndGenVO> selectTodayCoronaGen(){
        Calendar now = Calendar.getInstance();
        Calendar standard = Calendar.getInstance();
        standard.set(Calendar.HOUR_OF_DAY, 15); // 24시간 기준, HOUR 쓰면 12시간 기준
        standard.set(Calendar.MINUTE, 00);
        standard.set(Calendar.SECOND, 00);

        if(now.getTimeInMillis() < standard.getTimeInMillis()){
            now.add(Calendar.DATE, -1);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dt = formatter.format(now.getTime());
        return mapper.selectCoronaGenByDate(dt);
    }
    public List<CoronaAgeAndGenVO> selectCoronaGenByDate(String date){
        return mapper.selectCoronaGenByDate(date);
    }

    public void insertCoronaVaccineInfo(CoronaVaccineInfoVO vo){
        mapper.insertCoronaVaccineInfo(vo);
    }
    public List<CoronaVaccineInfoVO> selectTodayCoronaVaccineInfo(){
        Calendar now = Calendar.getInstance();
        Calendar standard = Calendar.getInstance();
        standard.set(Calendar.HOUR_OF_DAY, 11); // 24시간 기준, HOUR 쓰면 12시간 기준
        standard.set(Calendar.MINUTE, 00);
        standard.set(Calendar.SECOND, 00);

        if(now.getTimeInMillis() < standard.getTimeInMillis()){
            now.add(Calendar.DATE, -1);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dt = formatter.format(now.getTime());
        return mapper.selectCoronaVaccineInfo(dt);
    }
    public List<CoronaVaccineInfoVO> selectCoronaVaccineInfo(String date){
        return mapper.selectCoronaVaccineInfo(date);
    }
}
