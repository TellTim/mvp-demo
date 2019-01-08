package com.hunter.mvp.base;

/**
 * @program: MVXdemo
 * @packageName: com.hunter.mvp.app.action
 * @fileName: Test
 * @author: Tell.Tim
 * @date: 2019/1/7 10:14
 * @description:  回调方法的接口
 */
public interface IBaseCallback<T> {

    /**
     * 数据请求成功
     *
     * @param data 请求到的数据
     */
    void onSuccess(T data);

    /**
     * 使用网络API接口请求方式时，虽然已经请求成功但是由
     * 于{@code FailureCode}的原因无法正常返回数据。
     *
     * @param failureCode String
     */
    void onFailure(int failureCode);

    /**
     * 请求数据失败，指在请求网络API接口请求方式时，出现无法联网、
     * 缺少权限，内存泄露等原因导致无法连接到请求数据源。
     *
     * @param errorCode String
     */
    void onError(int errorCode);

    /**
     * 当请求数据结束时，无论请求结果是成功，失败或是抛出异常都会执行此方法给用户做处理，通常做网络
     * 请求时可以在此处隐藏“正在加载”的等待控件
     */
    void onComplete();
}
