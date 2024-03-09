package com.bank.manage.application.ui.home.adapter;

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
public class SchoolNewsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SchoolNewsAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public SchoolNewsAdapter(List data) {
        super(data);
    }

    public SchoolNewsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {
        helper.setText(R.id.num_tv, item);
    }
}