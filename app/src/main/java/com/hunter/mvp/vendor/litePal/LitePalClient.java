package com.hunter.mvp.vendor.litePal;

import android.content.Context;
import com.hunter.mvp.vendor.IPlug;
import org.litepal.Operator;

public class LitePalClient implements IPlug {

    private volatile static LitePalClient singleton;

    private LitePalClient() {
    }

    public static LitePalClient getInstance() {

        if (singleton == null) {
            synchronized (LitePalClient.class) {
                if (singleton == null) {
                    singleton = new LitePalClient();
                }
            }
        }
        return singleton;
    }

    @Override
    public void plugin(Context context) {
        //数据库初始化
        Operator.initialize(context);
    }

    @Override
    public void plugout() {
    }
}