package com.jarvishub.emandemo.ui.signUp;

import com.jarvishub.emandemo.model.User;

/**
 * Created by mrrobot on 8/23/17.
 */

public interface ISignUpPresenter {
    void setView(ISignUpView view);
    void attemptSignUp(User user);
}
