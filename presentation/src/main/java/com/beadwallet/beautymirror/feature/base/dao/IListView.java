package com.beadwallet.beautymirror.feature.base.dao;


public interface IListView<T> extends IPageView<T> {
    void onStartLoading();
    void onEndLoading();
}
