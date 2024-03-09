package com.bank.manage.application.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.bank.manage.application.R;
import com.bank.manage.application.utils.DipUtils;


/**
 * Created by admin on 17/3/13.
 */

public class ViewUtils {

    public static TextView getRightTextView(Context context, String text) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, DipUtils.dip2px(context, 15), 0);
        TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setText(text);
        textView.setTextColor(context.getResources().getColor(R.color.color_0076ff));
        textView.setTextSize(16);
        return textView;
    }

    /**
     * 上图  下文字
     *
     * @param context
     * @param text
     * @return
     */
    public static TextView getRightTextViewWithImg(Context context, String text) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DipUtils.dp2px(context, 30), RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, DipUtils.dip2px(context, 15), 0);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setLayoutParams(layoutParams);

        textView.setTextColor(context.getResources().getColor(R.color.color_979db4));
        textView.setTextSize(10);

        Drawable drawable = context.getResources().getDrawable(R.mipmap.icon_no_collect);
        // 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
        textView.setText(text);
        return textView;
    }

    public static ImageView getRightImageView(Context context, int resouceId) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, DipUtils.dip2px(context, 10), 0);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(resouceId);
        return imageView;
    }

    public static LoadMoreView getLoadMoreView() {
        LoadMoreView loadMoreView = new LoadMoreView() {
            @Override
            public int getLayoutId() {
                return R.layout.load_more_footer;
            }

            @Override
            protected int getLoadingViewId() {
                return R.id.load_more_loading_view;
            }

            @Override
            protected int getLoadFailViewId() {
                return R.id.load_more_load_fail_view;
            }

            @Override
            protected int getLoadEndViewId() {
                return R.id.load_more_load_end_view;
            }
        };
        return loadMoreView;
    }

//	public static LoadMoreView getHorizontalLoadMoreView(){
//		LoadMoreView loadMoreView = new LoadMoreView() {
//			@Override
//			public int getLayoutId() {
//				return R.layout.load_more_horizon_footer;
//			}
//
//			@Override
//			protected int getLoadingViewId() {
//				return R.id.load_more_loading_view;
//			}
//
//			@Override
//			protected int getLoadFailViewId() {
//				return R.id.load_more_load_fail_view;
//			}
//
//			@Override
//			protected int getLoadEndViewId() {
//				return R.id.load_more_load_end_view;
//			}
//		};
//		return loadMoreView;
//	}

//	public static MoneyView getMoneyView(Context context){
//		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
//				RelativeLayout.LayoutParams.MATCH_PARENT);
//		MoneyView moneyView = new MoneyView(context);
//		moneyView.setLayoutParams(layoutParams);
//		return moneyView;
//	}

    public static EmptyView getEmptyView(Context context) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        EmptyView emptyView = new EmptyView(context);
        layoutParams.setMargins(0, DipUtils.dp2px(context, 100), 0, 0);
        emptyView.setGravity(Gravity.CENTER_HORIZONTAL);
        emptyView.setLayoutParams(layoutParams);
        return emptyView;
    }

    public static EmptyView getEmptyView2(Context context) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        EmptyView emptyView = new EmptyView(context);
        layoutParams.setMargins(0, 0, 0, 0);
        emptyView.setGravity(Gravity.CENTER_HORIZONTAL);
        emptyView.setLayoutParams(layoutParams);
        return emptyView;
    }

}
