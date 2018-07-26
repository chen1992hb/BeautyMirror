package com.beadwallet.data.repository;

import android.app.Service;

import com.beadwallet.data.BuildConfig;
import com.beadwallet.data.bean.request.DemoRequest;
import com.beadwallet.data.bean.response.DemoResponse;
import com.beadwallet.data.cache.BaseUrlCache;
import com.beadwallet.data.cache.UserCache;
import com.beadwallet.data.mapper.DemoDataMapper;
import com.beadwallet.data.net.ApiConnection;
import com.beadwallet.data.net.ApiUrl;
import com.beadwallet.data.net.service.DemoService;
import com.beadwallet.data.util.RepositoryUtil;
import com.beadwallet.data.util.RequestParamUtil;
import com.beadwallet.domain.entity.DemoEntity;
import com.beadwallet.domain.repository.DemoRepostiory;
import io.reactivex.functions.Function;
import io.reactivex.Observable;
import javax.inject.Inject;

import io.reactivex.Observable;

public class DemoDataRepository implements DemoRepostiory {
    @Inject
    RepositoryUtil mRepositoryUtil;

    private ApiConnection mApiConnection;
    private DemoDataMapper mDemoDataMapper;

    @Inject
    public DemoDataRepository(ApiConnection apiConnection,
                                DemoDataMapper mDemoDataMapper) {
       this.mApiConnection = apiConnection;
        this.mDemoDataMapper = mDemoDataMapper;
    }


    @Override
    public Observable<DemoEntity> demoSetData(String sis) {

        DemoService service = mApiConnection.createService(DemoService.class);
        Observable<DemoResponse> request = service.request(getParams(sis));
        return mRepositoryUtil.extractData(request, DemoResponse.class).map(
                new Function<DemoResponse, DemoEntity>() {
                    @Override
                    public DemoEntity apply(DemoResponse DemoResponse) throws Exception {
                        return mDemoDataMapper.transform(DemoResponse, DemoEntity.class);
                    }
                });
    }

    private DemoRequest getParams(String sis) {
        DemoRequest demoRequest = new DemoRequest();
        // 获取本地时间
        demoRequest.setTimestamp(RequestParamUtil.getCurrTerminaltime());
        demoRequest.setSts(sis);
        demoRequest.setAppVersion(BuildConfig.VERSION_CODE+"");

        return demoRequest;
    }



}
