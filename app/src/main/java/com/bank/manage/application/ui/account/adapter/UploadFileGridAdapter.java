package com.bank.manage.application.ui.account.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bank.manage.application.R;
import com.bank.manage.application.utils.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author leo.li
 * @description: 上传文件
 * @date :2019/7/25 13:59
 */
public class UploadFileGridAdapter extends BaseQuickAdapter<Map<String, Object>, BaseViewHolder> {
    private OnClearClickListener mListener;

    public UploadFileGridAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public UploadFileGridAdapter(List data) {
        super(data);
    }

    public UploadFileGridAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Map<String, Object> item) {
        if (!StringUtils.isEmpty(item.get("pkgFileUrl").toString())) {
            helper.setVisible(R.id.clear_iv, true);
            if (item.get("pkgFileUrl").toString().contains(".pdf")) {
                ((ImageView) helper.getView(R.id.file_iv)).setImageDrawable(mContext.getResources().getDrawable(R.mipmap.icon_pdf));
            } else if (item.get("pkgFileUrl").toString().contains(".xlsx")) {
                ((ImageView) helper.getView(R.id.file_iv)).setImageDrawable(mContext.getResources().getDrawable(R.mipmap.icon_word));
            }

        } else {
            helper.setVisible(R.id.clear_iv, false);
            ((ImageView) helper.getView(R.id.file_iv)).setImageDrawable(mContext.getResources().getDrawable(R.mipmap.icon_class_upload));
        }

        helper.setOnClickListener(R.id.clear_iv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClearListener(helper.getLayoutPosition());
            }
        });
    }


    public void setOnClearListener(OnClearClickListener mListener) {
        this.mListener = mListener;
    }

    public interface OnClearClickListener {
        void onClearListener(int position);
    }

}
