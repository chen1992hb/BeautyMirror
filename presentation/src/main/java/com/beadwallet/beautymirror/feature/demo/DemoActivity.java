package com.beadwallet.beautymirror.feature.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.beadwallet.beautymirror.R;
import com.beadwallet.beautymirror.di.module.demo.DemoActivityModule;
import com.beadwallet.beautymirror.feature.base.BaseActivity;
import com.beadwallet.beautymirror.model.DemoModel;

import javax.inject.Inject;

public class DemoActivity  extends BaseActivity implements  IDemoView{
    public static void start(Context context) {
        Intent starter = new Intent(context, DemoActivity.class);
        context.startActivity(starter);
    }
    @Inject
    DemoPresenter mPresenter;

    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initdefaultUI(Bundle savedInstanceState) {
        mPresenter.request("123465");
    }


    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void requestError(String msg) {
        Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSucess(DemoModel data) {
        Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartLoading() {
        Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEndLoading() {
        Toast.makeText(getApplicationContext(),"4",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void setupActivityComponent() {
        getGlobleApplication().getBaseComponent().plus(new DemoActivityModule(this)).inject(this);
    }


}
