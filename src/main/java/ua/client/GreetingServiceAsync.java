package ua.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ua.client.model.User;

import java.util.List;

public interface GreetingServiceAsync {
    void getAllUsers(AsyncCallback<List<User>> async);


    void authorizedUser(String login, String password, AsyncCallback<User> async);


}
