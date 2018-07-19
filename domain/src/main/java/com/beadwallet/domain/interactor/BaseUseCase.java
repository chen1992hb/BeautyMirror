package com.beadwallet.domain.interactor;


public abstract class BaseUseCase<Q extends BaseUseCase.RequestValues,P extends BaseUseCase.ResponseValue>{

    private Q mRequestValuse;

    private UseCaseCallback<P> mUseCaseCallback;

    public UseCaseCallback<P> getmUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setmUseCaseCallback(UseCaseCallback<P> mUseCaseCallback) {
        this.mUseCaseCallback = mUseCaseCallback;
    }

    public Q getmRequestValuse() {
        return mRequestValuse;
    }

    public void setmRequestValuse(Q mRequestValuse) {
        this.mRequestValuse = mRequestValuse;
    }

    public void run(){
        executeUseCase(mRequestValuse);
    }


    public abstract void executeUseCase(Q requestValues);


    public interface RequestValues {
    }


    public interface ResponseValue {
    }


    public interface UseCaseCallback<P> {
        void onSuccess(P response);

        void onError(String error);
    }
}
