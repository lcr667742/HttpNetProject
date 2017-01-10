package com.lee.kevin.httpnetproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lee.kevin.httpnetproject.builder.Request;
import com.lee.kevin.httpnetproject.builder.RequestParams;
import com.lee.kevin.httpnetproject.core.call.CallBack;
import com.lee.kevin.httpnetproject.core.io.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        httpNet();
    }


    public void httpNet() {
        HttpNetClient client = new HttpNetClient();

        RequestParams params = new RequestParams()
                .put("appname", "baisibudejie")
                .put("client", "android")
                .put("udid", "867068022424518")
                .put("mac", "f4%3A8b%3A32%3A71%3A2d%3A85");

        Request request = new Request.Builder()
                .method("GET")
                .params(params)
                .url("http://s.budejie.com/topic/tag-topic/64/hot/budejie-android-6.6.0/0-20.json")
                .build();

        client.newCall(request).execute(new CallBack() {
            @Override
            public void onResponse(Response response) {
                if (response != null) {
                    String test = response.getBody();
                }
            }

            @Override
            public void onFailure(Exception e) {


            }
        });
    }


}
