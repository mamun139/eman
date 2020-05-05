package com.jarvishub.emandemo.data.db;

import android.content.res.Resources;

import com.jarvishub.emandemo.model.User;
import com.jarvishub.emandemo.model.service_packages.Packages;

/**
 * Created by mrrobot on 9/13/17.
 */

public interface IDbHelper {
    Long insertUser(User user) throws Exception;
    User getUser(String mobile) throws Resources.NotFoundException, NullPointerException;
    //Long insertServicePackages(Packages packages) throws Exception;
   // Packages getServicePacksFromLoc() throws Resources.NotFoundException,NullPointerException;
}
