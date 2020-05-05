package com.jarvishub.emandemo.ui.createOrder;

import com.jarvishub.emandemo.model.CreateOrderInfo;

/**
 * Created by mrrobot on 9/28/17.
 */

public interface ICreateOrderView {
    void getCreateOrderDraft(CreateOrderInfo createOrderInfo);
    void getCreateOrderStatus(String createOrderStatus);
}
