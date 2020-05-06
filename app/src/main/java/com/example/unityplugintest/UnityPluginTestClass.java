package com.example.unityplugintest;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;

public class UnityPluginTestClass {
    private static UnityPluginTestClass m_instance;
    private Context context;

    public static UnityPluginTestClass instance() {
        if (m_instance == null) {
            m_instance = new UnityPluginTestClass();
        }
        return m_instance;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    private void ShowToast(String toastStr) {
        Toast.makeText(this.context, toastStr, Toast.LENGTH_LONG).show();
    }

    private void AndroidVersionCheck(String objName, String objMethod){
        UnityPlayer.UnitySendMessage(objName, objMethod, "My Android Version: " + Build.VERSION.RELEASE);
    }
}
