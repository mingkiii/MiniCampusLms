package com.zerobase.fastlms.banner.model;

import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.type.TargetStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerInput {

    long id;

    private String name;
    private String url;
    private Boolean isOpen;
    private TargetStatusType targetStatus;
    private int sortValue;

    private String idList;

    private String fileName;
    private String urlFileName;


}