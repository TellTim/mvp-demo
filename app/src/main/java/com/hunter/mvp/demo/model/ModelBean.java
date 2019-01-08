package com.hunter.mvp.demo.model;

import android.support.annotation.NonNull;
import org.litepal.crud.LitePalSupport;

/**
 * @program: MVXdemo
 * @packageName: com.hunter.mvp.app.action
 * @fileName: ModelBean
 * @author: Tell.Tim
 * @date: 2019/1/7 10:14
 * @description: Model的POJO简单实例
 */
public class ModelBean extends LitePalSupport implements Comparable<ModelBean> {

    private String fieldOne;

    public ModelBean(String fieldOne) {
        this.fieldOne = fieldOne;
    }

    public String getFieldOne() {
        return fieldOne;
    }

    public void setFieldOne(String fieldOne) {
        this.fieldOne = fieldOne;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            ModelBean modelBean = (ModelBean) o;
            return (this.getFieldOne().equals(modelBean.getFieldOne()));
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(@NonNull ModelBean modelBean) {
        return this.getFieldOne().compareTo(modelBean.getFieldOne());
    }
}
