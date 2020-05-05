package com.jarvishub.emandemo.di.component;

import com.jarvishub.emandemo.di.custom_annotation.PerService;
import com.jarvishub.emandemo.di.module.ServiceModule;
import com.jarvishub.emandemo.service.HttpService;

import dagger.Component;

/**
 * Created by mrrobot on 9/13/17.
 */
@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(HttpService service);

}