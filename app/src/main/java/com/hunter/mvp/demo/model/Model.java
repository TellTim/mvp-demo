package com.hunter.mvp.demo.model;

import android.support.annotation.NonNull;
import com.hunter.mvp.app.exception.StatusCode;
import com.hunter.mvp.base.IBaseCallback;
import com.hunter.mvp.util.AppExecutors;
import java.util.List;
import org.litepal.FluentQuery;
import org.litepal.Operator;

/**
 * @program: MVXdemo
 * @packageName: com.hunter.mvp.app.action
 * @fileName: Model
 * @author: Tell.Tim
 * @date: 2019/1/7 10:14
 * @description: MVP中的M角色, 用来访问数据库或者网络等，用线程池来执行具体的业务逻辑
 *     数据库的访问用DiskIOPool,网络的访问用NetWorkPool,回调方法中更新页面用MainThread
 */
public class Model implements IModel {

    @Override
    public void insertModel(ModelBean modelBean) {
        if (modelBean != null) {
            Runnable saveRunnable = () -> modelBean.saveOrUpdate("fieldOne = ?", modelBean.getFieldOne());
            AppExecutors.getDiskIOPool().execute(saveRunnable);
        }
    }


    public void delModel(int id) {
        Runnable deleteRunnable = () -> Operator.deleteAll(ModelBean.class, "id = ?", Integer.toString(id));
        AppExecutors.getDiskIOPool().execute(deleteRunnable);
    }


    public void queryModel(int id, @NonNull final IBaseCallback baseCallback) {

        Runnable runnable = () -> {
            ModelBean bean;
            FluentQuery clusterQuery = Operator.where("id = ?", Integer.toString(id));
            List<ModelBean> beans = clusterQuery.find(ModelBean.class);
            if (beans != null && beans.size() > 0) {
                bean = beans.get(0);
            } else {
                bean = null;
            }

            //UI线程执行更新操作
            AppExecutors.getMainThread().execute(() -> {
                if (bean != null) {
                    baseCallback.onSuccess(bean);
                } else {
                    baseCallback.onFailure(StatusCode.RESULT_EMPTY);
                }
            });
        };
        //IO线程执行IO操作
        AppExecutors.getDiskIOPool().execute(runnable);
    }


    public void updateModel(ModelBean modelBean) {
        if (modelBean != null) {
            Runnable saveRunnable = () -> modelBean.saveOrUpdate("fieldOne = ?", modelBean.getFieldOne());
            AppExecutors.getDiskIOPool().execute(saveRunnable);
        }
    }

    /**
     * 返回结合类的查找一般比较耗时，所以需要用线程池来操作，并且在回调函数中返回结果
     *
     * @param baseCallback IBaseCallback
     */
    @Override
    public void queryAllModel(@NonNull final IBaseCallback baseCallback) {

        Runnable runnable = () -> {
            final List<ModelBean> modelBeanList = Operator.findAll(ModelBean.class);
            //UI线程执行更新操作
            AppExecutors.getMainThread().execute(() -> {
                if (modelBeanList == null) {
                    baseCallback.onFailure(StatusCode.RESULT_EMPTY);
                } else {
                    baseCallback.onSuccess(modelBeanList);
                }
            });
        };
        //IO线程执行IO操作
        AppExecutors.getDiskIOPool().execute(runnable);
    }
}
