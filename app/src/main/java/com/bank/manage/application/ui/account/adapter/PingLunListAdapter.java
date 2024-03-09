package com.bank.manage.application.ui.account.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bank.manage.application.R;
import com.bank.manage.application.bean.FeeListBean;

import java.util.List;

/**
 * @author leo.li
 * @description:
 * @date :2019/7/24 9:48
 */
public class PingLunListAdapter extends BaseQuickAdapter<FeeListBean.RecordsBean, BaseViewHolder> {

    private int mType;

    public PingLunListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public PingLunListAdapter(List data) {
        super(data);
    }

    public PingLunListAdapter(int layoutResId, int type) {
        super(layoutResId);
        this.mType = type;
    }

    @Override
    protected void convert(final BaseViewHolder helper, FeeListBean.RecordsBean item) {
        if (mType == 1) {
            helper.setVisible(R.id.header_iv, true);
            helper.setVisible(R.id.name_tv, true);
        } else {
            helper.setVisible(R.id.header_iv, false);
            helper.setVisible(R.id.name_tv, false);
        }
        helper.setText(R.id.name_tv, item.getTitle());
        helper.setText(R.id.content_tv, "内容：" + item.getContent());
    }
}