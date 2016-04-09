package ua.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import ua.client.ui.Auth;


public class Main implements EntryPoint {

  @Override
  public void onModuleLoad() {
    Auth auth = new Auth();
    RootPanel.get("body").add(auth);
  }
}
