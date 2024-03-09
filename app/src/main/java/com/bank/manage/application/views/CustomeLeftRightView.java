package com.bank.manage.application.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bank.manage.application.R;

/**
 * @Description: 左边标题 右边内容
 * @Author: leo.li
 * @CreateDate: 2020/01/14 16:22
 */
public class CustomeLeftRightView extends RelativeLayout {

    private TextView mContent;     //内容textview
    private TextView mTitle;      //标签Textview


    public CustomeLeftRightView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CustomeLeftRightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CustomeLeftRightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View.inflate(context, R.layout.view_left_right_layout, this);

        final TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LeftRightView, defStyleAttr, 0);
        //标签文字
        String mTitleStr = typedArray.getString(R.styleable.LeftRightView_labelString);
        typedArray.recycle();

        mTitle = (TextView) findViewById(R.id.title_tv);
        mTitle.setText(mTitleStr);
        mContent = (TextView) findViewById(R.id.content_tv);
    }

    /**
     * 设置左边标题
     *
     * @param str 内容文字
     */
    public void setTitle(String str) {
        mTitle.setText(str);
    }

    /**
     * 设置右边内容
     *
     * @param str 内容文字
     */
    public void setContent(String str) {
        mContent.setText(str);
    }

    /**
     * 设置右边文字颜色
     *
     */
    public void setContentColor(int color) {
        mContent.setTextColor(color);
    }


}
