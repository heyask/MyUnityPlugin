package com.example.myunityplugin

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.unity3d.player.UnityPlayer

class MyUnityPluginBridge {
    private val TAG = "MyUnityPluginBridge"
    private var mCallback: MyUnityPluginCallback? = null
    private var mContext: Context? = null
    fun GetCallbackHandler(): MyUnityPluginCallback? {
        return mCallback
    }

    private fun setContext(context: Context) {
        Log.w(TAG, "setContext Called!")
        mContext = context
    }

    fun Initialize(callback: MyUnityPluginCallback?): Boolean {
        Log.w(TAG, "Initialize Called!")
        return if (mCallback == null) {
            mCallback = callback
            mCallback!!.OnLoad()
            Log.w(TAG, "MyUnityPluginBridge has initialized!")
            true
        } else {
            Log.w(TAG, "MyUnityPluginBridge already initialized!")
            false
        }
    }

    private fun ShowToast(toastStr: String) {
        Toast.makeText(mContext, toastStr, Toast.LENGTH_LONG).show()
        val callback =
            instance()!!.GetCallbackHandler()
        Log.e(TAG, "callbackTestFunc1 in android1")
        if (callback != null) {
            Log.e(TAG, "callbackTestFunc1 in android2")
            callback.OnCallTestFunc1(toastStr)
        }
    }

    private fun AndroidVersionCheck(objName: String, objMethod: String) {
        val callback =
            instance()!!.GetCallbackHandler()
        Log.e(TAG, "callbackTestFunc2 in android1")
        if (callback != null) {
            Log.e(TAG, "callbackTestFunc2 in android2")
            callback.OnCallTestFunc2(11111)
        }
        UnityPlayer.UnitySendMessage(
            objName,
            objMethod,
            "My Android Version: " + Build.VERSION.RELEASE
        )
    }

    companion object {
        private var mInstance: MyUnityPluginBridge? = null

        @JvmStatic
        fun instance(): MyUnityPluginBridge? {
            if (mInstance == null) {
                mInstance = MyUnityPluginBridge()
            }
            return mInstance
        }
    }
}