package com.lee.kevin.httpnetproject.core.io;

import com.lee.kevin.httpnetproject.builder.RequestParams;
import com.lee.kevin.httpnetproject.core.ContentTypeFactory;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Lee on 2017/1/6.
 */

public class MultipartContent extends HttpContent {

    public MultipartContent(String encode, RequestParams params) {
        super(encode, params);
    }

    @Override
    public void doOutput() throws IOException {
        outputText();
        outputFileFormData();
        outputEnd();
    }

    private void outputText() throws IOException {
        StringBuffer buffer = new StringBuffer();
        Set<String> set = mParams.getTextParams().keySet();
        IdentityHashMap<String, String> texts = mParams.getTextParams();
        for (Iterator<String> iterator = set.iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            String value = texts.get(key);
            buffer.append(END + DATA_TAG + BOUNDARY + END);
            buffer.append("Content-Disposition: form-data; name=\"" + key + "\"");
            buffer.append(END + END);
            buffer.append(value);
        }

        mOutputStream.write(buffer.toString().getBytes(mEncode));

    }

    private void outputFileFormData() throws IOException {
        Set<String> set = mParams.getMultiParams().keySet();
        IdentityHashMap<String, File> fileMap = mParams.getMultiParams();
        for (Iterator<String> iterator = set.iterator(); iterator.hasNext(); ) {
            StringBuffer buffer = new StringBuffer();
            String key = iterator.next();
            File file = fileMap.get(key);
            String fileName = file.getName();
            buffer.append(END + DATA_TAG + BOUNDARY + END);
            buffer.append("Content-Disposition: form-data; name=\"" + key + "\"; filename=\"" + fileName + "\"");
            buffer.append(END);
            buffer.append("Content-Type: " + ContentTypeFactory.getInstance().getContentType(fileName));
            buffer.append(END + END);
            mOutputStream.writeBytes(buffer.toString());
            outputFile(file);
        }

    }

    private void outputFile(File file) throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferout = new byte[1024 * 10];
        while ((bytes = in.read(bufferout)) != -1) {
            mOutputStream.write(bufferout, 0, bytes);
        }
        in.close();
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
        StringBuffer buffer = new StringBuffer();
        intoString(buffer);
        return buffer.substring(0, buffer.length() - 1);
    }
}
