package com.bank.manage.application.ui.dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.bank.manage.application.R;
import com.bank.manage.application.utils.StringUtils;
import com.bank.manage.application.utils.ToastUtils;


/**
 * @Description: 专款拨入  拨入金额
 * @Author: leo.li
 * @CreateDate: 2019/8/27 10:56
 */
public class DialogForInputAmount extends DialogFragment {
    private int widthPadding;
    private OnConfirmWithTagListener onConfirmWithTagListener;

    public static DialogForInputAmount newInstance() {
        DialogForInputAmount fragment = new DialogForInputAmount();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();

        if (activity != null) {
            if (activity instanceof OnConfirmWithTagListener) {
                onConfirmWithTagListener = (OnConfirmWithTagListener) activity;
            }
        }
        widthPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 53, getResources().getDisplayMetrics());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View view = inflater.inflate(R.layout.dialog_for_input_amount, container);

        TextView sure_btn = (TextView) view.findViewById(R.id.sure_tv);
        TextView btn_cancel = (TextView) view.findViewById(R.id.btn_cancel);
        final EditText inputEt = (EditText) view.findViewById(R.id.input_et);


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(inputEt.getText().toString())){
                    ToastUtils.showShortToast("请输入打款金额");
                    return;
                }
                onConfirmWithTagListener.onButtonClick(v, inputEt.getText().toString());
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(getResources().getDisplayMetrics().widthPixels - 2 * widthPadding, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
