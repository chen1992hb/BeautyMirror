package com.beadwallet.beautymirror.mapper;

import com.beadwallet.beautymirror.model.DemoModel;
import com.beadwallet.data.mapper.BaseMapper;
import com.beadwallet.domain.entity.DemoEntity;

import javax.inject.Inject;

public class DemoModelMapper extends BaseMapper<DemoEntity,DemoModel> {
    @Inject
    public DemoModelMapper() {
    }

}
