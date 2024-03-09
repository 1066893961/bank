package com.bank.manage.application.ui.account.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bank.manage.application.R;
import com.bank.manage.application.utils.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Map;

/**
 * @author leo.li
 * @description:
 * @date :2019/7/25 13:59
 */
public class LiCaiListAdapter extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public LiCaiListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public LiCaiListAdapter(List data) {
        super(data);
    }

    public LiCaiListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Map<String, String> item) {
        helper.setText(R.id.account_tv, item.get("name"));
        helper.setText(R.id.content_tv, item.get("content"));

    }
}
