package com.example.yuriydazhuk.rxexample;

import com.google.inject.AbstractModule;

import roboguice.RoboGuice;

/**
 * Created by Yuriy Dazhuk on 31.07.2015.
 */
public class BindingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Bindable.class).to(BindableExt.class);
    }
}
