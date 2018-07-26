package com.beadwallet.beautymirror.feature.base;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.View;

import com.beadwallet.beautymirror.comminview.dialog.PromptDialog;
import com.beadwallet.beautymirror.comminview.dialog.PromptDialog.Builder;
import com.beadwallet.beautymirror.util.PermissionManagementUtil;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;



@RuntimePermissions
public class PermissionFragment extends android.support.v4.app.Fragment {

  public static final String PERMISSION_PHONE_STATE = "手机状态";
  public static final String PERMISSION_EXTERNAL_STORAGE = "访问外部存储";
  private PromptDialog mPromptDialog;

  @NeedsPermission(Manifest.permission.CAMERA)
  void CameraPermitted() {
  }

  @OnShowRationale(Manifest.permission.CAMERA)
  void cameraRationale(final PermissionRequest request) {
  }

  @OnPermissionDenied(Manifest.permission.CAMERA)
  void cameraDenied() {
  }

  @OnNeverAskAgain(Manifest.permission.CAMERA)
  void cameraNeverAskAgain() {
  }

  public void requestCamera(){
    PermissionFragmentPermissionsDispatcher.CameraPermittedWithCheck(this);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    PermissionFragmentPermissionsDispatcher
        .onRequestPermissionsResult(this, requestCode, grantResults);
  }

  public void requestExternalStorage(){
    PermissionFragmentPermissionsDispatcher.externalStoragePermittedWithCheck(this);
  }

  @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE,
      Manifest.permission.WRITE_EXTERNAL_STORAGE})
  protected void externalStoragePermitted() {
  }

  @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE,
      Manifest.permission.WRITE_EXTERNAL_STORAGE})
  protected void externalStorageRationale(final PermissionRequest request) {
    showRationaleDialog(request, PERMISSION_EXTERNAL_STORAGE);
  }

  @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE,
      Manifest.permission.WRITE_EXTERNAL_STORAGE})
  protected void externalStorageDenied() {
    showDenyOrNeverAskAgainDialog(PERMISSION_EXTERNAL_STORAGE);
  }

  @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE,
      Manifest.permission.WRITE_EXTERNAL_STORAGE})
  protected void externalStorageNeverAskAgain() {
    showDenyOrNeverAskAgainDialog(PERMISSION_EXTERNAL_STORAGE);
  }

  private void showDenyOrNeverAskAgainDialog(String permissionName){
    if(mPromptDialog == null){
      mPromptDialog = new Builder(PromptDialog.TYPE_DIALOG_CHOOSE)
          .setMessage("您拒绝了" + permissionName + "权限, 若要使用完整功能，请到应用权限设置页面打开相应权限")
          .setPositiveButton(
              "OK", new PromptDialog.PromptDialogListener() {
                @Override
                public void onViewClick(View view, PromptDialog promptDialog) {
                  promptDialog.dismissAllowingStateLoss();
                  PermissionManagementUtil.GoToSetting(getActivity());
                }
              }).create();
      mPromptDialog.show(getActivity().getFragmentManager(), getClass().getSimpleName());
    }
  }

  private void showRationaleDialog(final PermissionRequest request, String permissionName) {
    new AlertDialog.Builder(getActivity())
        .setMessage("开启" + permissionName + "权限，以正常使用功能")
        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            //再次执行请求
            request.proceed();
          }
        })
        .show();
  }
}
