package com.jarvishub.emandemo.di.module;

import android.app.Service;
import android.content.Context;

import com.jarvishub.emandemo.service.HttpService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mrrobot on 9/13/17.
 */


@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }
/*
    @Provides
    @Singleton
    HttpService provideHttpService(Context context) {
        return new HttpService(context);
    }
*/

}