package com.xt.mcommonutilsdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xt.m_common_utils.ARuntimePermissionUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ARuntimePermissionUtil.isPermissionsGranted(grantResults)) {
            toast();
        }
    }

    private void requestLocationRuntimePermission() {
        if (ARuntimePermissionUtil.requestPermissions(this, ARuntimePermissionUtil.PERMISSIONS_LOCATION, ARuntimePermissionUtil.RUNTIM_EPERMISSION_REQUEST_CODE_LOCATION)) {
            toast();
        }
    }

    private void toast() {
        Toast.makeText(this, "定位权限获取成功", Toast.LENGTH_SHORT).show();
    }

    public void location(View view) {
        requestLocationRuntimePermission();
    }
}
