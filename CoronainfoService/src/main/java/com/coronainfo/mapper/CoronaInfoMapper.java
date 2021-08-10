package com.coronainfo.mapper;

import com.coronainfo.vo.CoronaInfoVO;
import com.coronainfo.vo.CoronaSidoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoronaInfoMapper {
    public void insertCoronaInfo(CoronaInfoVO vo);
    public CoronaInfoVO selectCoronaInfoByDate(String date);

    public void insertCoronaSido(CoronaSidoVO vo);
    public CoronaSidoVO selectCoronaSidoByDate(String date);
}