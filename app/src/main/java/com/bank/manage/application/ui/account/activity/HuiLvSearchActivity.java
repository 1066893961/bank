package com.bank.manage.application.ui.account.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bank.manage.application.R;
import com.bank.manage.application.base.BaseActivity;
import com.bank.manage.application.ui.dialog.DialogForList;
import com.bank.manage.application.ui.dialog.OnConfirmWithTagListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: 国际汇率查询
 * @Author: leo.li
 * @CreateDate: 2020/12/17 11:30
 */
public class HuiLvSearchActivity extends BaseActivity implements OnConfirmWithTagListener {


    @BindView(R.id.yuan_tv)
    TextView yuanTv;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.select_ll)
    LinearLayout selectLl;

    private List<String> stringList = new ArrayList<>();

    public static void startAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, HuiLvSearchActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_huilv;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "国际汇率查询";
    }

    @Override
    protected View getRightView() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        stringList.add("美元");
        stringList.add("欧元");
        stringList.add("英镑");
        stringList.add("港币");
        stringList.add("日元");
        stringList.add("韩币");
        stringList.add("澳门币");
        stringList.add("澳元");
        stringList.add("加币");
        stringList.add("瑞士法郎");
        stringList.add("丹麦克朗");
        stringList.add("挪威克朗");
        stringList.add("新西兰元");
        stringList.add("菲律宾币");
        stringList.add("卢布");
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


    @OnClick(R.id.select_ll)
    public void onClick() {
        DialogForList dialogForList = DialogForList.newInstance(stringList);
        dialogForList.show(getSupportFragmentManager(), "search");
    }

    @Override
    public void onButtonClick(View v, String tag) {
        text.setText(tag);
        if (tag.equals("美元")) {
            yuanTv.setText("657.34");
        } else if (tag.equals("欧元")) {
            yuanTv.setText("775.61");
        } else if (tag.equals("英镑")) {
            yuanTv.setText("911.91");
        } else if (tag.equals("港币")) {
            yuanTv.setText("84.51");
        } else if (tag.equals("日元")) {
            yuanTv.setText("5.9594");
        } else if (tag.equals("韩币")) {
            yuanTv.setText("0.584");
        } else if (tag.equals("澳门币")) {
            yuanTv.setText("82.14");
        } else if (tag.equals("澳元")) {
            yuanTv.setText("502.54");
        } else if (tag.equals("加币")) {
            yuanTv.setText("525.39");
        } else if (tag.equals("瑞士法郎")) {
            yuanTv.setText("699.06");
        } else if (tag.equals("丹麦克朗")) {
            yuanTv.setText("104.26");
        } else if (tag.equals("挪威克朗")) {
            yuanTv.setText("77.29");
        } else if (tag.equals("新西兰元")) {
            yuanTv.setText("463.24");
        } else if (tag.equals("菲律宾币")) {
            yuanTv.setText("13.6");
        } else if (tag.equals("卢布")) {
            yuanTv.setText("8.65");
        }
    }
}
