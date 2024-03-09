package com.bank.manage.application.ui.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bank.manage.application.R;
import com.bank.manage.application.base.BaseActivity;
import com.bank.manage.application.bean.FeeListBean;

import butterknife.BindView;

/**
 * @Description: 校园资讯详情
 * @Author: leo.li
 * @CreateDate: 2020/12/17 11:30
 */
public class SchoolNewsDetailActivity extends BaseActivity {


    @BindView(R.id.top_iv)
    ImageView topIv;
    @BindView(R.id.content_tv)
    TextView contentTv;

    private FeeListBean.RecordsBean mRecordsBean;

    public static void startAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, SchoolNewsDetailActivity.class);
        context.startActivity(intent);
    }

    public static void startAction(Activity context, FeeListBean.RecordsBean recordsBean) {
        Intent intent = new Intent();
        intent.setClass(context, SchoolNewsDetailActivity.class);
        intent.putExtra("bean", recordsBean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_schoole_news_detail_info_layout;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "校园资讯详情";
    }

    @Override
    protected View getRightView() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mRecordsBean = (FeeListBean.RecordsBean) getIntent().getSerializableExtra("bean");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {


    }
}
