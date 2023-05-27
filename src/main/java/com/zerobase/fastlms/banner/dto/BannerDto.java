package com.zerobase.fastlms.banner.dto;


import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.type.TargetStatusType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BannerDto {

    private Long id;

    private String name;
    private String url;
    private Boolean isOpen;
    private TargetStatusType targetStatusType;
    private int sortValue;

    private LocalDateTime regDt;
    private LocalDateTime udtDt;

    private String fileName;
    private String urlFileName;

    long totalCount;
    long seq;

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .name(banner.getName())
                .url(banner.getUrl())
                .isOpen(banner.getIsOpen())
                .targetStatusType(banner.getTargetStatus())
                .sortValue(banner.getSortValue())
                .regDt(banner.getRegDt())
                .udtDt(banner.getUdtDt())
                .fileName(banner.getFileName())
                .urlFileName(banner.getUrlFileName())
                .build();
    }


    public static List<BannerDto> of(List<Banner> banners) {

        if (banners == null) {
            return null;
        }

        List<BannerDto> bannerList = new ArrayList<>();
        for(Banner x : banners) {
            bannerList.add(BannerDto.of(x));
        }
        return bannerList;

    }


}