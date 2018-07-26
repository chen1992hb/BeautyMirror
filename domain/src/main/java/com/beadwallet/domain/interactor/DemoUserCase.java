package com.beadwallet.domain.interactor;

import com.beadwallet.domain.entity.DemoEntity;
import com.beadwallet.domain.executor.IExecutionThread;
import com.beadwallet.domain.executor.IPostExecutionThread;
import com.beadwallet.domain.repository.DemoRepostiory;
import com.beadwallet.domain.interactor.DemoUserCase.Params;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DemoUserCase extends UseCase<DemoEntity,Params>{

private DemoRepostiory mDemoRepostiory;

    @Inject
    DemoUserCase(DemoRepostiory demoRepostiory, IExecutionThread IExecutionThread, IPostExecutionThread IPostExecutionThread) {
        super(IExecutionThread, IPostExecutionThread);
        mDemoRepostiory=demoRepostiory;
    }

    @Override
    Observable<DemoEntity> buildUseCaseObservable(Params params) {
        return mDemoRepostiory.demoSetData("123456");
    }


    public static final class Params{
        String mAppKey;
        String mSis;
        private Params(String sis) {
            mSis = sis;

        }

        public static Params forRefund(String sis){
            return new Params(sis);
        }
    }

}
