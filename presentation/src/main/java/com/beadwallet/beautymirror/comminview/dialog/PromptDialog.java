package com.beadwallet.beautymirror.comminview.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.beadwallet.beautymirror.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2017/12/18
 */

public class PromptDialog extends DialogFragment {

  private static PromptDialog newInstance() {

    Bundle args = new Bundle();

    PromptDialog fragment = new PromptDialog();
    fragment.setArguments(args);
    return fragment;
  }

  public static final int TYPE_DIALOG_CHOOSE  = 0;
  public static final int TYPE_DIALOG_NEUTRAL = 1;

  @BindView(R.id.tv_dialog_message)
  TextView mTvDialogMessage;
  @BindView(R.id.tv_dialog_confirm)
  TextView mTvDialogConfirm;
  @BindView(R.id.tv_dialog_cancel)
  TextView mTvDialogCancel;
  @BindView(R.id.tv_dialog_neutral)
  TextView mTvDialogNeutral;
  Unbinder unbinder;
  private View mView;

  private static String mMessage;
  private static String mPositiveButtonText;
  private static String mNegativeButtonText;
  private static String mNeutralButtonText;
  private static PromptDialogListener mOnPositiveButtonClickListener;
  private static PromptDialogListener mOnNegativeButtonClickListener;
  private static PromptDialogListener mOnNeutralButtonClickListener;
  private static int             mDialogType;

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    mView = inflater.inflate(R.layout.dialog_prompt, null);
    unbinder = ButterKnife.bind(this, mView);
    builder.setView(mView);

    switch (mDialogType){
      case TYPE_DIALOG_CHOOSE:
        mTvDialogConfirm.setVisibility(View.VISIBLE);
        mTvDialogCancel.setVisibility(View.VISIBLE);
        mTvDialogNeutral.setVisibility(View.INVISIBLE);
        break;

      case TYPE_DIALOG_NEUTRAL:
        mTvDialogConfirm.setVisibility(View.INVISIBLE);
        mTvDialogCancel.setVisibility(View.INVISIBLE);
        mTvDialogNeutral.setVisibility(View.VISIBLE);
        break;
    }

    mTvDialogMessage.setText(mMessage);
    setButton(mTvDialogConfirm, mPositiveButtonText, mOnPositiveButtonClickListener);
    setButton(mTvDialogCancel, mNegativeButtonText, mOnNegativeButtonClickListener);
    setButton(mTvDialogNeutral, mNeutralButtonText, mOnNeutralButtonClickListener);
    AlertDialog alertDialog = builder.create();
    alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    return alertDialog;
  }

  private void setButton(TextView textView, String text, PromptDialogListener promptDialogListener){
    if(text != null){
      textView.setText(text);
    }
    if(promptDialogListener == null){
      promptDialogListener = new PromptDialogListener() {
        @Override
        public void onViewClick(View view, PromptDialog promptDialog) {
          promptDialog.dismissAllowingStateLoss();
        }
      };
    }
    final PromptDialogListener finalPromptDialogListener = promptDialogListener;
    textView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        finalPromptDialogListener.onViewClick(v, PromptDialog.this);
      }
    });
  }

  public static class Builder{

    public Builder(@DialogType int dialogType) {
      mDialogType = dialogType;
    }

    public PromptDialog create(){
      PromptDialog promptDialog = newInstance();
      return promptDialog;
    }

    public Builder setMessage(@NonNull String message) {
      mMessage = message;
      return this;
    }

    public Builder setPositiveButton(String positiveButtonText, @NonNull PromptDialogListener onPositiveButtonClickListener){
      mPositiveButtonText = positiveButtonText;
      mOnPositiveButtonClickListener = onPositiveButtonClickListener;
      return this;
    }

    public Builder setNegativeButton(String negativeButtonText, @Nullable PromptDialogListener onNegativeButtonClickListener){
      mNegativeButtonText = negativeButtonText;
      mOnNegativeButtonClickListener = onNegativeButtonClickListener;
      return this;
    }

    public Builder setNeutralButton(String neutralButtonText, @Nullable PromptDialogListener onNeutralButtonClickListener){
      mNeutralButtonText = neutralButtonText;
      mOnNeutralButtonClickListener = onNeutralButtonClickListener;
      return this;
    }
  }

  @IntDef({TYPE_DIALOG_CHOOSE, TYPE_DIALOG_NEUTRAL})
  @Retention(RetentionPolicy.SOURCE)
  public @interface DialogType{

  }

  public interface PromptDialogListener{
    void onViewClick(View view, PromptDialog promptDialog);
  }
}
