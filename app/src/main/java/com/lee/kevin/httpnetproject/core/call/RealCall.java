package com.lee.kevin.httpnetproject.core.call;

import com.lee.kevin.httpnetproject.HttpNetClient;
import com.lee.kevin.httpnetproject.builder.Request;
import com.lee.kevin.httpnetproject.core.io.Response;

/**
 * Created by Lee
 * on 2017/1/5.
 */

public class RealCall implements Call {

    private HttpNetClient mClient;
    private Request mRequest;
    private AsyncCall mAsyncCall;

    public RealCall(HttpNetClient client, Request request) {
        mClient = client;
        mRequest = request;
    }

    @Override
    public void execute(CallBack callBack) {
        if (mAsyncCall == null)
            mAsyncCall = new AsyncCall(mRequest, callBack);
        mClient.dispatcher().enqueue(mAsyncCall);
    }

    @Override
    public Response execute() {
        return null;
    }

    @Override
    public void cancel() {

    }
}
