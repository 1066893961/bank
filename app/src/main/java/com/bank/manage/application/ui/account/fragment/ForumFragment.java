package com.bank.manage.application.ui.account.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bank.manage.application.R;
import com.bank.manage.application.base.BaseFragment;
import com.bank.manage.application.bean.FeeListBean;
import com.bank.manage.application.http.HttpHelper;
import com.bank.manage.application.http.JsonCallback;
import com.bank.manage.application.http.LzyResponse;
import com.bank.manage.application.ui.account.adapter.PingLunListAdapter;
import com.bank.manage.application.utils.ToastUtils;
import com.bank.manage.application.views.PopupInputWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description:  心理咨询
 */

public class ForumFragment extends BaseFragment {


    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.top_iv)
    ImageView topIv;
    @BindView(R.id.input_content_et)
    EditText inputContentEt;
    @BindView(R.id.zixun_tv)
    TextView zixunTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.comment_rv)
    RecyclerView commentRv;
    Unbinder unbinder;

    private PopupInputWindow mInputWindow;
    final Handler myHandler = new Handler();

    List<FeeListBean.RecordsBean> recordsBeans = new ArrayList<>();
    private PingLunListAdapter mPingLunListAdapter;

    public static ForumFragment newInstance() {
        ForumFragment fragment = new ForumFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xinli_zixun;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPingLunListAdapter = new PingLunListAdapter(R.layout.pinglun_list_item, 2);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        commentRv.setLayoutManager(manager);
        commentRv.setAdapter(mPingLunListAdapter);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        getMessageListData();
    }


    private void getMessageListData() {
        showLoadingDialog();
        HttpHelper.getFeeList(TAG, new JsonCallback<LzyResponse<FeeListBean>>() {
            @Override
            public void onSuccess(LzyResponse<FeeListBean> lzyResponse, Call call, Response response) {

                FeeListBean feeListBean = lzyResponse.entity;
                if (feeListBean == null) {
                    return;
                }
                recordsBeans = feeListBean.getRecords();
                mPingLunListAdapter.setNewData(recordsBeans);
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

    @OnClick(R.id.zixun_tv)
    public void onClick() {
        //发表咨询内容
        myHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mInputWindow == null) {
                    mInputWindow = new PopupInputWindow(mActivity);
                }
                mInputWindow.showPopup300(zixunTv, new PopupInputWindow.OnInputListener() {
                    @Override
                    public void inputString(String comment) {
                        ToastUtils.showShortToast(comment);
                    }
                });
            }
        });
    }
}
