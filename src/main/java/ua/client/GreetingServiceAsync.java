package ua.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ua.client.model.User;

import java.util.List;

/**
 * Created by Surger on 09.04.2016.
 */
public interface GreetingServiceAsync {
    void getAllUsers(AsyncCallback<List<User>> async);

    void AuthorizedUser(String name, String password, AsyncCallback<Void> async);


}
