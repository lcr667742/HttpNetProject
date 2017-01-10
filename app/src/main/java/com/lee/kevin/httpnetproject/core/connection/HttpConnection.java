package com.lee.kevin.httpnetproject.core.connection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

/**
 * Created by Lee on 2017/1/6.
 */

public class HttpConnection extends Connection {

    private HttpURLConnection mConnection;

    @Override
    protected void convertConnect() {
        mConnection = (HttpURLConnection) mURLConnection;
    }

    @Override
    protected void initMethod(String method) throws ProtocolException {
        mConnection.setRequestMethod(method);
    }

    @Override
    protected int getResponseCode() throws IOException {
        return mConnection.getResponseCode();
    }


    @Override
    protected void finish() {
        super.finish();
        mConnection.disconnect();
    }
}
