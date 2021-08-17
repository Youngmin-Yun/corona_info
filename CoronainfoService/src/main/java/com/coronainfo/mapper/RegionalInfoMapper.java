package com.coronainfo.mapper;

import com.coronainfo.vo.CoronaInfoVO;
import com.coronainfo.vo.CoronaSidoVO;
import com.coronainfo.vo.CoronaVaccineInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegionalInfoMapper {
    public CoronaSidoVO selectRegionalCoronaInfo(String region, String date);
    public CoronaInfoVO selectCoronaInfoRegionTotal(String date);
    public CoronaVaccineInfoVO selectCoronaVaccineStatus(String region, String date);
}

