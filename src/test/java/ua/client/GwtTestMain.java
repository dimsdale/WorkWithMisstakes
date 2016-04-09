package ua.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import ua.client.model.User;
import ua.client.ui.Greeting;

import java.util.Date;
import java.util.List;

public class GwtTestMain extends GWTTestCase {

  public String getModuleName() {
    return "ua.MainJUnit";
  }

  public void testGreetingServiceForNotNullableUsersList () {
    GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    ServiceDefTarget target = (ServiceDefTarget) greetingService;

    target.setServiceEntryPoint(GWT.getModuleBaseURL() + "/Main/GreetingService");

    greetingService.getAllUsers(new AsyncCallback<List<User>>() {
      @Override
      public void onFailure(Throwable throwable) {
        fail("Request failure: " + throwable.getMessage());
      }

      @Override
      public void onSuccess(List<User> users) {
        assertNotNull(users);

        finishTest();
      }
    });
  }
  public void testGreetingServiceForNotAuthorizedUser(){
    GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    ServiceDefTarget target = (ServiceDefTarget) greetingService;

    target.setServiceEntryPoint(GWT.getModuleBaseURL() + "/Main/GreetingService");
    greetingService.authorizedUser("bla", "bla", new AsyncCallback<User>() {
      @Override
      public void onFailure(Throwable throwable) {
        fail("Request failure: " + throwable.getMessage());
      }

      @Override
      public void onSuccess(User user) {
          assertNull(user);

          finishTest();
      }
    });
  }

  public void testGreetingForNotNullableGreetingMessage(){
    Greeting greeting = new Greeting(null);
    Date date = new Date();
    assertNotNull(greeting.getWelcomeMessage(date));
  }

}
