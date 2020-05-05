package com.jarvishub.emandemo.ui.servicePackages;

import com.jarvishub.emandemo.model.CreateOrderInfo;
import com.jarvishub.emandemo.model.Order;
import com.jarvishub.emandemo.model.OrderInfo;
import com.jarvishub.emandemo.model.service_packages.Packages;
import com.jarvishub.emandemo.model.service_packages.ServicePackageData;
import com.jarvishub.emandemo.ui.home.IMapView;

import java.util.List;

/**
 * Created by mrrobot on 9/22/17.
 */

public interface IServicePackagesPresenter {
    void setView(IMapView view);
    ServicePackageData getServicePackages();
    void createDraftOrder(CreateOrderInfo order);
    void applyPromo(String promoCode);
}
