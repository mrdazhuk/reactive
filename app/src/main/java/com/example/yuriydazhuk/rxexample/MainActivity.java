package com.example.yuriydazhuk.rxexample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yuriydazhuk.rxexample.api.ApiSearch;
import com.example.yuriydazhuk.rxexample.api.SearchResponse;
import com.example.yuriydazhuk.rxexample.view.results.ExampleViewInject;
import com.example.yuriydazhuk.rxexample.view.results.SearchResultsAdapter;
import com.google.inject.Inject;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import rx.Observable;
import rx.android.app.AppObservable;
import rx.android.content.ContentObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.view.OnClickEvent;
import rx.android.view.ViewObservable;
import rx.android.widget.WidgetObservable;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {
    @InjectView(R.id.etSearch)
    private EditText etSearch;

    @InjectView(R.id.lvFoundResults)
    private ListView lvFoundResult;

    @InjectView(R.id.btDo)
    private Button btDo;

    @Inject
    private Bindable bindable;

    @InjectView(R.id.view)
    private ExampleViewInject exampleViewInject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WidgetObservable.text(this.etSearch)
                .map((text) -> text.text().toString())
                .filter((text) -> text.length() >= 2)
                .debounce(2, TimeUnit.SECONDS)
                .map((text) -> doSeachRequest(text))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (result) -> this.lvFoundResult.setAdapter(new SearchResultsAdapter(this, result.responseData.results)),
                        (error) -> Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show());

        Observable<OnClickEvent> clickEventObservable = ViewObservable.clicks(this.btDo);
        clickEventObservable.subscribe((param) -> Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show());

        SharedPreferences sharedPreferences = getPreferences(MODE_APPEND);

        ContentObservable.fromSharedPreferencesChanges(sharedPreferences).subscribe(
                (e) -> Toast.makeText(MainActivity.this, e, Toast.LENGTH_SHORT).show());

        sharedPreferences.edit().putString("Tqest", "true").commit();
    }

    private SearchResponse doSeachRequest(String searchString) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://ajax.googleapis.com/ajax/services/search")
                .build();

        return restAdapter.create(ApiSearch.class).doSearch(1.0, searchString, 8);
    }
}
