package com.coronainfo.mapper;

import java.util.List;

import com.coronainfo.vo.InternationalInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InternationalInfoMapper {
    public void insertInternationalInfo(InternationalInfoVO vo);
    public List<InternationalInfoVO> selectInternationalCorona(String continent, String date);
}
