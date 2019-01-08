package com.hunter.mvp.base;

import android.app.Dialog;
import android.content.Context;

/**
 * @program: MVXdemo
 * @description: View的接口定义
 * @author: Tell.Tim
 * @create: 2019-01-03 14:51
 **/
public interface IBaseView {

    /**
     * 等待提示框
     * @param tip String
     * @return Dialog
     */
    Dialog showWaitingDialog(String tip);

    /**
     * 关闭正在加载view
     */
    void hideWaitingDialog();

    /**
     * 显示提示
     *
     * @param msg String
     */
    void showToast(String msg);

    /**
     * 显示请求错误提示
     */
    void showErr();

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    Context getContext();
}
