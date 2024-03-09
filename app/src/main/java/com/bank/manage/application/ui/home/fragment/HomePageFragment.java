package com.bank.manage.application.ui.home.fragment;

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
import com.bank.manage.application.ui.home.activity.FeeDetailActivity;
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
 * Description: 服务查询
 */

public class HomePageFragment extends BaseFragment {


    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.zz_tv)
    TextView zzTv;
    @BindView(R.id.mx_tv)
    TextView mxTv;

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_list;
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


    @OnClick({R.id.zz_tv, R.id.mx_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zz_tv:
                FeeDetailActivity.startAction(mActivity);
               break;
            case R.id.mx_tv:
                ZzDetailListActivity.start(mActivity, 1, 0);
                break;
        }
    }
}
