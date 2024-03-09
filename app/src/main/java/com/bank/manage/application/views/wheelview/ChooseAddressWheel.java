package com.bank.manage.application.views.wheelview;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.bank.manage.application.R;
import com.bank.manage.application.bean.AddressBean;
import com.bank.manage.application.views.wheelview.adapter.AreaWheelAdapter;
import com.bank.manage.application.views.wheelview.adapter.CityWheelAdapter;
import com.bank.manage.application.views.wheelview.adapter.ProvinceWheelAdapter;
import com.bank.manage.application.views.wheelview.listener.OnAddressChangeListener;
import com.bank.manage.application.views.wheelview.wheelview.MyOnWheelChangedListener;
import com.bank.manage.application.views.wheelview.wheelview.MyWheelView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseAddressWheel implements MyOnWheelChangedListener {

    @BindView(R.id.province_wheel)
    MyWheelView provinceWheel;
    @BindView(R.id.city_wheel)
    MyWheelView cityWheel;
    @BindView(R.id.district_wheel)
    MyWheelView districtWheel;

    private Activity context;
    private View parentView;
    private PopupWindow popupWindow = null;
    private WindowManager.LayoutParams layoutParams = null;
    private LayoutInflater layoutInflater = null;
    private AddressBean provinceEntity = new AddressBean();
    private AddressBean.ChildrenBeanX cityEntity = new AddressBean.ChildrenBeanX();
    private AddressBean.ChildrenBeanX.ChildrenBean areaEntity = new AddressBean.ChildrenBeanX.ChildrenBean();
    private List<AddressBean> province = null;

    private OnAddressChangeListener onAddressChangeListener = null;

    public ChooseAddressWheel(Activity context) {
        this.context = context;
        init();
    }

    private void init() {
        layoutParams = context.getWindow().getAttributes();
        layoutInflater = context.getLayoutInflater();
        initView();
        initPopupWindow();
    }

    /**
     * 设置默认选中的城市
     * @param position
     */
    public void setItem(int position){
        provinceWheel.setCurrentItem(position);
        cityWheel.setCurrentItem(position);
        districtWheel.setCurrentItem(position);
    }

    private void initView() {
        parentView = layoutInflater.inflate(R.layout.choose_city_layout, null);
        ButterKnife.bind(this, parentView);

        provinceWheel.setVisibleItems(5);
        cityWheel.setVisibleItems(5);
        districtWheel.setVisibleItems(5);

        provinceWheel.addChangingListener(this);
        cityWheel.addChangingListener(this);
        districtWheel.addChangingListener(this);
    }

    private void initPopupWindow() {
        popupWindow = new PopupWindow(parentView, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setAnimationStyle(R.style.anim_push_bottom);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                layoutParams.alpha = 1.0f;
                context.getWindow().setAttributes(layoutParams);
                popupWindow.dismiss();
            }
        });
    }

    private void bindData() {
        provinceWheel.setViewAdapter(new ProvinceWheelAdapter(context, province));
        updateCitiy();
        updateDistrict();
    }

    @Override
    public void onChanged(MyWheelView wheel, int oldValue, int newValue) {
        if (wheel == provinceWheel) {
            updateCitiy();//省份改变后城市和地区联动
        } else if (wheel == cityWheel) {
            updateDistrict();//城市改变后地区联动
        } else if (wheel == districtWheel) {
        }
    }

    private void updateCitiy() {
        int index = provinceWheel.getCurrentItem();
        List<AddressBean.ChildrenBeanX> citys = province.get(index).getChildren();
        if (citys != null && citys.size() > 0) {
            cityWheel.setViewAdapter(new CityWheelAdapter(context, citys));
            cityWheel.setCurrentItem(0);
            updateDistrict();
        }
    }

    private void updateDistrict() {
        int provinceIndex = provinceWheel.getCurrentItem();
        List<AddressBean.ChildrenBeanX> citys = province.get(provinceIndex).getChildren();
        int cityIndex = cityWheel.getCurrentItem();
        List<AddressBean.ChildrenBeanX.ChildrenBean> districts = citys.get(cityIndex).getChildren();
        if (districts != null && districts.size() > 0) {
            districtWheel.setViewAdapter(new AreaWheelAdapter(context, districts));
            districtWheel.setCurrentItem(0);
        }

    }

    public void show(View v) {
        layoutParams.alpha = 0.6f;
        context.getWindow().setAttributes(layoutParams);
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }

    public void setProvince(List<AddressBean> province) {
        this.province = province;
        bindData();
    }

    public void defaultValue(String provinceStr, String city, String arae) {
        if (TextUtils.isEmpty(provinceStr)) {
            return;
        }
        for (int i = 0; i < province.size(); i++) {
            AddressBean provinces = province.get(i);
            if (provinces != null && provinces.getName().equalsIgnoreCase(provinceStr)) {
                provinceWheel.setCurrentItem(i);
                if (TextUtils.isEmpty(city)) {
                    return;
                }
                List<AddressBean.ChildrenBeanX> citys = provinces.getChildren();
                for (int j = 0; j < citys.size(); j++) {
                    AddressBean.ChildrenBeanX cityEntity = citys.get(j);
                    if (cityEntity != null && cityEntity.getName().equalsIgnoreCase(city)) {
                        cityWheel.setViewAdapter(new CityWheelAdapter(context, citys));
                        cityWheel.setCurrentItem(j);
                        if (TextUtils.isEmpty(arae)) {
                            return;
                        }
                        List<AddressBean.ChildrenBeanX.ChildrenBean> areas = cityEntity.getChildren();
                        for (int k = 0; k < areas.size(); k++) {
                            AddressBean.ChildrenBeanX.ChildrenBean areaEntity = areas.get(k);
                            if (areaEntity != null && areaEntity.getName().equalsIgnoreCase(arae)) {
                                districtWheel.setViewAdapter(new AreaWheelAdapter(context, areas));
                                districtWheel.setCurrentItem(k);
                            }
                        }
                    }
                }
            }
        }
    }

    @OnClick(R.id.confirm_button)
    public void confirm() {
        if (onAddressChangeListener != null) {
            int provinceIndex = provinceWheel.getCurrentItem();
            int cityIndex = cityWheel.getCurrentItem();
            int areaIndex = districtWheel.getCurrentItem();

            String provinceName = null, cityName = null, areaName = null;

            List<AddressBean.ChildrenBeanX> citys = null;
            if (province != null && province.size() > provinceIndex) {
               provinceEntity = province.get(provinceIndex);
                citys = provinceEntity.getChildren();
                provinceName = provinceEntity.getName();
            }
            List<AddressBean.ChildrenBeanX.ChildrenBean> districts = null;
            if (citys != null && citys.size() > cityIndex) {
                cityEntity = citys.get(cityIndex);
                districts = cityEntity.getChildren();
                cityName = cityEntity.getName();
            }

            if (districts != null && districts.size() > areaIndex) {
                 areaEntity = districts.get(areaIndex);
                areaName = areaEntity.getName();
            }

            onAddressChangeListener.onAddressChange(provinceEntity, cityEntity, areaEntity);
        }
        cancel();
    }

    @OnClick(R.id.cancel_button)
    public void cancel() {
        popupWindow.dismiss();
    }

    public void setOnAddressChangeListener(OnAddressChangeListener onAddressChangeListener) {
        this.onAddressChangeListener = onAddressChangeListener;
    }
}