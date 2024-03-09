package com.bank.manage.application.ui.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bank.manage.application.R;
import com.bank.manage.application.base.BaseActivity;
import com.bank.manage.application.constant.AddressContants;
import com.bank.manage.application.constant.GlobalKeyContans;
import com.bank.manage.application.event.AddSuccessEvent;
import com.bank.manage.application.event.SelectSuccessEvent;
import com.bank.manage.application.http.HttpHelper;
import com.bank.manage.application.http.JsonCallback;
import com.bank.manage.application.http.LzyResponse;
import com.bank.manage.application.ui.dialog.DialogForInputAmount;
import com.bank.manage.application.ui.dialog.OnConfirmWithTagListener;
import com.bank.manage.application.ui.login.activity.LoginActivity;
import com.bank.manage.application.utils.Preferences;
import com.bank.manage.application.utils.SPUtils;
import com.bank.manage.application.utils.ToastUtils;
import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.EventBus;
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
public class ZhuanZhangActivity extends BaseActivity implements OnConfirmWithTagListener {


    @BindView(R.id.account_tv)
    TextView accountTv;
    @BindView(R.id.right_iv)
    ImageView rightIv;
    @BindView(R.id.select_rel)
    RelativeLayout selectRel;
    @BindView(R.id.sure_tv)
    TextView sureTv;
    @BindView(R.id.amount_et)
    EditText amountEt;
    @BindView(R.id.account_et)
    EditText accountEt;

    private List<String> historyTags = new LinkedList<>();
    private String mNum;
    private SPUtils spUtils;
    private String mAccount;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SelectSuccessEvent event) {
        mAccount = event.getmNum();
        accountTv.setText(mAccount);
    }

    public static void startAction(Activity context, String num) {
        Intent intent = new Intent();
        intent.setClass(context, ZhuanZhangActivity.class);
        intent.putExtra("num", num);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_zz_layout;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "账号转账";
    }

    @Override
    protected View getRightView() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mNum = getIntent().getStringExtra("num");
        spUtils = new SPUtils(GlobalKeyContans.SP_NAME);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (!mNum.equals("")) {
            accountEt.setText(mNum);
        }
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @OnClick({R.id.select_rel, R.id.sure_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_rel:
                ZzDetailListActivity.start(mActivity, 2, 111);
                break;
            case R.id.sure_tv:
                DialogForInputAmount dialogForApply = DialogForInputAmount.newInstance();
                dialogForApply.show(getSupportFragmentManager(), "input");
                break;
        }
    }

    @Override
    public void onButtonClick(View v, String tag) {
        OkGo.post(AddressContants.API_SERVER_ZHAUNZHANG)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("userId", Preferences.getUserId())
                .params("primaryBankCardNo", accountTv.getText().toString())
                .params("targetBankCardNo", accountEt.getText().toString())
                .params("transferMoney", amountEt.getText().toString())
                .params("payPassword", tag)
                .execute(new JsonCallback<LzyResponse<Object>>() {

                    @Override
                    public void onSuccess(LzyResponse<Object> agentBeanLzyResponse, Call call, Response response) {
                        ToastUtils.showLongToast("转账成功");
                        EventBus.getDefault().post(new AddSuccessEvent());
                        saveTag(accountEt.getText().toString());
                        FeeDetailActivity.startAction(mActivity);
                        finish();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.showLongToast("转账失败");
                    }
                });
    }

    private void saveTag(String text) {
        historyTags = spUtils.getStrListValue(GlobalKeyContans.SP_KEY_SEARCH);
        if (historyTags.size() == 10) {
            historyTags.remove(historyTags.size() - 1);
            if (!historyTags.contains(text)) {
                historyTags.add(0, text);
            } else {
                if (historyTags.indexOf(text) != 0) {
                    historyTags.remove(historyTags.indexOf(text));
                    historyTags.add(0, text);
                }
            }
            spUtils.putStrListValue(GlobalKeyContans.SP_KEY_SEARCH, historyTags);
            historyTags = spUtils.getStrListValue(GlobalKeyContans.SP_KEY_SEARCH);

        } else {
            if (!historyTags.contains(text)) {
                historyTags.add(0, text);
            } else {
                if (historyTags.indexOf(text) != 0) {
                    historyTags.remove(historyTags.indexOf(text));
                    historyTags.add(0, text);
                }
            }
            spUtils.putStrListValue(GlobalKeyContans.SP_KEY_SEARCH, historyTags);
            historyTags = spUtils.getStrListValue(GlobalKeyContans.SP_KEY_SEARCH);
        }
    }
}
