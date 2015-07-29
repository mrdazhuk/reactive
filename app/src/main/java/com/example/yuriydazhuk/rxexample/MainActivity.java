package com.example.yuriydazhuk.rxexample;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yuriydazhuk.rxexample.api.ApiSearch;
import com.example.yuriydazhuk.rxexample.api.SearchResponse;
import com.example.yuriydazhuk.rxexample.view.results.SearchResultsAdapter;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.widget.WidgetObservable;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {
    @InjectView(R.id.etSearch)
    private EditText etSearch;

    @InjectView(R.id.lvFoundResults)
    private ListView lvFoundResult;

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
    }

    private SearchResponse doSeachRequest(String searchString) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://ajax.googleapis.com/ajax/services/search")
                .build();

        return restAdapter.create(ApiSearch.class).doSearch(1.0, searchString, 8);
    }
}
