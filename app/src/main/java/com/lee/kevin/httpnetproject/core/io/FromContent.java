package com.lee.kevin.httpnetproject.core.io;

import com.lee.kevin.httpnetproject.builder.RequestParams;

import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Lee
 * on 2017/1/6.
 */

public class FromContent extends HttpContent {

    public FromContent(String encode, RequestParams params) {
        super(encode, params);
    }

    @Override
    public void doOutput() throws IOException {
        StringBuffer buffer = new StringBuffer();
        intoString(buffer);
        mOutputStream.write(buffer.substring(0, buffer.length() - 1).getBytes(mEncode));
    }

    private void intoString(StringBuffer buffer) {
        Set<String> set = mParams.getTextParams().keySet();
        IdentityHashMap<String, String> texts = mParams.getTextParams();
        for (Iterator<String> iterator = set.iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            String value = texts.get(key);
            buffer.append(key + "=" + value + "&");
        }
    }

    @Override
    public String intoString() {
        if (mParams.getTextParams() == null || mParams.getTextParams().size() == 0)
            return "";
        StringBuffer buffer = new StringBuffer();
        intoString(buffer);
        return buffer.substring(0, buffer.length() - 1);
    }
}
