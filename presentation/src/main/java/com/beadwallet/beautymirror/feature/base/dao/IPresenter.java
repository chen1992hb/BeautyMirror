package com.beadwallet.beautymirror.feature.base.dao;


public interface IPresenter {

  void onCreate();

  void onStart();

  void onResume();

  void onPause();

  void onStop();

  void onDestroy();

  void onViewCreated();

  void onDestroyView();
}
