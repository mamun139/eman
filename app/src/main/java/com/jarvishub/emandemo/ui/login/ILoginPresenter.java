package com.jarvishub.emandemo.ui.login;

import com.jarvishub.emandemo.model.User;

/**
 * Created by mrrobot on 8/23/17.
 */

public interface ILoginPresenter {
    void setView(ILoginView view);

    void attemptLogin(User user);

    void onSuccess();
}
