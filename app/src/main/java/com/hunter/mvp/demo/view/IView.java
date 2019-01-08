package com.hunter.mvp.demo.view;

import com.hunter.mvp.base.IBaseView;
import com.hunter.mvp.demo.model.ModelBean;
import java.util.List;

/**
 * @program: MVXdemo
 * @packageName: com.hunter.mvp.app.action
 * @fileName: Presenter
 * @author: Tell.Tim
 * @date: 2019/1/7 10:14
 * @description: MVP框架中V的角色, 由具体的Activity实现，
 * 此接口中定义的方法，一般只预留给P的回调方法调用，其他的方法不需要暴露出去，例如页面点击事件的回调
 */
public interface IView extends IBaseView {

    /**
     * 页面显示所有model数据
     *
     * @deprecated 需要传递M传递过来的具体参数, 由showAllModels(List < ModelBean > beans)
     */
    @Deprecated
    void showAllModels();

    /**
     * 因为P中去访问M的方法中，一般都有回调方法，为了避免在Activity中直接showAllModels，而需要去实现M中的回调方法，现只需要加载数据然后渲染页面
     *
     * @param beans List<ModelBean>
     */
    void showAllModels(List<ModelBean> beans);

    /**
     * 页面显示无数据
     */
    void showNoModel();
}
