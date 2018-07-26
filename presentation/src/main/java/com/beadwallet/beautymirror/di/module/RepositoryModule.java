package com.beadwallet.beautymirror.di.module;
import com.beadwallet.data.repository.DemoDataRepository;
import com.beadwallet.domain.repository.DemoRepostiory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public DemoRepostiory provideRefundRepository(DemoDataRepository refundDataRepository) {
        return refundDataRepository;
    }


}