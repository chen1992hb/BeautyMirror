package com.beadwallet.beautymirror.feature.demo;

import com.beadwallet.beautymirror.feature.base.dao.ILoadingView;
import com.beadwallet.beautymirror.model.DemoModel;

public interface IDemoView extends ILoadingView{
    void startLoading();
     void endLoading();
    void requestError(String msg);
    void requestSucess(DemoModel data);
}
