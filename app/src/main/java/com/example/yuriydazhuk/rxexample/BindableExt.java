package com.example.yuriydazhuk.rxexample;

import android.util.Log;

/**
 * Created by Yuriy Dazhuk on 31.07.2015.
 */
public class BindableExt implements Bindable {
    @Override
    public void doSomething() {
        Log.e("TEST", "Do");
    }
}
