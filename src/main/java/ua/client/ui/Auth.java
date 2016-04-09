package ua.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ua.client.GreetingService;
import ua.client.model.User;
import ua.server.GreetingServiceImpl;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Surger on 09.04.2016.
 */
public class Auth extends Composite {

    public static final Logger logger = Logger.getLogger(Auth.class.getName());

    interface AuthUiBinder extends UiBinder<HTMLPanel, Auth> {
    }

    @UiField
    TextBox login;
    @UiField
    PasswordTextBox password;
    @UiField
    Button signin;

    private static AuthUiBinder ourUiBinder = GWT.create(AuthUiBinder.class);

    public Auth() {
        logger.info("Initial widget authorization.....");
        initWidget(ourUiBinder.createAndBindUi(this));
        logger.info("Initial finished");
        signin.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
               GreetingService.App.getInstance().authorizedUser(login.getText(), password.getText(), new AsyncCallback<User>() {
                   @Override
                   public void onFailure(Throwable throwable) {
                        logger.info(throwable.toString());
                   }

                   @Override
                   public void onSuccess(User user) {
                      if (user != null){
                          RootPanel.get("body").clear();
                          RootPanel.get("body").add(new Greeting(user.getName()));
                      }
                   }
                   });
            }
        });
    }
}