package com.coronainfo.mapper;

import java.util.List;

import com.coronainfo.vo.ConsignedCenterVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConsignedCenterMapper {
    public void insertConsignedCenter(ConsignedCenterVO vo);
    public List<ConsignedCenterVO> selectConsignedCenter(String adr);
    public void getLatAndLng(String Lat, String Lng, Integer seq);
}
