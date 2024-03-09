package com.bank.manage.application.ui.account.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.bank.manage.application.R;
import com.bank.manage.application.base.BaseFragment;
import com.bank.manage.application.bean.FeeListBean;
import com.bank.manage.application.event.AddSuccessEvent;
import com.bank.manage.application.http.HttpHelper;
import com.bank.manage.application.http.JsonCallback;
import com.bank.manage.application.http.LzyResponse;
import com.bank.manage.application.ui.account.activity.ConsultDetailActivity;
import com.bank.manage.application.ui.account.adapter.ConsultListAdapter;
import com.bank.manage.application.utils.ToastUtils;
import com.bank.manage.application.views.EmptyView;
import com.bank.manage.application.views.ViewUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description: 心灵论坛
 */

public class ConsultFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.black_list_rv)
    RecyclerView blackListRv;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout refreshLayout;

    private ConsultListAdapter mBlackListAdapter;
    private EmptyView mEmptyView;
    List<FeeListBean.RecordsBean> recordsBeans = new ArrayList<>();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AddSuccessEvent event) {
        getMessageListData();
    }

    public static ConsultFragment newInstance() {
        ConsultFragment fragment = new ConsultFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xinling_luntan;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        refreshLayout.setDelegate(this);
        refreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getContext(), true));
        refreshLayout.setPullDownRefreshEnable(true);

        mBlackListAdapter = new ConsultListAdapter(R.layout.activity_consult_list_item);
        mEmptyView = ViewUtils.getEmptyView(getContext());
        mBlackListAdapter.setEmptyView(mEmptyView);
        mEmptyView.setViewState(EmptyView.ViewState.GONE);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        blackListRv.setLayoutManager(manager);
        mBlackListAdapter.setLoadMoreView(ViewUtils.getLoadMoreView());
//        mBlackListAdapter.setOnLoadMoreListener(this);
        blackListRv.setAdapter(mBlackListAdapter);

        mEmptyView.setOnErrorClickListener(new EmptyView.OnErrorClickListener() {
            @Override
            public void onClick() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getMessageListData();//空页面加载
                    }
                }, 100);

            }
        });
    }

    @Override
    protected void setListener() {
        mBlackListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter dapter, View view, int position) {
                ConsultDetailActivity.startAction(mActivity);
            }
        });
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
                mBlackListAdapter.setNewData(recordsBeans);
                refreshLayout.endRefreshing();
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


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getMessageListData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
