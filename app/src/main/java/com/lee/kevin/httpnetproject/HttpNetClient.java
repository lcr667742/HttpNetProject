package com.lee.kevin.httpnetproject;

import com.lee.kevin.httpnetproject.builder.Request;
import com.lee.kevin.httpnetproject.core.Dispatcher;
import com.lee.kevin.httpnetproject.core.call.Call;
import com.lee.kevin.httpnetproject.core.call.RealCall;

/**
 * Created by Lee
 * on 2017/1/6.
 */

public final class HttpNetClient {
    private Dispatcher mDispatcher;

    public HttpNetClient() {
        mDispatcher = new Dispatcher();
    }

    public Call newCall(Request request) {
        return new RealCall(this, request);
    }

    public Dispatcher dispatcher() {
        return mDispatcher;
    }
}
