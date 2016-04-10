package ua.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import ua.client.model.User;

import java.util.List;


@RemoteServiceRelativePath("GreetingService")
public interface GreetingService extends RemoteService {

    public List<User> getAllUsers();

    public User authorizedUser(String login, String password);



    public static class App {
        private static final GreetingServiceAsync ourInstance = (GreetingServiceAsync) GWT.create(GreetingService.class);

        public static GreetingServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
