package com.hunter.mvp.app;

import com.hunter.mvp.base.BaseApp;
import com.hunter.mvp.vendor.litePal.LitePalClient;
import com.hunter.utils.ProcessUtils;

/**
 * @program: MVXdemo
 * @description: 主Application
 * @author: Tell.Tim
 * @create: 2019-01-02 18:53
 **/
public class MyApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        //防止内存泄漏此处用来初始化第三方的SDK
        if (getApplicationInfo().packageName.equals(ProcessUtils.getCurrentProcessName())) {
            //数据库初始化
            LitePalClient.getInstance().plugin(this);
        }
    }
}
