package com.coronainfo.mapper;

import java.util.List;

import com.coronainfo.vo.CoronaInfoVO;
import com.coronainfo.vo.CoronaSidoVO;
import com.coronainfo.vo.CoronaVaccineInfoVO;
import com.coronainfo.vo.CoronaWeeksVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegionalInfoMapper {
    public CoronaSidoVO selectRegionalCoronaInfo(String region, String date);
    public CoronaInfoVO selectCoronaInfoRegionTotal(String date);
    public CoronaVaccineInfoVO selectCoronaVaccineStatus(String region, String date);
    public String selectDangerAge(String date);
    public List<CoronaWeeksVO> selectRegionalCoronaTwoWeeks (String region, String date);
    public List<CoronaWeeksVO> selectRegionalVaccineTwoWeeks (String region, String date);
    public List<CoronaWeeksVO> selectVaccineInfo(String date);
}

