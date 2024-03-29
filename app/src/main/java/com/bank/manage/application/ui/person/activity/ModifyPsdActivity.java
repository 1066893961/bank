package com.bank.manage.application.ui.person.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bank.manage.application.R;
import com.bank.manage.application.base.BaseActivity;
import com.bank.manage.application.bean.InfoBean;
import com.bank.manage.application.http.HttpHelper;
import com.bank.manage.application.http.JsonCallback;
import com.bank.manage.application.http.LzyResponse;
import com.bank.manage.application.utils.Preferences;
import com.bank.manage.application.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2021/4/1 10:35
 */
public class ModifyPsdActivity extends BaseActivity {

    @BindView(R.id.new_psd)
    EditText newPsd;
    @BindView(R.id.old_et)
    EditText oldEt;
    @BindView(R.id.psd_et)
    EditText psdEt;
    @BindView(R.id.modify_tv)
    TextView modifyTv;

    public static void start(Context context) {
        Intent intent = new Intent(context, ModifyPsdActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_psd_info;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "修改密码";
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

    @OnClick(R.id.modify_tv)
    public void onClick() {
        showLoadingDialog();
        HttpHelper.modifyPsd(TAG, oldEt.getText().toString(), psdEt.getText().toString(), newPsd.getText().toString(),
                new JsonCallback<LzyResponse<Object>>() {
            @Override
            public void onSuccess(LzyResponse<Object> lzyResponse, Call call, Response response) {

                ToastUtils.showLongToast("修改成功");
                finish();
                dismissLoadingDialog();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                dismissLoadingDialog();
                finish();
            }
        });
    }
}
