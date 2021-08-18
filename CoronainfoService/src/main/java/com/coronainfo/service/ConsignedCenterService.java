package com.coronainfo.service;

import java.util.List;

import com.coronainfo.mapper.ConsignedCenterMapper;
import com.coronainfo.vo.ConsignedCenterVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsignedCenterService {
    @Autowired
    ConsignedCenterMapper mapper;
    public void insertConsignedCenter(ConsignedCenterVO vo){
        mapper.insertConsignedCenter(vo);
    }
    public List<ConsignedCenterVO> selectConsignedCenter(String adr){
        return mapper.selectConsignedCenter(adr);
    }
}
