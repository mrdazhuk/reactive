package com.example.yuriydazhuk.rxexample.api;

import java.util.List;

/**
 * Created by Yuriy Dazhuk on 29.07.2015.
 */
public class SearchResponse {
    public ResponseData responseData;
    public String responseDetails;
    public int responseStatus;

    public class ResponseData {
        public List<Result> results;
    }

    public class Result {
        public String url;
        public String title;
        public String content;
    }
}
