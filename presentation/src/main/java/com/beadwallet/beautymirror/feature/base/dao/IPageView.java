package com.beadwallet.beautymirror.feature.base.dao;

import java.util.List;



public interface IPageView<Data> extends IBaseView{
  void onRefreshSuccess(List<Data> dataList);
  void onLoadMoreSuccess(List<Data> dataList);
  void onRefreshComplete();
  void onLoadMoreComplete();
  void onRefreshStart();
  void onLoadMoreStart();
  void onRefreshFail();
  void onLoadMoreFail();
  void onRefreshEmpty();
  void onLoadMoreEmpty();
  void onEnableLoadMore(boolean enable);
  void onLastPage();
}
