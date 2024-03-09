package com.bank.manage.application.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bank.manage.application.R;
import com.bank.manage.application.base.BaseActivity;
import com.bank.manage.application.bean.CommonListBean;
import com.bank.manage.application.bean.FeeListBean;
import com.bank.manage.application.constant.AddressContants;
import com.bank.manage.application.event.SelectSuccessEvent;
import com.bank.manage.application.http.HttpHelper;
import com.bank.manage.application.http.JsonCallback;
import com.bank.manage.application.http.LzyResponse;
import com.bank.manage.application.ui.home.adapter.ToIssueBillsListAdapter;
import com.bank.manage.application.ui.person.activity.PutMessageActivity;
import com.bank.manage.application.utils.Preferences;
import com.bank.manage.application.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import okhttp3.Call;
import okhttp3.Response;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2021/4/1 10:35
 */
public class ZzDetailListActivity extends BaseActivity {
    @BindView(R.id.black_list_rv)
    RecyclerView blackListRv;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout refreshLayout;

    private ToIssueBillsListAdapter mBlackListAdapter;
    List<FeeListBean.RecordsBean> recordsBeans = new ArrayList<>();
    private int mType; //1转账明细  2我的卡片  3账单查询
    private int mSelect;

    public static void start(Context context, int type, int select) {
        Intent intent = new Intent(context, ZzDetailListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("type", type);
        intent.putExtra("select", select);
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
        if (mType == 1) {
            return "转账明细";
        } else if (mType == 2) {
            return "我的银行卡";
        } else {
            return "账单列表";
        }
    }

    @Override
    protected View getRightView() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mType = getIntent().getIntExtra("type", 0);
        mSelect = getIntent().getIntExtra("select", 0);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (mType == 2) {
            mBlackListAdapter = new ToIssueBillsListAdapter(R.layout.activity_my_bank_card_list_item, mType);
        } else {
            mBlackListAdapter = new ToIssueBillsListAdapter(R.layout.activity_to_issue_bills_list_item, mType);
        }
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        blackListRv.setLayoutManager(manager);
        blackListRv.setAdapter(mBlackListAdapter);
    }

    @Override
    protected void setListener() {
        mBlackListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mSelect != 0) {
                    EventBus.getDefault().post(new SelectSuccessEvent(mBlackListAdapter.getData().get(position).getBankCardNo()));
                    finish();
                }
            }
        });

        if (mType == 2) {
            mBlackListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                    ToastUtils.showShortToast("222");
                    OkGo.post(AddressContants.API_SERVER_DELETE_BANK_CARD)
                            .isMultipart(true)
                            .headers("Content-Type", "multipart/form-data; boundary=;")
                            .params("id", mBlackListAdapter.getData().get(position).getId())
                            .execute(new JsonCallback<LzyResponse<Object>>() {

                                @Override
                                public void onSuccess(LzyResponse<Object> agentBeanLzyResponse, Call call, Response response) {
                                    ToastUtils.showLongToast("删除成功");
                                    getMessageListData();
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    ToastUtils.showLongToast("删除失败");
                                }
                            });
                    return false;
                }
            });
        }

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        getMessageListData();
    }


    private void getMessageListData() {
        showLoadingDialog();
        HttpHelper.getCommonList(TAG, mType, new JsonCallback<LzyResponse<List<CommonListBean>>>() {
            @Override
            public void onSuccess(LzyResponse<List<CommonListBean>> lzyResponse, Call call, Response response) {
                dismissLoadingDialog();
                List<CommonListBean> feeListBean = lzyResponse.list;
                mBlackListAdapter.setNewData(feeListBean);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                dismissLoadingDialog();
                ToastUtils.showLongToast(e.getMessage());
            }
        });
    }
}
