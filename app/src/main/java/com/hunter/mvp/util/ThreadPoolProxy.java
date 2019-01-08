package com.hunter.mvp.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : Tell.Tim
 * @description: 线程池
 * @date : 2019-01-03 15:21
 **/
public class ThreadPoolProxy {
    /**
     * 只需创建一次
     */
    ThreadPoolExecutor mExecutor;
    int mCorePoolSize;
    int mMaximumPoolSize;
    long mKeepAliveTime;

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize,
            long keepAliveTime) {
        super();
        mCorePoolSize = corePoolSize;
        mMaximumPoolSize = maximumPoolSize;
        mKeepAliveTime = keepAliveTime;
    }

    /**
     * 双重检查加锁
     *
     * @return ThreadPoolExecutor
     */
    private ThreadPoolExecutor initThreadPoolExecutor() {
        if (mExecutor == null) {
            synchronized (ThreadPoolProxy.class) {
                if (mExecutor == null) {
                    // 毫秒
                    TimeUnit unit = TimeUnit.MILLISECONDS;
                    // 无界队列
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    // 任务数量超过时，丢弃任务，抛出异常
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

                    /*
                     * mCorePoolSize, // 核心线程数
                     *  mMaximumPoolSize,// 最大线程数
                     *  mKeepAliveTime, // 保持时间
                     *  unit, // 保持时间对应的单位
                     *   workQueue,// 缓存队列/
                     *   threadFactory,// 线
                     *   handler// 异常捕获器
                     */
                    mExecutor = new ThreadPoolExecutor(
                            mCorePoolSize,
                            mMaximumPoolSize,
                            mKeepAliveTime,
                            unit,
                            workQueue,
                            threadFactory,
                            handler
                    );
                }
            }
        }
        return mExecutor;
    }

    /**
     * 执行任务
     */
    public void execute(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }

    /**
     * 提交任务
     */
    public Future<?> submit(Runnable task) {
        initThreadPoolExecutor();
        return mExecutor.submit(task);
    }

    /**
     * 移除任务
     */
    public void removeTask(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.remove(task);
    }
}
