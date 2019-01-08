package com.hunter.mvp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;


/**
 * @program: MVXdemo
 * @packageName: com.hunter.mvp.app.action
 * @fileName: Test
 * @author: Tell.Tim
 * @date: 2019/1/7 10:14
 * @description: 自定义P的基类，通过attachView的方式将V绑定到P中.
 */
public class BasePresenter<V> {

    /**
     * MVP中P持有的V引用，在P中可以调用getView的方法来拿到V定义的接口
     */
    private Reference<V> mViewRef;

    /**
     * 建立Presenter与View连接
     */
    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    /**
     * 判断Presenter与View的连接
     *
     * @return boolean
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 断开Presenter与View的连接
     */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 获取Presenter绑定的View,是MVP中P调用V的重要纽带
     */
    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }
}
