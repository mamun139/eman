package com.jarvishub.emandemo.di.custom_annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by mrrobot on 9/13/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}
