package com.beadwallet.beautymirror.feature.base;

import com.beadwallet.beautymirror.feature.base.dao.IPresenter;
import com.beadwallet.domain.interactor.UseCase;

import java.util.ArrayList;
import java.util.Arrays;



public abstract class BasePresenter implements IPresenter {

  private ArrayList<UseCase> mUseCases = new ArrayList<>();

  public BasePresenter(UseCase... useCases) {
    mUseCases.addAll(Arrays.asList(useCases));
  }

  @Override
  public void onCreate() {

  }

  @Override
  public void onStart() {

  }

  @Override
  public void onResume() {

  }

  @Override
  public void onPause() {

  }

  @Override
  public void onStop() {

  }

  @Override
  public void onDestroy() {
    for (UseCase mUseCase : mUseCases) {
      if(mUseCase != null){
        mUseCase.dispose();
      }
    }
    mUseCases.clear();
  }

  @Override
  public void onViewCreated() {

  }

  @Override
  public void onDestroyView() {

  }
}
