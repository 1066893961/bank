/*
 *  Copyright 2010 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.bank.manage.application.views.wheelview.adapter;

import android.content.Context;


import com.bank.manage.application.bean.AddressBean;

import java.util.List;


public class AreaWheelAdapter extends BaseWheelAdapter<AddressBean.ChildrenBeanX.ChildrenBean> {
    public AreaWheelAdapter(Context context, List<AddressBean.ChildrenBeanX.ChildrenBean> list) {
        super(context,list);
    }

    @Override
    protected CharSequence getItemText(int index) {
        AddressBean.ChildrenBeanX.ChildrenBean data = getItemData(index);
        if (data != null) {
            return data.getName();
        }
        return null;
    }
}
