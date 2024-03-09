package com.bank.manage.application.views.wheelview.listener;


import com.bank.manage.application.bean.AddressBean;

public interface OnAddressChangeListener {
	void onAddressChange(AddressBean province, AddressBean.ChildrenBeanX city, AddressBean.ChildrenBeanX.ChildrenBean district);
}
