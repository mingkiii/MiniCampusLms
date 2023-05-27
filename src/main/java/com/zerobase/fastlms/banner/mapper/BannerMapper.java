package com.zerobase.fastlms.banner.mapper;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BannerMapper {
    long selectListCount(BannerParam parameter);
    List<BannerDto> selectList(BannerParam parameter);
}