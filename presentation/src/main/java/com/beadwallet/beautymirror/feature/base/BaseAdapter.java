package com.beadwallet.beautymirror.feature.base;

import android.content.Context;
import android.support.annotation.Nullable;

import com.beadwallet.beautymirror.comminview.CustomLoadMoreView;
import com.beadwallet.beautymirror.comminview.EmptyView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public abstract class BaseAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

  private final EmptyView mEmptyView;

  public BaseAdapter(Context context, int layoutResId,
                     @Nullable List<T> data) {
    super(layoutResId, data);
    mEmptyView = new EmptyView(context);
    setEmptyView(mEmptyView);
    setLoadMoreView(new CustomLoadMoreView());
  }

  public EmptyView getEmptyView(){
    return mEmptyView;
  }
}
