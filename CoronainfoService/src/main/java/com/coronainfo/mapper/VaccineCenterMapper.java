package com.coronainfo.mapper;

import java.util.List;

import com.coronainfo.vo.VaccineCenterVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VaccineCenterMapper {
    public void insertCoronaVaccineCenter(VaccineCenterVO vo);
    public List<VaccineCenterVO> selectVaccineCenter(String region);
}
