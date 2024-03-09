package com.bank.manage.application.ui.account.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bank.manage.application.R;
import com.bank.manage.application.base.BaseActivity;
import com.bank.manage.application.ui.account.adapter.LiCaiListAdapter;
import com.bank.manage.application.ui.dialog.OnConfirmWithTagListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2020/12/17 11:30
 */
public class LiCaiListActivity extends BaseActivity{


    @BindView(R.id.black_list_rv)
    RecyclerView blackListRv;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout refreshLayout;

    private LiCaiListAdapter mLiCaiListAdapter;
    private List<Map<String, String>> stringList = new ArrayList<>();

    public static void startAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, LiCaiListActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_list;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "理财产品列表";
    }

    @Override
    protected View getRightView() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        for (int i = 0; i < 5; i++) {
            Map<String, String> map = new HashMap<>();
            if (i == 0) {
                map.put("name", "2.92%");
                map.put("content", "闲钱理财人气之选");
            } else if (i == 1) {
                map.put("name", "3.08%");
                map.put("content", "天天可期 日日加鑫");
            } else if (i == 2) {
                map.put("name", "4.20%");
                map.put("content", "固收打底 权益增厚");
            } else if (i == 3) {
                map.put("name", "2.98%");
                map.put("content", "中银理财 乐享天天");
            } else if (i == 4) {
                map.put("name", "2.88%");
                map.put("content", "配置灵活 天天收益");
            }
            stringList.add(map);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mLiCaiListAdapter = new LiCaiListAdapter(R.layout.activity_licai_list_item);
        blackListRv.setLayoutManager(new LinearLayoutManager(mActivity));
        blackListRv.setAdapter(mLiCaiListAdapter);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mLiCaiListAdapter.setNewData(stringList);
    }

}
