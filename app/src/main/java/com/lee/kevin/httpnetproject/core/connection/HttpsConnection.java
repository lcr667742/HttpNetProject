package com.lee.kevin.httpnetproject.core.connection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Lee
 * on 2017/1/10.
 */

public class HttpsConnection extends Connection {

    private HttpsURLConnection mConnection;

    public HttpsConnection() {
    }

    @Override
    protected int getResponseCode() throws IOException {
        return mConnection.getResponseCode();
    }

    @Override
    protected void initMethod(String method) throws ProtocolException {

        mConnection.setRequestMethod(method);
    }

    @Override
    protected void convertConnect() {
        mConnection = (HttpsURLConnection) mURLConnection;
    }

    @Override
    protected void finish() {
        super.finish();
        mConnection.disconnect();
    }
}
