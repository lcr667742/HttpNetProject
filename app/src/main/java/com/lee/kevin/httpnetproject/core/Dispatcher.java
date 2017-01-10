package com.lee.kevin.httpnetproject.core;

import com.lee.kevin.httpnetproject.core.call.AsyncCall;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lee
 * on 2017/1/5.
 */

public final class Dispatcher {


    private ExecutorService mExecutorService;

    public Dispatcher() {
        this.mExecutorService = new ThreadPoolExecutor(5, 64, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5));

    }

    public void enqueue(AsyncCall call) {
        mExecutorService.execute(call);
    }

}
