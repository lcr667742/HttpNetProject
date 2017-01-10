package com.lee.kevin.httpnetproject.core.io;

import com.lee.kevin.httpnetproject.builder.RequestParams;

import java.io.IOException;

/**
 * Created by Lee on 2017/1/6.
 */

public class JsonContent extends HttpContent {
    private String mJson;

    public JsonContent(String json) {
        super("UTF-8", null);
        this.mJson = json;
    }

    public JsonContent(String json, String encode) {
        super(encode, null);
        this.mJson = json;
    }

    public String getJson() {
        return mJson;
    }

    public void setJson(String json) {
        mJson = json;
    }

    @Override
    public void doOutput() throws IOException {
        mOutputStream.write(mJson.getBytes(mEncode));
    }

    @Override
    public String intoString() {
        return mJson;
    }
}
