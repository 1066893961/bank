package com.bank.manage.application.ui.home.adapter;

import com.bank.manage.application.bean.CommonListBean;
import com.bank.manage.application.utils.StringUtils;
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
public class ToIssueBillsListAdapter extends BaseQuickAdapter<CommonListBean, BaseViewHolder> {
    private int mType;

    public ToIssueBillsListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public ToIssueBillsListAdapter(List data) {
        super(data);
    }

    public ToIssueBillsListAdapter(int layoutResId, int type) {
        super(layoutResId);
        this.mType = type;
    }

    @Override
    protected void convert(final BaseViewHolder helper, CommonListBean item) {
        if (mType == 2) {
            helper.setText(R.id.account_tv, item.getBankCardNo());
            helper.setText(R.id.name_tv, item.getBankName());
            helper.setText(R.id.amount_tv, "人民币元：" + item.getMoney());

        } else {
            helper.setText(R.id.title_tv, "支付账号："+item.getPrimaryBankCardNo());
            helper.setText(R.id.content_tv,  "收款账号："+item.getTargetBankCardNo());
            helper.setText(R.id.amount_tv,  "转账金额："+item.getTransferMoney());
            helper.setText(R.id.time_tv, StringUtils.timeFormat(item.getCreateAt()));
        }
    }
}