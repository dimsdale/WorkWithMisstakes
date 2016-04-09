package ua.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import ua.client.i18n.Labels;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Surger on 09.04.2016.
 */
public class Greeting extends Composite {

    interface GreetingUiBinder extends UiBinder<HTMLPanel, Greeting> {
    }

    private Date date = new Date();

    private Labels labels = GWT.<Labels>create(Labels.class);

    public static final Logger logger = Logger.getLogger(Greeting.class.getName());

    @UiField
    Label greeting;
    @UiField
    Hyperlink hyperlink;

    private static GreetingUiBinder ourUiBinder = GWT.create(GreetingUiBinder.class);

    public Greeting(String name) {
        logger.info("Initial Greeting....");
        initWidget(ourUiBinder.createAndBindUi(this));
        greeting.setText(getWelcomeMessage(date) + " " + name);
        initialAnchor(hyperlink);

    }

    public void initialAnchor (Hyperlink hyperlink){
        hyperlink.setText(labels.Exit());
        hyperlink.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RootPanel.get("body").clear();
                RootPanel.get("body").add(new Auth());
                logger.info("Logout...");
            }
        });
    }


    public String getWelcomeMessage(Date date){
        logger.info("Getting time user...");
        int hour = date.getHours();
        logger.info("Initialize welcome message");
        if (hour >= 23 || hour >= 0 && hour <= 5){
            return labels.GoodNight();
        } else if (hour >= 6 && hour <= 8){
            return labels.GoodMorning();
        } else if (hour >= 9 && hour <= 18){
            return labels.GoodDay();
        } else if (hour >=19 && hour <=22) {
            return labels.GoodEvening();
        } else return null;
    }
}