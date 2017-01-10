package com.lee.kevin.httpnetproject.builder;

import com.lee.kevin.httpnetproject.core.io.FromContent;
import com.lee.kevin.httpnetproject.core.io.HttpContent;
import com.lee.kevin.httpnetproject.core.io.MultipartContent;

/**
 * Created by Lee
 * on 2016/12/30.
 */

public class Request {

    private String mUrl;
    private String mMethod;
    private String mEncode;
    private int mTimeout;

    private String mHost;
    private int mPort;

    private Headers mHeaders;
    private RequestParams mRequestParams;
    private HttpContent mHttpContent;

    private Request(Builder builder) {
        this.mUrl = builder.mUrl;
        this.mMethod = builder.mMethod;
        this.mEncode = builder.mEncode;
        this.mTimeout = builder.mTimeout;
        this.mHost = builder.mHost;
        this.mPort = builder.mPort;
        this.mHeaders = builder.mHeaders.build();
        this.mRequestParams = builder.mRequestParams;
        this.mHttpContent = builder.mHttpContent;
    }


    public String url() {
        return mUrl;
    }

    public String method() {
        return mMethod;
    }

    public String encode() {
        return mEncode;
    }

    public int timeout() {
        return mTimeout;
    }

    public String host() {
        return mHost;
    }

    public int port() {
        return mPort;
    }

    public Headers headers() {
        return mHeaders;
    }

    public RequestParams params() {
        return mRequestParams;
    }

    public HttpContent content() {
        return this.mHttpContent;
    }

    public static final class Builder {

        private String mUrl;
        private String mMethod;
        private String mEncode;
        private int mTimeout;

        private RequestParams mRequestParams;
        private Headers.Builder mHeaders;

        private String mHost;
        private int mPort;

        private HttpContent mHttpContent;

        public Builder() {
            this.mMethod = "GET";
            this.mEncode = "UTF-8";
            this.mTimeout = 13000;
            this.mHeaders = new Headers.Builder();
        }

        public Builder url(String url) {
            this.mUrl = url;
            return this;
        }

        public Builder method(String method) {
            this.mMethod = method;
            return this;
        }

        public Builder encode(String encode) {
            this.mEncode = encode;
            return this;
        }

        public Builder timeout(int timeout) {
            this.mTimeout = timeout;
            if (mTimeout <= 0) mTimeout = 13000;
            return this;
        }

        public Builder params(RequestParams params) {
            if (params == null) throw new NullPointerException("params can not be null");
            this.mRequestParams = params;
            return this;
        }

        public Builder headers(Headers.Builder headers) {
            this.mHeaders = headers;
            return this;
        }

        public Builder content(HttpContent content) {
            if (content == null) throw new NullPointerException("content can not be null");
            this.mHttpContent = content;
            return this;
        }

        public Builder proxy(String host, int port) {
            if (host == null) throw new NullPointerException("host can not be null");
            this.mHost = host;
            this.mPort = port;
            return this;
        }

        public Request build() {
            if (mHttpContent == null && mRequestParams != null) {
                if (mRequestParams.getMultiParams() != null) {
                    mHttpContent = new MultipartContent(mEncode, mRequestParams);
                } else {
                    mHttpContent = new FromContent(mEncode, mRequestParams);
                }
            }
            return new Request(this);
        }
    }
}
