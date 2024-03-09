package com.bank.manage.application.ui.account.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bank.manage.application.R;
import com.bank.manage.application.base.BaseActivity;
import com.bank.manage.application.bean.FeeListBean;
import com.bank.manage.application.http.HttpHelper;
import com.bank.manage.application.http.JsonCallback;
import com.bank.manage.application.http.LzyResponse;
import com.bank.manage.application.ui.account.adapter.PingLunListAdapter;
import com.bank.manage.application.utils.ToastUtils;
import com.bank.manage.application.views.CustomLinearLayoutManager;
import com.bank.manage.application.views.PopupInputWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * @Description: 论坛详情
 * @Author: leo.li
 * @CreateDate: 2020/12/17 11:30
 */
public class ConsultDetailActivity extends BaseActivity {


    @BindView(R.id.top_iv)
    ImageView topIv;
    @BindView(R.id.content_tv)
    TextView contentTv;
    @BindView(R.id.shoucang_img)
    ImageView shoucangImg;
    @BindView(R.id.shoucang_ll)
    LinearLayout shoucangLl;
    @BindView(R.id.upvolate_img)
    ImageView upvolateImg;
    @BindView(R.id.upvolate_ll)
    LinearLayout upvolateLl;
    @BindView(R.id.pinglun_rv)
    RecyclerView pinglunRv;


    private PopupInputWindow mInputWindow;
    final Handler myHandler = new Handler();
    List<FeeListBean.RecordsBean> recordsBeans = new ArrayList<>();
    private FeeListBean.RecordsBean mRecordsBean;
    private PingLunListAdapter mPingLunListAdapter;

    public static void startAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, ConsultDetailActivity.class);
        context.startActivity(intent);
    }

    public static void startAction(Activity context, FeeListBean.RecordsBean recordsBean) {
        Intent intent = new Intent();
        intent.setClass(context, ConsultDetailActivity.class);
        intent.putExtra("bean", recordsBean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_consult_detail_info_layout;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "论坛详情";
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
        mPingLunListAdapter = new PingLunListAdapter(R.layout.pinglun_list_item, 1);
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(mActivity);
        linearLayoutManager.setScrollEnabled(false);
        pinglunRv.setLayoutManager(linearLayoutManager);
        pinglunRv.setAdapter(mPingLunListAdapter);
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

    @OnClick({R.id.shoucang_ll, R.id.upvolate_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shoucang_ll:
                //发表咨询内容
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mInputWindow == null) {
                            mInputWindow = new PopupInputWindow(mActivity);
                        }
                        mInputWindow.showPopup300(shoucangLl, new PopupInputWindow.OnInputListener() {
                            @Override
                            public void inputString(String comment) {
                                ToastUtils.showShortToast(comment);
                            }
                        });
                    }
                });
                break;
            case R.id.upvolate_ll:
                if (true) {
                    like();
                } else {
                    noLike();
                }
                break;
        }
    }


    /**
     * 点赞
     */
    private void like() {
        HttpHelper.like(TAG, 11, new JsonCallback() {
            @Override
            public void onSuccess(Object o, Call call, Response response) {
                ToastUtils.showShortToast("点赞成功");
                upvolateImg.setImageResource(R.mipmap.icon_dianzan_hover);
//                adviceBeanList.get(0).setLikes(1);
                dismissLoadingDialog();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                dismissLoadingDialog();
                ToastUtils.showLongToast("点赞失败");
            }
        });
    }

    /**
     * 取消点赞
     */
    private void noLike() {

        HttpHelper.noLike(TAG, 11, new JsonCallback() {
            @Override
            public void onSuccess(Object o, Call call, Response response) {
                ToastUtils.showShortToast("取消点赞");
                upvolateImg.setImageResource(R.mipmap.icon_dianzan_normal);
//                adviceBeanList.get(0).setLikes(0);
                dismissLoadingDialog();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                dismissLoadingDialog();
                ToastUtils.showLongToast("取消点赞失败");
            }
        });
    }

}
