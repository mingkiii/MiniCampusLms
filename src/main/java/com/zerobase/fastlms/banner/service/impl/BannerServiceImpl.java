package com.zerobase.fastlms.banner.service.impl;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.mapper.BannerMapper;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.zerobase.fastlms.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public boolean add(BannerInput parameter) {

        Banner banner = Banner.builder()
                .name(parameter.getName())
                .url(parameter.getUrl())
                .isOpen(parameter.getIsOpen())
                .targetStatus(parameter.getTargetStatus())
                .sortValue(parameter.getSortValue())
                .regDt(LocalDateTime.now())
                .fileName(parameter.getFileName())
                .urlFileName(parameter.getUrlFileName())
                .build();

        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean set(BannerInput parameter) {

        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());

        if (!optionalBanner.isPresent()) {
            return false;
        }

        Banner banner = optionalBanner.get();

        banner.setName(parameter.getName());
        banner.setUrl(parameter.getUrl());
        banner.setIsOpen(parameter.getIsOpen());
        banner.setTargetStatus(parameter.getTargetStatus());
        banner.setSortValue(parameter.getSortValue());
        banner.setUdtDt(LocalDateTime.now());
        banner.setFileName(parameter.getFileName());
        banner.setUrlFileName(parameter.getUrlFileName());

        bannerRepository.save(banner);
        return true;
    }

    @Override
    public List<BannerDto> list(BannerParam parameter) {

        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }

    @Override
    public BannerDto getById(long id) {
        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    @Override
    public boolean del(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");

            for (String x : ids) {
                long id = 0L;

                try {
                    id = Long.parseLong(x);
                } catch(Exception e){

                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public List<BannerDto> indexBannerList() {
        return BannerDto.of(bannerRepository.findByIsOpenOrderBySortValue(true));
    }

}