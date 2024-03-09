package com.bank.manage.application.ui.person.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bank.manage.application.R;
import com.bank.manage.application.base.BaseActivity;
import com.bank.manage.application.bean.FeeListBean;
import com.bank.manage.application.constant.AddressContants;
import com.bank.manage.application.http.HttpHelper;
import com.bank.manage.application.http.JsonCallback;
import com.bank.manage.application.http.LzyResponse;
import com.bank.manage.application.utils.ParamsUtils;
import com.bank.manage.application.utils.Preferences;
import com.bank.manage.application.utils.ToastUtils;
import com.lzy.okgo.OkGo;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2021/4/1 10:35
 */
public class MyMessageActivity extends BaseActivity {


    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.card_num_et)
    EditText cardNumEt;
    @BindView(R.id.bank_name_et)
    EditText bankNameEt;
    @BindView(R.id.add_tv)
    TextView addTv;

    public static void start(Context context) {
        Intent intent = new Intent(context, MyMessageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_bank;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "添加银行卡";
    }

    @Override
    protected View getRightView() {
        return null;
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

    @OnClick(R.id.add_tv)
    public void onClick() {
        OkGo.post(AddressContants.API_SERVER_ADD_BANK_CARD)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("userId", Preferences.getUserId())
                .params("bankName", bankNameEt.getText().toString())
                .params("bankCardNo", cardNumEt.getText().toString())
                .execute(new JsonCallback<LzyResponse<Object>>() {

                    @Override
                    public void onSuccess(LzyResponse<Object> agentBeanLzyResponse, Call call, Response response) {
                        ToastUtils.showLongToast("添加成功，请到我的卡片列表查看");
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.showLongToast("添加失败");
                    }
                });
    }
}
