package com.beadwallet.beautymirror.comminview;

import com.beadwallet.beautymirror.R;
import com.chad.library.adapter.base.loadmore.LoadMoreView;


/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2017/12/6
 */

public class CustomLoadMoreView extends LoadMoreView {

  @Override
  public int getLayoutId() {
    return R.layout.view_loadmore;
  }

  @Override
  protected int getLoadingViewId() {
    return R.id.ll_loadmore_loading;
  }

  @Override
  protected int getLoadFailViewId() {
    return R.id.ll_loadmore_fail;
  }

  @Override
  protected int getLoadEndViewId() {
    return R.id.ll_loadmore_end;
  }
}
