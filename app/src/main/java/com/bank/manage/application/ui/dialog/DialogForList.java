package com.bank.manage.application.ui.dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.bank.manage.application.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 列表展示数据弹窗
 * @Author: leo.li
 * @CreateDate: 2019/8/27 10:56
 */
public class DialogForList extends DialogFragment {
    private static final String ARG_LIST = "param1";
    private DialogListAdapter mDialogListAdapter;
    private List<String> mVouInfosBeanList;
    private OnConfirmWithTagListener onConfirmWithTagListener;


    public static DialogForList newInstance(List<String> strings) {
       DialogForList dialogForList = new DialogForList();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LIST, (Serializable) strings);
        dialogForList.setArguments(args);
        return dialogForList;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mVouInfosBeanList = (List<String>) getArguments().getSerializable(ARG_LIST);
        }

        FragmentActivity activity = getActivity();

        if (activity != null) {
            if (activity instanceof OnConfirmWithTagListener) {
                onConfirmWithTagListener = (OnConfirmWithTagListener) activity;
            }
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View view = inflater.inflate(R.layout.dialog_list, container);
        RecyclerView dialogRv = (RecyclerView) view.findViewById(R.id.dialog_list_rv);
        TextView cancelTv = (TextView) view.findViewById(R.id.cancel_tv);


        mDialogListAdapter = new DialogListAdapter(R.layout.dialog_list_item);
        dialogRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        dialogRv.setAdapter(mDialogListAdapter);
        mDialogListAdapter.setNewData(mVouInfosBeanList);

        mDialogListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onConfirmWithTagListener.onButtonClick(view, mDialogListAdapter.getItem(position));
                dismiss();
            }
        });


        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
