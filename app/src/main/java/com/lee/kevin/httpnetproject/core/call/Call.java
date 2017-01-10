package com.lee.kevin.httpnetproject.core.call;

import com.lee.kevin.httpnetproject.core.io.Response;

/**
 * Created by Lee
 * on 2017/1/3.
 */

public interface Call {
    void execute(CallBack callBack);

    Response execute();

    void cancel();
}
