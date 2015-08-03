package com.example.yuriydazhuk.rxexample.view.results;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.yuriydazhuk.rxexample.R;

import roboguice.RoboGuice;
import roboguice.inject.InjectView;
import roboguice.inject.RoboInjector;
import rx.android.view.ViewObservable;

/**
 * Created by Yuriy Dazhuk on 31.07.2015.
 */
public class ExampleViewInject extends LinearLayout {
    @InjectView(R.id.button)
    private Button button;

    public ExampleViewInject(Context context) {
        super(context);
        init(context);
    }

    public ExampleViewInject(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public ExampleViewInject(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_example, this);
        RoboGuice.getInjector(context).injectMembers(this);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        ViewObservable.clicks(this.button).subscribe(v -> Log.e(ExampleViewInject.class.getSimpleName(), v.toString()));
    }
}
