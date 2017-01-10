package com.lee.kevin.httpnetproject.core.call;

import com.lee.kevin.httpnetproject.builder.Request;
import com.lee.kevin.httpnetproject.core.connection.Connection;
import com.lee.kevin.httpnetproject.core.connection.HttpConnection;
import com.lee.kevin.httpnetproject.core.connection.HttpsConnection;

/**
 * Created by Lee on 2017/1/6.
 */

public class AsyncCall implements Runnable {

    private Request mRequest;
    private CallBack mCallBack;
    private Connection mConnection;

    public AsyncCall(Request request, CallBack callBack) {
        mRequest = request;
        mCallBack = callBack;
        mConnection = request.url().startsWith("https") ? new HttpsConnection() : new HttpConnection();
    }

    @Override
    public void run() {
        mConnection.connect(mRequest, mCallBack);
    }

    public CallBack getCallBack() {
        return mCallBack;
    }

    public void setCallBack(CallBack callBack) {
        mCallBack = callBack;
    }

    public Request getRequest() {
        return mRequest;
    }

    public void setRequest(Request request) {
        mRequest = request;
    }

    public Connection getConnection() {
        return mConnection;
    }

    public void setConnection(Connection connection) {
        mConnection = connection;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof AsyncCall)
            return mRequest.url().equalsIgnoreCase(((AsyncCall) obj).getRequest().url());
        return super.equals(obj);
    }
}
