package com.bank.manage.application.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bank.manage.application.R;
import com.bank.manage.application.utils.DipUtils;
import com.bank.manage.application.utils.ScreenUtils;

/**
 * Created by fengzheng on 2017/6/8.
 */

public class EmptyView extends RelativeLayout {

    public enum ViewState {
        DISMISS,
        ERROR,
        LODDING,
        EMPTY,
        NEW_EMPTY,
        MESSAGE_EMPTY,//消息通知无数据页面
        SEARCH_EMPTY,//搜索无数据页面
        GONE,
        NO_ORDER_RECORD,//无订单记录
        NO_SYSTEM_MESSAGE,//无系统消息
        NO_TASK,//无代办事项
        NO_ADDRESS,//无地址
        NO_VOUCHER_LIST,//无凭证列表
        NO_RONGZI,//无融资申请记录
        NO_WITNESS,//无见证签署
        NO_GRAB_SINGLE,//无融资申请
        NO_CUSTOMER_LIST,       //无客户列表
    }

    public interface OnErrorClickListener {
        void onClick();
    }


    private int mFlag;
    private Context mContext;
    //    private FlipImageView flidImageIv;
    private ImageView statusImageView;
    private TextView other_tip_tv;
    private TextView final_tip_tv;
    private TextView tipTv;
    private LinearLayout loadViewLl;
    //    private TextView tv_refreshing;
    // AnimationDrawable animationDrawable;
    private OnErrorClickListener onErrorClickListener;

    public EmptyView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.empty_layout, null);
//        flidImageIv = (FlipImageView) view.findViewById(R.id.load_image_iv);
        loadViewLl = (LinearLayout) view.findViewById(R.id.load_view_ll);
//        tv_refreshing = (TextView) view.findViewById(R.id.tv_refreshing);
        statusImageView = (ImageView) view.findViewById(R.id.status_iv);
        tipTv = (TextView) view.findViewById(R.id.bottom_tip_tv);
        other_tip_tv = (TextView) view.findViewById(R.id.other_tip_tv);
        final_tip_tv = (TextView) view.findViewById(R.id.final_tip_tv);
        statusImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onErrorClickListener != null) {
                    onErrorClickListener.onClick();
                }
            }
        });
        /**  默认加载状态*/
        statusImageView.setVisibility(GONE);
        tipTv.setVisibility(GONE);
        other_tip_tv.setVisibility(GONE);
        final_tip_tv.setVisibility(GONE);
        loadViewLl.setVisibility(VISIBLE);
//        flidImageIv.startFlid();
//        imageIv.setImageResource(R.drawable.loading_anim);
//        animationDrawable = (AnimationDrawable) imageIv.getDrawable();
//        if (animationDrawable != null)
//            animationDrawable.start();

        addView(view);
    }

    /**
     * 区分业务管理各个模块的pos
     *
     * @param pos
     */
    public void setBusFlag(int pos) {
        this.mFlag = pos;
    }

    public void setViewState(ViewState state) {
        switch (state) {
            case DISMISS: {
                statusImageView.setVisibility(GONE);
                tipTv.setVisibility(GONE);
                loadViewLl.setVisibility(GONE);
                break;
            }
            case ERROR: {
                statusImageView.setVisibility(VISIBLE);
                tipTv.setVisibility(VISIBLE);
                statusImageView.setImageResource(R.mipmap.no_net_work);
                tipTv.setText("网络连接失败");
                loadViewLl.setVisibility(GONE);
                break;
            }
            case LODDING: {
                loadViewLl.setVisibility(VISIBLE);
                statusImageView.setVisibility(INVISIBLE);
                break;
            }
            case EMPTY: {
                statusImageView.setVisibility(VISIBLE);
                loadViewLl.setVisibility(GONE);
                statusImageView.setImageResource(R.mipmap.no_system_message);
                break;
            }
            case NEW_EMPTY: {
                statusImageView.setVisibility(VISIBLE);
                loadViewLl.setVisibility(GONE);
                tipTv.setVisibility(VISIBLE);
                tipTv.setText("暂无数据");
                statusImageView.setImageResource(R.mipmap.no_voucher_list);
                break;
            }
            case MESSAGE_EMPTY: {
                statusImageView.setVisibility(VISIBLE);
                loadViewLl.setVisibility(GONE);
                break;
            }
            case SEARCH_EMPTY: {
                statusImageView.setVisibility(VISIBLE);
                loadViewLl.setVisibility(GONE);
                break;
            }
            case GONE: {
                statusImageView.setVisibility(GONE);
                loadViewLl.setVisibility(GONE);
                break;
            }
            case NO_ORDER_RECORD: {
                setImageMarginTop();
                statusImageView.setVisibility(VISIBLE);
                loadViewLl.setVisibility(GONE);
                tipTv.setVisibility(VISIBLE);
                final_tip_tv.setVisibility(VISIBLE);
                if (mFlag == 1 || mFlag == 2) {
                    final_tip_tv.setText("您可以在线管理企业融资流程，或直接要求企业提交补充资料。");
                } else if (mFlag == 3) {
                    final_tip_tv.setText("您可以在线管理企业融资流程，或直接跳过该步骤。");
                } else if (mFlag == 4) {
                    final_tip_tv.setText("您可以在线管理企业融资流程。");
                } else if (mFlag == 5) {
                    final_tip_tv.setText("您可以在线开展贷后管理操作，或直接提示融资企业上传还款明细。");
                } else if (mFlag == 6) {
                    final_tip_tv.setText("您可以在线管理所有已到期的历史融资业务。");
                }
                tipTv.setText("您尚无融资业务待办事宜，可以去“融资申请”中看看。");
                tipTv.setTextSize(18);
                tipTv.setTextColor(getResources().getColor(R.color.color_030303));
                statusImageView.setImageResource(R.mipmap.no_order_record);
                break;
            }
            case NO_WITNESS: {
                statusImageView.setVisibility(VISIBLE);
                loadViewLl.setVisibility(GONE);
                tipTv.setVisibility(VISIBLE);
                final_tip_tv.setVisibility(VISIBLE);
                final_tip_tv.setText("见证签署——是支持多方共同参合约服务的在线签署，同时引入权威见证机构以具备完全法律效力的专业合约签章平台。");
                tipTv.setText("您尚无相关见证签署事宜");
                tipTv.setTextSize(18);
                tipTv.setTextColor(getResources().getColor(R.color.color_030303));
                statusImageView.setImageResource(R.mipmap.no_order_record);
                break;
            }
            case NO_SYSTEM_MESSAGE: {
                statusImageView.setVisibility(VISIBLE);
                loadViewLl.setVisibility(GONE);
                tipTv.setVisibility(VISIBLE);
                tipTv.setText("无系统消息");
                statusImageView.setImageResource(R.mipmap.no_system_message);
                break;
            }
            case NO_TASK: {
                statusImageView.setVisibility(VISIBLE);
                loadViewLl.setVisibility(GONE);
                tipTv.setVisibility(VISIBLE);
                tipTv.setText("无代办事项");
                statusImageView.setImageResource(R.mipmap.no_task);
                break;
            }
            case NO_ADDRESS: {
                statusImageView.setVisibility(VISIBLE);
                loadViewLl.setVisibility(GONE);
                tipTv.setVisibility(VISIBLE);
                tipTv.setText("暂无地址");
                statusImageView.setImageResource(R.mipmap.no_order_record);
                break;
            }
            case NO_VOUCHER_LIST: {
                statusImageView.setVisibility(VISIBLE);
                loadViewLl.setVisibility(GONE);
                tipTv.setVisibility(VISIBLE);
                tipTv.setText("无凭证列表");
                    statusImageView.setImageResource(R.mipmap.no_voucher_list);
                break;
            }
            case NO_RONGZI: {
                statusImageView.setVisibility(VISIBLE);
                loadViewLl.setVisibility(GONE);
                tipTv.setVisibility(VISIBLE);
                tipTv.setText("无融资申请记录");
                statusImageView.setImageResource(R.mipmap.no_rongzi);
                break;
            }
            case NO_GRAB_SINGLE: {
                setImageMarginTop();
                statusImageView.setVisibility(VISIBLE);
                loadViewLl.setVisibility(GONE);
                tipTv.setVisibility(VISIBLE);
                final_tip_tv.setVisibility(VISIBLE);
                final_tip_tv.setText("在这里，您可以集中接收并管理融资企业发来的融资需求。");
                tipTv.setText("您尚无融资申请待办事宜，或是尚未得到融资企业授权。可以去“应收账款凭证”中试试。");
                tipTv.setTextSize(18);
                tipTv.setTextColor(getResources().getColor(R.color.color_030303));
                statusImageView.setImageResource(R.mipmap.no_rongzi);
                break;
            }
            case NO_CUSTOMER_LIST: {
                statusImageView.setVisibility(VISIBLE);
                loadViewLl.setVisibility(GONE);
                tipTv.setVisibility(VISIBLE);
                tipTv.setText("APP版本功能正待上线！");
                tipTv.setTextSize(18);
                tipTv.setTextColor(getResources().getColor(R.color.color_39425f));
                statusImageView.setImageResource(R.mipmap.no_voucher_list);
                break;
            }
        }

    }

    public void setOnErrorClickListener(OnErrorClickListener onErrorClickListener) {
        this.onErrorClickListener = onErrorClickListener;
    }

    public void setImageMarginTop() {
        if (ScreenUtils.getScreenWidth() == 1080 && ScreenUtils.getScreenHeight() == 1920) {
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 0);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.BELOW, R.id.final_tip_tv);
            statusImageView.setLayoutParams(params);
        } else {
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(0, DipUtils.dp2px(getContext(), 50), 0, 0);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.BELOW, R.id.final_tip_tv);
            statusImageView.setLayoutParams(params);
        }
    }

    public void resetImageParams() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//        params.setMargins(0, 0, 0, 0);
        statusImageView.setLayoutParams(params);
    }
}
