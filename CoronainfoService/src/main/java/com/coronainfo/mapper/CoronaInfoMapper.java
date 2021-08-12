package com.coronainfo.mapper;

import java.util.List;

import com.coronainfo.vo.CoronaAgeAndGenVO;
import com.coronainfo.vo.CoronaInfoVO;
import com.coronainfo.vo.CoronaSidoVO;
import com.coronainfo.vo.CoronaVaccineInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoronaInfoMapper {
    public void insertCoronaInfo(CoronaInfoVO vo);
    public CoronaInfoVO selectCoronaInfoByDate(String date);

    public void insertCoronaSido(CoronaSidoVO vo);
    public List<CoronaSidoVO> selectCoronaSidoByDate(String date);

    public void insertCoronaAgeGen(CoronaAgeAndGenVO vo);
    public List<CoronaAgeAndGenVO> selectCoronaAgeByDate(String date);
    public List<CoronaAgeAndGenVO> selectCoronaGenByDate(String date);

    public void insertCoronaVaccineInfo(CoronaVaccineInfoVO vo);
    public List<CoronaVaccineInfoVO> selectCoronaVaccineInfo(String date);
}