package com.lee.kevin.httpnetproject.core.connection;

import com.lee.kevin.httpnetproject.builder.Headers;
import com.lee.kevin.httpnetproject.builder.Request;
import com.lee.kevin.httpnetproject.builder.RequestParams;
import com.lee.kevin.httpnetproject.core.call.CallBack;
import com.lee.kevin.httpnetproject.core.io.HttpContent;
import com.lee.kevin.httpnetproject.core.io.IO;
import com.lee.kevin.httpnetproject.core.io.Response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Lee on
 * 2017/1/5.
 */

public abstract class Connection {

    protected URLConnection mURLConnection;
    protected Request mRequest;
    protected DataOutputStream mOutputStream;
    protected InputStream mInputStream;


    public void connect(Request request, CallBack callBack) {
        this.mRequest = request;
        try {
            String host = mRequest.host();
            String httpUrl = mRequest.url();
            String method = mRequest.method();
            if ("GET".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method)) {
                if (mRequest.params() != null && mRequest.params().getTextParams() != null) {
                    String paramsStr = mRequest.content().intoString();
                    httpUrl = httpUrl + (httpUrl.endsWith("?") ? paramsStr : "?" + paramsStr);

                }
            }
            URL url = new URL(httpUrl);
            if (host == null)
                mURLConnection = url.openConnection();
            else
                mURLConnection = url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, mRequest.port())));

            initConnection();
            initHeaders();
            initMethod(method);

            if ("POST".equalsIgnoreCase(method))
                post(callBack);
            else if ("GET".equals(method))
                get(callBack);
            else if ("PUT".equals(method))
                put(callBack);
            else if ("DELETE".equals(method))
                delete(callBack);
            mInputStream = mURLConnection.getInputStream();
            callBack.onResponse(new Response(mURLConnection.getHeaderFields(), getResponseCode(), mInputStream, mRequest.encode()));

        } catch (IOException e) {
            e.printStackTrace();
            callBack.onFailure(e);
        } finally {

            finish();
        }

    }

    private void get(CallBack callBack) throws IOException {

    }

    private void post(CallBack callBack) throws IOException {
        mURLConnection.setDoOutput(true);
        mURLConnection.setRequestProperty("Content-Type", getContentType());
        mOutputStream = new DataOutputStream(mURLConnection.getOutputStream());
        mRequest.content().setOutputStream(mOutputStream);

    }

    protected void put(CallBack callBack) throws IOException {

    }

    protected void delete(CallBack callBack) throws IOException {

    }

    protected abstract int getResponseCode() throws IOException;

    protected abstract void initMethod(String method) throws ProtocolException;

    protected abstract void convertConnect();


    /**
     * 初始化基础链接
     */
    protected void initConnection() {

        convertConnect();
        mURLConnection.setUseCaches(true);
        mURLConnection.setConnectTimeout(mRequest.timeout());
        mURLConnection.setRequestProperty("Accept-Language", "zh-CN");
        mURLConnection.setRequestProperty("Charset", mRequest.encode());
        mURLConnection.setRequestProperty("Connection", "Keep-Alive");

    }

    /**
     * 初始化头部
     */
    protected void initHeaders() {
        Headers headers = mRequest.headers();
        if (headers != null) {
            Map<String, List<String>> maps = headers.getHeaders();
            if (maps != null) {
                Set<String> sets = maps.keySet();
                for (Iterator<String> iterator = sets.iterator(); iterator.hasNext(); ) {
                    String key = iterator.next();
                    for (String value : maps.get(key)) {
                        mURLConnection.addRequestProperty(key, value);
                    }
                }
            }

        }

    }

    protected String getContentType() {
        RequestParams params = mRequest.params();
        return params != null ?
                (params.getMultiParams() != null ? "multipart/from-data; boundary=\"" + HttpContent.BOUNDARY + "\"" : "application/x-www-form-urlencoded")
                : "application/json; charset=utf-8";
    }

    protected void finish() {
        IO.close(mOutputStream, mInputStream);
    }

}
