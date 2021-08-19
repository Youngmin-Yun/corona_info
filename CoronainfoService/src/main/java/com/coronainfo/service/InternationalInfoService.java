package com.coronainfo.service;

import java.util.List;

import com.coronainfo.mapper.InternationalInfoMapper;
import com.coronainfo.vo.InternationalInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InternationalInfoService {
    @Autowired
    InternationalInfoMapper mapper;
    public void insertInternationalInfo(InternationalInfoVO vo){
        mapper.insertInternationalInfo(vo);
    }
    public List<InternationalInfoVO> selectInternationalCorona(String continent, String date){
        return mapper.selectInternationalCorona(continent, date);
    }
}
