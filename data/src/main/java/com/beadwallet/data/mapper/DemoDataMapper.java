package com.beadwallet.data.mapper;

import com.beadwallet.data.bean.response.DemoResponse;
import com.beadwallet.domain.entity.DemoEntity;

import javax.inject.Inject;

public class DemoDataMapper extends BaseMapper<DemoResponse, DemoEntity> {
    @Inject
    public DemoDataMapper() {
    }
}