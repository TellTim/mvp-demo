package com.hunter.mvp.demo.presenter;

import com.hunter.mvp.base.BasePresenter;
import com.hunter.mvp.base.IBaseCallback;
import com.hunter.mvp.demo.model.IModel;
import com.hunter.mvp.demo.model.Model;
import com.hunter.mvp.demo.model.ModelBean;
import com.hunter.mvp.demo.view.IView;
import java.util.List;

/**
 * @program: MVXdemo
 * @packageName: com.hunter.mvp.app.action
 * @fileName: Presenter
 * @author: Tell.Tim
 * @date: 2019/1/7 10:14
 * @description: MVP框架中P的角色,继承BasePresenter，持有V并包含M的成员变量
 */
public class Presenter extends BasePresenter<IView> implements IPresenter {

    /**
     * 持有的MVP中的M对象
     */
    private IModel model;

    /**
     * 是否第一次加载
     */
    private boolean mFirstLoad = true;

    /**
     * 构造方法，需实例化成员变量M
     */
    public Presenter() {
        this.model = new Model();
    }

    /**
     * 保存数据
     *
     * @param field String
     */
    @Override
    public void saveModel(String field) {
        ModelBean modelBean = new ModelBean(field);
        //调用M中的方法
        model.insertModel(modelBean);

        //此处可优化，用来增量更新数据库
        //调用V中的方法
        /*getView().showAllModels();*/
        loadModels(true);
    }

    /**
     * 加载数据，此方法将实现Model中要求回调方法
     *
     * @param forceUpdate boolean 是否强制刷新
     */
    @Override
    public void loadModels(boolean forceUpdate) {
        loadModels(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadModels(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            getView().showWaitingDialog("正在加载数据");
        }

        if (forceUpdate) {
           //此处用来清理缓存
        }

        model.queryAllModel(new IBaseCallback<List<ModelBean>>() {
            /**
             * 数据请求成功
             *
             * @param data 请求到的数据
             */
            @Override
            public void onSuccess(List<ModelBean> data) {
                if(showLoadingUI){
                    getView().hideWaitingDialog();
                }
                //调用V中的方法,来显示数据
                getView().showAllModels(data);
            }

            /**
             * 使用网络API接口请求方式时，虽然已经请求成功但是由
             * 于{@code FailureCode}的原因无法正常返回数据。
             *
             * @param failureCode String
             */
            @Override
            public void onFailure(int failureCode) {
                if(showLoadingUI){
                    getView().hideWaitingDialog();
                }
            }

            /**
             * 请求数据失败，指在请求网络API接口请求方式时，出现无法联网、
             * 缺少权限，内存泄露等原因导致无法连接到请求数据源。
             *
             * @param errorCode String
             */
            @Override
            public void onError(int errorCode) {
                if(showLoadingUI){
                    getView().hideWaitingDialog();
                }
            }

            /**
             * 当请求数据结束时，无论请求结果是成功，失败或是抛出异常都会执行此方法给用户做处理，通常做网络
             * 请求时可以在此处隐藏“正在加载”的等待控件
             */
            @Override
            public void onComplete() {
                if(showLoadingUI){
                    getView().hideWaitingDialog();
                }
            }
        });
    }

    /**
     * 获取数据，具体由V实现
     * @deprecated 回调函数不能直接在V中去实现,避免V的臃肿,由loadModels替代
     */
    @Override
    @Deprecated
    public void getModels(IBaseCallback<List<ModelBean>> callback) {
        model.queryAllModel(callback);
    }
}
