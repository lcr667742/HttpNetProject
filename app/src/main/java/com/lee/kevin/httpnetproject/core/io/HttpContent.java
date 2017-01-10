package com.lee.kevin.httpnetproject.core.io;

import com.lee.kevin.httpnetproject.builder.RequestParams;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Lee
 * on 2017/1/5.
 */

public abstract class HttpContent {
    public static final String BOUNDARY = "http-net";
    public static final String DATA_TAG = "--";
    public static final String END = "\r\n";
    protected String mEncode;
    protected RequestParams mParams;
    protected DataOutputStream mOutputStream;

    public HttpContent(String encode, RequestParams params) {
        this.mEncode = encode == null ? "UTF-8" : encode;
        this.mParams = params;
    }

    public HttpContent(RequestParams params) {
        mParams = params;
    }


    public void setParams(RequestParams params) {
        mParams = params;
    }

    public RequestParams getParams() {
        return mParams;
    }

    public String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, mEncode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setOutputStream(DataOutputStream outputStream) throws IOException {
        this.mOutputStream = outputStream;
        doOutput();
    }


    public abstract void doOutput() throws IOException;

    public abstract String intoString();

    public void outputEnd() throws IOException {
        mOutputStream.writeBytes(END + DATA_TAG + BOUNDARY + DATA_TAG + END);
        mOutputStream.flush();
        mOutputStream.close();
    }

}
