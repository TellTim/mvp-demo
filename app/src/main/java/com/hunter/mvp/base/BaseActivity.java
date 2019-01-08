package com.hunter.mvp.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.hunter.mvp.R;
import com.hunter.mvp.widget.CustomDialog;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * @program: MVXdemo
 * @packageName: com.hunter.mvp.app.action
 * @fileName: Test
 * @author: Tell.Tim
 * @date: 2019/1/7 10:14
 * @description:  自定义Activity的基类, 通过泛型定义将Activity，V以及P组织在一起
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements IBaseView{

    /**
     * 自定义toolbar栏
     */
    @BindView(R.id.afl_toolbar)
    public FrameLayout mToolbar;

    ///以下是所有Activity中可能会出现的控件
    /**
     * Activity中toobar的返回的ImageView
     */
    @BindView(R.id.iv_back_arrow)
    public ImageView mImgViewBack;
    /**
     * toolbar的返回的title bar
     */
    @BindView(R.id.all_toolbar_title)
    public AutoLinearLayout mLlToolbarTitle;
    /**
     * title bar中的title
     */
    @BindView(R.id.tvToolbarTitle)
    public TextView mToolbarTitle;
    /**
     * title bar中的sub title
     */
    @BindView(R.id.tvToolbarSubTitle)
    public TextView mToolbarSubTitle;
    /**
     * title bar中的左边的取消文本
     */
    @BindView(R.id.tv_left)
    public TextView mLeft;
    /**
     * title bar中的右边的发送文本
     */
    @BindView(R.id.tv_right)
    public TextView mRight;
    /**
     * activity持有的Presenter对象
     */
    protected T mPresenter;
    /**
     * Activity的appBar布局
     */
    @BindView(R.id.appBar)
    protected AppBarLayout mAppBar;

    private CustomDialog mDialogWaiting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            //将V绑定到P中去
            mPresenter.attachView((V) this);
        }

        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        ButterKnife.bind(this);

        setupAppBarAndToolbar();

        initView();
        initData();
        initListener();
    }

    /**
     * 设置AppBar和Toolbar
     */
    private void setupAppBarAndToolbar() {
        //如果该应用运行在android 5.0以上设备，设置标题栏的z轴高度
        if (mAppBar != null && Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            mAppBar.setElevation(0.0f);
        }

        if (mImgViewBack != null) {
            mImgViewBack.setVisibility(isToolbarCanBack() ? View.VISIBLE : View.GONE);
            mImgViewBack.setOnClickListener(v -> onBackPressed());
        }

        if (mLeft != null) {
            mLeft.setOnClickListener(v -> onBackPressed());
        }

        if (mLlToolbarTitle != null) {
            mLlToolbarTitle.setPadding(isToolbarCanBack() ? 0 : 40, 0, 0, 0);
        }
    }

    /**
     * 设置toolbar的标题
     */
    public void setToolbarTitle(String title) {
        mToolbarTitle.setText(title);
    }

    /**
     * 设置toolbar的子标题
     */
    public void setToolbarSubTitle(String subTitle) {
        mToolbarSubTitle.setText(subTitle);
        mToolbarSubTitle.setVisibility(subTitle.length() > 0 ? View.VISIBLE : View.GONE);
    }

    /**
     * 在setContentView()调用之前调用，可以设置WindowFeature
     * (如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
     */
    protected void init() {
        registerBroadcast();
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }

    protected void registerBroadcast() {
    }

    protected void unRegisterBroadcast() {
    }

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     *
     * @return T
     */
    protected abstract T createPresenter();

    /**
     * 得到当前界面的布局文件id(由子类实现)
     *
     * @return int
     */
    protected abstract int provideContentViewId();

    /**
     * 是否让Toolbar有返回按钮(默认可以，一般一个应用中除了主界面，其他界面都是可以有返回按钮的)
     */
    protected boolean isToolbarCanBack() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterBroadcast();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 显示等待提示框
     */
    @Override
    public Dialog showWaitingDialog(String tip) {
        hideWaitingDialog();
        View view = View.inflate(this, R.layout.layout_dialog_waiting, null);
        if (!TextUtils.isEmpty(tip))
            ((TextView) view.findViewById(R.id.tvTip)).setText(tip);
        mDialogWaiting = new CustomDialog(this, view, R.style.CustomDialog);
        mDialogWaiting.show();
        mDialogWaiting.setCancelable(false);
        return mDialogWaiting;
    }

    /**
     * 关闭正在加载view
     */
    @Override
    public void hideWaitingDialog() {
        if (mDialogWaiting != null) {
            mDialogWaiting.dismiss();
            mDialogWaiting = null;
        }
    }


    /**
     * 显示提示
     *
     * @param msg String
     */
    @Override
    public void showToast(String msg) {

    }

    /**
     * 显示请求错误提示
     */
    @Override
    public void showErr() {

    }

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    @Override
    public Context getContext() {
        return this;
    }
}
