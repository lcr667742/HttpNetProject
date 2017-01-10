package com.lee.kevin.httpnetproject.builder;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

/**
 * Created by Lee on 2016/12/30.
 */

public class Headers {

    private IdentityHashMap<String, List<String>> mHeaders;

    public IdentityHashMap<String, List<String>> getHeaders() {
        return mHeaders;
    }

    public void setHeaders(IdentityHashMap<String, List<String>> headers) {
        this.mHeaders = headers;
    }

    public Headers(Builder builder) {
        this.mHeaders = builder.mHeaders;
    }

    public static final class Builder {

        private IdentityHashMap<String, List<String>> mHeaders;

        public Builder() {
            mHeaders = new IdentityHashMap<>();
        }

        public Builder addBuilder(String name, String value) {
            checkNotNull(name, value);
            if (mHeaders.containsKey(name)) {
                if (mHeaders.get(name) == null) mHeaders.put(value, new ArrayList<String>());
                mHeaders.get(name).add(value);
            } else {
                List<String> h = new ArrayList<>();
                h.add(value);
                mHeaders.put(name, h);
            }
            return this;
        }

        private void checkNotNull(String name, String value) {
            if (name == null) throw new NullPointerException("name can not be null");
            if (value == null) throw new NullPointerException("value can not be null");
        }

        public Headers build() {
            return new Headers(this);
        }
    }


}
