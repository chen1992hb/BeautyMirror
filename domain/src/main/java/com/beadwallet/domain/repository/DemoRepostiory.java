package com.beadwallet.domain.repository;

import com.beadwallet.domain.entity.DemoEntity;

import io.reactivex.Observable;

public interface DemoRepostiory {

    Observable<DemoEntity> demoSetData(String sis);

}
