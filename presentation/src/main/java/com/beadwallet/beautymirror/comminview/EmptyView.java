package com.beadwallet.beautymirror.comminview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.beadwallet.beautymirror.R;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmptyView extends FrameLayout {

  private static final String DEFAULT_RETRY_TEXT = "重试";

  @BindView(R.id.iv_empty)
  ImageView mIvEmpty;
  @BindView(R.id.tv_empty_cause)
  TextView mTvEmptyCause;
  @BindView(R.id.btn_empty_retry)
  QMUIRoundButton mBtnEmptyRetry;

  private OnRetryClickListener mOnRetryClickListener;

  public EmptyView(@NonNull Context context) {
    this(context, null);
  }

  public EmptyView(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    View rootView = View.inflate(context, R.layout.view_empty, this);
    ButterKnife.bind(rootView);
  }


  @OnClick(R.id.btn_empty_retry)
  public void onViewClicked() {
    if(mOnRetryClickListener != null){
      mOnRetryClickListener.onRetryClick();
    }
  }

  public void showLoading(@DrawableRes int drawableResId, String loadingText) {
    setImage(drawableResId);
    setCause(loadingText);
    showRetry(false);
  }

  public void showNetError(@DrawableRes int drawableResId, String netErrorText, String retryText) {
    setImage(drawableResId);
    setCause(netErrorText);
    showRetry(retryText);
  }

  public void showNetError(@DrawableRes int drawableResId, String retryText) {
    setImage(drawableResId);
    setCause(null);
    showRetry(retryText);
  }

  public void showEmptyRetry(@DrawableRes int drawableResId, String emptyText, String retryText) {
    setImage(drawableResId);
    setCause(emptyText);
    showRetry(retryText);
  }

  public void showEmptyRetry(@DrawableRes int drawableResId, String retryText) {
    setImage(drawableResId);
    setCause(null);
    showRetry(retryText);
  }

  private void setImage(@Nullable Drawable drawable) {
    if (drawable != null) {
      mIvEmpty.setBackground(drawable);
      mIvEmpty.setVisibility(VISIBLE);
    } else {
      mIvEmpty.setVisibility(GONE);
    }
  }

  private void setImage(@DrawableRes int drawableResId) {
    mIvEmpty.setBackgroundResource(drawableResId);
    mIvEmpty.setVisibility(VISIBLE);
  }

  private void setCause(String cause) {
    if (TextUtils.isEmpty(cause)) {
      mTvEmptyCause.setVisibility(View.GONE);
    } else {
      mTvEmptyCause.setText(cause);
      mTvEmptyCause.setVisibility(VISIBLE);
    }
  }

  private void showRetry(String retry){
    showRetry(true);
    if (TextUtils.isEmpty(retry)) {
      mBtnEmptyRetry.setText(DEFAULT_RETRY_TEXT);
    } else {
      mBtnEmptyRetry.setText(retry);
    }
  }

  private void showRetry(boolean isShow) {
    if (isShow) {
      mBtnEmptyRetry.setVisibility(VISIBLE);
    } else {
      mBtnEmptyRetry.setVisibility(GONE);
    }
  }

  public interface OnRetryClickListener{
    void onRetryClick();
  }

  public void setOnRetryClickListener(OnRetryClickListener onRetryClickListener){
    mOnRetryClickListener = onRetryClickListener;
  }
}
