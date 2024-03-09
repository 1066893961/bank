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
public class ConsultListAdapter extends BaseQuickAdapter<FeeListBean.RecordsBean, BaseViewHolder> {

    public ConsultListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public ConsultListAdapter(List data) {
        super(data);
    }

    public ConsultListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, FeeListBean.RecordsBean item) {
        helper.setText(R.id.title_tv, item.getTitle());
    }
}