package com.hunter.mvp.demo.model;

import com.hunter.mvp.base.IBaseCallback;

/**
 * @program: MVXdemo
 * @packageName: com.hunter.mvp.app.action
 * @fileName: IModel
 * @author: Tell.Tim
 * @date: 2019/1/7 10:14
 * @description: 处理业务逻辑，这里指数据读写，方法的定义一般不返回数据，通过回调的接口返回数据，以便页面实现动态刷新
 */
public interface IModel {

    void insertModel(ModelBean modelBean);

    void queryAllModel(final IBaseCallback baseCallback);
}
