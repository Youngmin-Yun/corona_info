package com.coronainfo.service;

import java.util.List;

import com.coronainfo.mapper.VaccineCenterMapper;
import com.coronainfo.vo.VaccineCenterVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinCenterService {
    @Autowired
    VaccineCenterMapper mapper;
    public void insertCoronaVaccineCenter(VaccineCenterVO vo){
        mapper.insertCoronaVaccineCenter(vo);
    }
    public List<VaccineCenterVO> selectVaccineCenter(String region){
        return mapper.selectVaccineCenter(region);
    }
}
