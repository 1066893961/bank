package com.bank.manage.application.ui.account.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bank.manage.application.R;
import com.bank.manage.application.base.BaseFragment;
import com.bank.manage.application.bean.FeeListBean;
import com.bank.manage.application.http.HttpHelper;
import com.bank.manage.application.http.JsonCallback;
import com.bank.manage.application.http.LzyResponse;
import com.bank.manage.application.ui.account.activity.HuiLvSearchActivity;
import com.bank.manage.application.ui.account.activity.LiCaiListActivity;
import com.bank.manage.application.ui.home.activity.ZzDetailListActivity;
import com.bank.manage.application.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description:  转账汇款
 */

public class AccountFragment extends BaseFragment {

    @BindView(R.id.kp_tv)
    TextView kpTv;
    @BindView(R.id.zd_tv)
    TextView zdTv;
    @BindView(R.id.huilv_tv)
    TextView huilvTv;
    Unbinder unbinder;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

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


    private void getMessageListData() {
        showLoadingDialog();
        HttpHelper.getFeeList(TAG, new JsonCallback<LzyResponse<FeeListBean>>() {
            @Override
            public void onSuccess(LzyResponse<FeeListBean> lzyResponse, Call call, Response response) {

                dismissLoadingDialog();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                dismissLoadingDialog();
                ToastUtils.showLongToast(e.getMessage());
            }
        });
    }


    @OnClick({R.id.kp_tv, R.id.zd_tv, R.id.huilv_tv, R.id.licai_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.kp_tv:
                ZzDetailListActivity.start(mActivity, 2, 0);
                break;
            case R.id.zd_tv:
                ZzDetailListActivity.start(mActivity, 3, 0);
                break;
            case R.id.huilv_tv:
                HuiLvSearchActivity.startAction(mActivity);
                break;
            case R.id.licai_tv:
                LiCaiListActivity.startAction(mActivity);
                break;
        }
    }
}
