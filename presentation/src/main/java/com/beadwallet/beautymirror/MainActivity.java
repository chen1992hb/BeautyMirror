package com.beadwallet.beautymirror;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beadwallet.beautymirror.feature.base.BaseActivity;
import com.beadwallet.beautymirror.feature.demo.DemoActivity;
import com.beadwallet.domain.Serializer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tv_show)
    TextView nameTv;
    @Inject
    protected Serializer mSerializer;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    @OnClick(R.id.tv_show)
    public void onViewClick(View v){
        DemoActivity.start(MainActivity.this);

    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initdefaultUI(Bundle savedInstanceState) {
        nameTv.setText(stringFromJNI());
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    @Override
    protected void setupActivityComponent() {

    }

   /* @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }*/


}
