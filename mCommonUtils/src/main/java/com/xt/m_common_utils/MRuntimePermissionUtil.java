package com.xt.m_common_utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Created by xuti on 2018/8/24.
 * 请求运行时权限，必须先在清单文件配置相关权限
 */
public class MRuntimePermissionUtil {
    //BLE权限
    public static final int RUNTIM_EPERMISSION_REQUEST_CODE_LOCATION = 0x004;
    public static final String[] PERMISSIONS_LOCATION = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };

    //相机权限
    public static final int RUNTIM_EPERMISSION_REQUEST_CODE_CAMERA = 0x005;
    public static final String[] PERMISSIONS_CAMERA = {
            Manifest.permission.CAMERA
    };

    public static boolean requestPermissions(Activity activity, String[] permissions, int requestCode) {
        ArrayList<String> per = new ArrayList<>();
        for (String permission : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(activity, permission)) {
                per.add(permission);
            }
        }
        if (per.size() > 0) {
            String[] p = new String[per.size()];
            ActivityCompat.requestPermissions(activity, per.toArray(p), requestCode);
            return false;
        } else {
            return true;
        }
    }

    public static boolean requestPermissions(Fragment fragment, String[] permissions, int requestCode) {
        ArrayList<String> per = new ArrayList<>();
        for (String permission : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(fragment.getContext(), permission)) {
                per.add(permission);
            }
        }
        if (per.size() > 0) {
            String[] p = new String[per.size()];
            fragment.requestPermissions(per.toArray(p), requestCode);
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPermissionsGranted(int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }
}
