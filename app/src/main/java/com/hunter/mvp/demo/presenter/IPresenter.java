package com.hunter.mvp.demo.presenter;

import android.support.annotation.NonNull;
import com.hunter.mvp.base.IBaseCallback;
import com.hunter.mvp.demo.model.ModelBean;
import java.util.List;

/**
 * @program: MVXdemo
 * @packageName: com.hunter.mvp.app.action
 * @fileName: IPresenter
 * @author: Tell.Tim
 * @date: 2019/1/7 10:14
 * @description:  MVP框架中P的接口定义,一般是V来调用P
 */
public interface IPresenter {
    /**
     * 保存数据
     *
     * @param field String
     */
    void saveModel(@NonNull String field);

    /**
     * 加载数据
     * @param forceUpdate boolean
     */
    void loadModels(boolean forceUpdate);

    /**
     * 以回调的方式拿到数据
     * @param callback IBaseCallback<List<ModelBean>>
     * @deprecated 回调方法不再暴漏出去，由loadModels(boolean forceUpdate)替代
     */
    @Deprecated
    void getModels(final IBaseCallback<List<ModelBean>> callback);
}

