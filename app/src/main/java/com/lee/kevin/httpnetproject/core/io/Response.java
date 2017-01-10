package com.lee.kevin.httpnetproject.core.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Lee
 * on 2017/1/3.
 */

public class Response {
    private Map<String, List<String>> mHeaders;
    private int mCode;
    private String mBody;
    private InputStream mInputStream;
    private String mEncode;

    public Response(Map<String, List<String>> headers, int code, InputStream inputStream, String encode) {
        mHeaders = headers;
        mCode = code;
        mInputStream = inputStream;
        mEncode = encode;
    }

    public int getCode() {
        return mCode;
    }

    public String getBody() {
        if (mBody == null) {
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                int b;
                while ((b = mInputStream.read()) != -1) {
                    os.write(b);
                }
                mBody = new String(os.toByteArray(), mEncode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mBody;
    }

    public InputStream toStream() {
        return mInputStream;
    }

    public Map<String, List<String>> getHeaders() {
        return mHeaders;
    }
}
