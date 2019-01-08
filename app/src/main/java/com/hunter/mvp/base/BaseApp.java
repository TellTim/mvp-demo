package com.hunter.mvp.base;

import android.support.multidex.MultiDexApplication;
import com.hunter.utils.AppUtils;
import com.hunter.utils.ProcessUtils;
import com.hunter.utils.Utils;

/**
 * @program: MVXdemo
 * @description: Application的基类, 管理app的生命周期一起全局参数
 * @author: Tell.Tim
 * @create: 2019-01-02 17:40
 **/
public class BaseApp extends MultiDexApplication {

    /**
     * 完全退出
     * 一般用于“退出程序”功能
     */
    public static void exit() {
        AppUtils.exitApp();
    }

    /**
     * 重启当前应用
     */
    public static void restart() {
        AppUtils.relaunchApp();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //防止内存泄漏
        if (getApplicationInfo().packageName.equals(
                ProcessUtils.getCurrentProcessName(getApplicationContext()))) {
            Utils.init(this);
        }
    }
}
