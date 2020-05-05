package com.jarvishub.emandemo.ui.createOrder;

import com.jarvishub.emandemo.model.CreateOrderInfo;
import com.jarvishub.emandemo.ui.home.IMapView;

/**
 * Created by mrrobot on 9/28/17.
 */

public interface ICreateOrderPresenter {
    void setView(IMapView view);

    void sendOrderDataToServer(CreateOrderInfo createOrderInfo);
}
