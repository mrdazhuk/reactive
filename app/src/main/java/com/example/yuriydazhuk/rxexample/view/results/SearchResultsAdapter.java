package com.example.yuriydazhuk.rxexample.view.results;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yuriydazhuk.rxexample.R;
import com.example.yuriydazhuk.rxexample.api.SearchResponse;

import java.util.List;

import static com.example.yuriydazhuk.rxexample.R.layout.view_result_item;

/**
 * Created by Yuriy Dazhuk on 29.07.2015.
 */
public class SearchResultsAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater;
	private List<SearchResponse.Result> searchResponses;

	public SearchResultsAdapter(Context context, List<SearchResponse.Result> searchResponses) {
		this.layoutInflater = LayoutInflater.from(context);
		this.searchResponses = searchResponses;
	}

	@Override
	public int getCount() {
		return this.searchResponses.size();
	}

	@Override
	public Object getItem(int position) {
		return this.searchResponses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = this.layoutInflater.inflate(view_result_item, null);

			holder = new ViewHolder();
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
			holder.tvUrl = (TextView) convertView.findViewById(R.id.tvUrl);
			holder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		SearchResponse.Result result = (SearchResponse.Result) getItem(position);

		holder.tvTitle.setText(Html.fromHtml(result.title));
		holder.tvUrl.setText(result.url);
		holder.tvContent.setText(result.content);

		return convertView;
	}

	public static class ViewHolder {
		TextView tvTitle;
		TextView tvUrl;
		TextView tvContent;
	}
}
