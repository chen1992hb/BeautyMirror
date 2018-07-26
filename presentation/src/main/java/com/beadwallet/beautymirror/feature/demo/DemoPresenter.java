package com.beadwallet.beautymirror.feature.demo;

import com.beadwallet.beautymirror.feature.base.BasePresenter;
import com.beadwallet.beautymirror.feature.base.CommonLoadingObserver;
import com.beadwallet.beautymirror.mapper.DemoModelMapper;
import com.beadwallet.beautymirror.model.DemoModel;
import com.beadwallet.data.bean.base.BaseResponse;
import com.beadwallet.domain.entity.DemoEntity;
import com.beadwallet.domain.interactor.DemoUserCase;

import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;

public class DemoPresenter extends BasePresenter {
    private IDemoView iDemoView;
    private DemoUserCase mDemoUserCase;
    private DemoModelMapper mDemoModelMapper;
    public DemoPresenter( IDemoView demoView,DemoUserCase demoUserCase,DemoModelMapper mDemoModelMapper){
        mDemoUserCase=demoUserCase;
        iDemoView=demoView;
       this.mDemoModelMapper=mDemoModelMapper;
    }
    public void request(String sis){
        mDemoUserCase.execute(new DemoObserver(),DemoUserCase.Params.forRefund(sis));
    }

    private class DemoObserver extends DisposableObserver<DemoEntity> {

        @Override
        public void onNext(DemoEntity refundEntity) {
            DemoModel demoModel = mDemoModelMapper.transform(refundEntity, DemoModel.class);
            if(BaseResponse.getStatusOk().equals(demoModel.getSts())){
                iDemoView.requestSucess(demoModel);
            }else{
                iDemoView.requestError(demoModel.getRmk());
            }
        }

        @Override
        public void onError(Throwable e) {
        iDemoView.requestError(e.getMessage());
        }

        @Override
        public void onComplete() {

        }


    }

}
