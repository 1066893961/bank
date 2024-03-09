package com.bank.manage.application.ui.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bank.manage.application.R;
import com.bank.manage.application.base.BaseActivity;
import com.bank.manage.application.bean.FeeListBean;
import com.bank.manage.application.constant.GlobalKeyContans;
import com.bank.manage.application.event.AddSuccessEvent;
import com.bank.manage.application.http.HttpHelper;
import com.bank.manage.application.http.JsonCallback;
import com.bank.manage.application.http.LzyResponse;
import com.bank.manage.application.ui.account.adapter.PingLunListAdapter;
import com.bank.manage.application.ui.home.adapter.SchoolNewsAdapter;
import com.bank.manage.application.utils.SPUtils;
import com.bank.manage.application.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2020/12/17 11:30
 */
public class FeeDetailActivity extends BaseActivity {

    @BindView(R.id.zz_tv)
    TextView zzTv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.zz_rv)
    RecyclerView zzRv;

    private SchoolNewsAdapter mSchoolNewsAdapter;

    private List<String> historyTags = new LinkedList<>();
    private SPUtils spUtils;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AddSuccessEvent event) {
        spUtils = new SPUtils(GlobalKeyContans.SP_NAME);
        historyTags = spUtils.getStrListValue(GlobalKeyContans.SP_KEY_SEARCH);
        mSchoolNewsAdapter.setNewData(historyTags);
        mSchoolNewsAdapter.notifyDataSetChanged();
        finish();
    }

    public static void startAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, FeeDetailActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_transaction_detail_info_layout;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "转账汇款";
    }

    @Override
    protected View getRightView() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        spUtils = new SPUtils(GlobalKeyContans.SP_NAME);
        historyTags = spUtils.getStrListValue(GlobalKeyContans.SP_KEY_SEARCH);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mSchoolNewsAdapter = new SchoolNewsAdapter(R.layout.person_info_list_item);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        zzRv.setLayoutManager(manager);
        zzRv.setAdapter(mSchoolNewsAdapter);

        mSchoolNewsAdapter.setNewData(historyTags);
    }

    @Override
    protected void setListener() {
        mSchoolNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZhuanZhangActivity.startAction(mActivity, mSchoolNewsAdapter.getData().get(position));
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

//        showLoadingDialog();
//        HttpHelper.getFeeList(TAG, new JsonCallback<LzyResponse<FeeListBean>>() {
//            @Override
//            public void onSuccess(LzyResponse<FeeListBean> lzyResponse, Call call, Response response) {
//
//                dismissLoadingDialog();
//            }
//
//            @Override
//            public void onError(Call call, Response response, Exception e) {
//                super.onError(call, response, e);
//                dismissLoadingDialog();
//                ToastUtils.showLongToast(e.getMessage());
//            }
//        });
    }


    @OnClick(R.id.zz_tv)
    public void onClick() {
        ZhuanZhangActivity.startAction(mActivity, "");
    }


}
