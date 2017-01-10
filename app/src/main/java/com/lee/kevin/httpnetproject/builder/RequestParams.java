package com.lee.kevin.httpnetproject.builder;

import java.io.File;
import java.util.IdentityHashMap;

/**
 * Created by Lee on 2016/12/30.
 */

public final class RequestParams {

    private IdentityHashMap<String, String> textParams;
    private IdentityHashMap<String, File> multiParams;

    public RequestParams() {
        textParams = new IdentityHashMap<>();
    }

    public RequestParams put(String name, String value) {
        textParams.put(name, value);
        return this;
    }

    public RequestParams put(String name, int value) {
        textParams.put(name, String.valueOf(value));
        return this;
    }

    public RequestParams put(String name, long value) {
        textParams.put(name, String.valueOf(value));
        return this;
    }

    public RequestParams put(String name, double value) {
        textParams.put(name, String.valueOf(value));
        return this;
    }

    public RequestParams put(String name, float value) {
        textParams.put(name, String.valueOf(value));
        return this;
    }

    public RequestParams put(String name, byte value) {
        textParams.put(name, String.valueOf(value));
        return this;
    }

    public RequestParams put(String name, boolean value) {
        textParams.put(name, String.valueOf(value));
        return this;
    }

    public RequestParams putFile(String name, File file) {
        if (multiParams == null)
            multiParams = new IdentityHashMap<>();
        if (!file.exists())
            throw new IllegalArgumentException("request param file not find exception");
        multiParams.put(name, file);
        return this;
    }

    public RequestParams putFile(String name, String fileName) {
        return putFile(name, new File(fileName));
    }


    public IdentityHashMap<String, String> getTextParams() {
        return textParams;
    }

    public IdentityHashMap<String, File> getMultiParams() {
        return multiParams;
    }
}
