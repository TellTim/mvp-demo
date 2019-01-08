/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hunter.mvp.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

/**
 * Global executor pools for the whole application.
 * <p>
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */
public class AppExecutors {

    static ThreadPoolProxy mNetWorkPool;
    static ThreadPoolProxy mDiskIOPool;
    static Executor mMainThread;

    /**
     * 得到一个网络的线程池
     */
    public static ThreadPoolProxy getNetWorkPool() {
        if (mNetWorkPool == null) {
            synchronized (AppExecutors.class) {
                if (mNetWorkPool == null) {
                    mNetWorkPool = new ThreadPoolProxy(5, 5, 3000);
                }
            }
        }
        return mNetWorkPool;
    }

    /**
     * 得到一个IO的线程池
     */
    public static ThreadPoolProxy getDiskIOPool() {
        if (mDiskIOPool == null) {
            synchronized (AppExecutors.class) {
                if (mDiskIOPool == null) {
                    mDiskIOPool = new ThreadPoolProxy(3, 3, 3000);
                }
            }
        }
        return mDiskIOPool;
    }

    /**
     * UI主线程
     *
     * @return Executor
     */
    public static Executor getMainThread() {
        if (mMainThread == null) {
            mMainThread = new MainThreadExecutor();
        }
        return mMainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
