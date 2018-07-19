package com.beadwallet.beautymirror.feature.base.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.beadwallet.beautymirror.comminview.EmptyView.OnRetryClickListener;

import com.beadwallet.beautymirror.comminview.EmptyView;

public interface IEmptyView {
    @NonNull
    EmptyView emptyView();

    @Nullable
    OnRetryClickListener onRetryClick();
}
