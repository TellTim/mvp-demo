package com.hunter.mvp.demo.view;

import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import com.hunter.mvp.R;
import com.hunter.mvp.base.BaseActivity;
import com.hunter.mvp.base.IBaseCallback;
import com.hunter.mvp.demo.model.ModelBean;
import com.hunter.mvp.demo.presenter.Presenter;
import com.hunter.utils.StringUtils;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;
import com.lqr.recyclerview.LQRRecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: MVXdemo
 * @packageName: com.hunter.mvp.app.action
 * @fileName: DemoActivity
 * @author: Tell.Tim
 * @date: 2019/1/7 10:14
 * @description: Demo的Activity类，只负责拿到数据渲染页面
 */
public class DemoActivity extends BaseActivity<IView, Presenter> implements
        IView {
    @BindView(R.id.btnAdd)
    Button mBtnAdd;

    @BindView(R.id.rvModel)
    LQRRecyclerView mRvModel;

    private List<ModelBean> mData = new ArrayList<>();
    private LQRAdapterForRecyclerView<ModelBean> mListAdapter;

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     *
     * @return T
     */
    @Override
    protected Presenter createPresenter() {
        return new Presenter();
    }

    /**
     * 得到当前界面的布局文件id(由子类实现)
     *
     * @return int
     */
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_model;
    }

    /**
     * 是否让Toolbar有返回按钮(默认可以，一般一个应用中除了主界面，其他界面都是可以有返回按钮的)
     */
    @Override
    protected boolean isToolbarCanBack() {
        return false;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView() {
        super.initView();
        setToolbarTitle(StringUtils.getString(R.string.string_demo_title));

        //初始化适配器
        mListAdapter = new LQRAdapterForRecyclerView<ModelBean>(this, mData,
                R.layout.model_item) {
            @Override
            public void convert(LQRViewHolderForRecyclerView helper, ModelBean item, int position) {
                helper.setText(R.id.field_one, item.getFieldOne());
            }
        };
        mRvModel.setAdapter(mListAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mBtnAdd.setOnClickListener(view -> popAddModel());
    }


    @Override
    protected void onResume() {
        super.onResume();
        //调用M中的方法来显示数据
        mPresenter.loadModels(true);
    }

    public void popAddModel() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        EditText fieldEditText = new EditText(this);
        builder.setTitle(StringUtils.getString(R.string.string_add_model))
                .setMessage(R.string.string_input_field_one)
                .setView(fieldEditText);
        builder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());

        builder.setPositiveButton("确定", (dialog, which) -> {
            String text = fieldEditText.getText().toString();
            //将字段传递给P,P来完成具体的业务逻辑
            mPresenter.saveModel(text);
        });
        builder.show();
    }

    /**
     * 此处是反例，Activity中不应该直接实现M中的回调方法，应该只关心拿到数据然后再渲染即可
     * 实现V中的方法,调用P中的方法,回调方法用来更新V
     */
    @Override
    public void showAllModels() {

        showWaitingDialog("正在加载数据");

        mPresenter.getModels(new IBaseCallback<List<ModelBean>>() {
            @Override
            public void onSuccess(List<ModelBean> data) {
                hideWaitingDialog();
                resetAdapter(data);
            }

            @Override
            public void onFailure(int failureCode) {
                hideWaitingDialog();
            }

            @Override
            public void onError(int errorCode) {
                hideWaitingDialog();
            }

            @Override
            public void onComplete() {
                hideWaitingDialog();
            }
        });
    }

    /**
     * 因为P中去访问M的方法中，一般都有回调方法，为了避免在Activity中直接showAllModels，而需要去实现M中的回调方法，现只需要加载数据然后渲染页面
     *
     * @param beans List<ModelBean>
     */
    @Override
    public void showAllModels(List<ModelBean> beans) {
        resetAdapter(beans);
    }

    @Override
    public void showNoModel() {

    }

    /**
     * 重置适配器中的数据
     * @param data
     */
    private void resetAdapter(List<ModelBean> data){
        mData.clear();
        mData.addAll(data);
        mListAdapter.notifyDataSetChanged();
    }
}
