package com.bank.manage.application.ui.person.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bank.manage.application.R;
import com.bank.manage.application.base.BaseFragment;
import com.bank.manage.application.event.ModifySuccessEvent;
import com.bank.manage.application.ui.login.activity.LoginActivity;
import com.bank.manage.application.ui.person.activity.ModifyPsdActivity;
import com.bank.manage.application.ui.person.activity.MyCollectionActivity;
import com.bank.manage.application.ui.person.activity.MyMessageActivity;
import com.bank.manage.application.ui.person.activity.PersonInfoActivity;
import com.bank.manage.application.ui.person.activity.PutMessageActivity;
import com.bank.manage.application.utils.Preferences;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description:
 */

public class PersonInfoFragment extends BaseFragment {


    @BindView(R.id.person_header_ll)
    LinearLayout personHeaderLl;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.num_tv)
    TextView numTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.class_tv)
    TextView classTv;
    @BindView(R.id.info_rel)
    RelativeLayout infoRel;
    @BindView(R.id.modify_psd_rel)
    RelativeLayout modifyPsdRel;
    @BindView(R.id.version_update_rel)
    RelativeLayout versionUpdateRel;
    @BindView(R.id.logout_rel)
    RelativeLayout logoutRel;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ModifySuccessEvent event) {
        //刷新数据
    }

    public static PersonInfoFragment newInstance() {
        PersonInfoFragment fragment = new PersonInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person_info;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        nameTv.setText("姓名：" + Preferences.getUsername());
        classTv.setText("年龄：" + Preferences.getKeyUserClassName());
        numTv.setText("学号：" + Preferences.getKeyUserNo());
        phoneTv.setText("电话：" + Preferences.getKeyUserMobile());
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
    }


    @OnClick({R.id.person_info_rel, R.id.info_rel, R.id.modify_psd_rel, R.id.version_update_rel, R.id.my_tiezi_rel, R.id.logout_rel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.person_info_rel:
                //个人资料
                PersonInfoActivity.start(mActivity);
                break;
            case R.id.info_rel:
                //我的收藏
//                MyCollectionActivity.start(mActivity);
                break;
            case R.id.modify_psd_rel:
                //修改密码
                ModifyPsdActivity.start(mActivity);
                break;
            case R.id.version_update_rel:
                //发布帖子
                PutMessageActivity.start(mActivity, 1);
                break;
            case R.id.my_tiezi_rel:
                //我的帖子
                MyMessageActivity.start(mActivity);
                break;
            case R.id.logout_rel:
                Preferences.cleanUserInfo();
                LoginActivity.start(mActivity);
                break;
        }
    }
}
