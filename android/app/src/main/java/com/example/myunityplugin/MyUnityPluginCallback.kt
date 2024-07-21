package com.example.myunityplugin

interface MyUnityPluginCallback {
    fun OnLoad()
    fun OnCallTestFunc1(str: String?)
    fun OnCallTestFunc2(num: Int)
}