package com.irishinterest.irishinterest.fragments.authors;

import com.irishinterest.irishinterest.model.authors.AuthorValues;
import com.irishinterest.irishinterest.model.provider.Provider;
import com.irishinterest.irishinterest.network.api.irishInterest.NotificationOnChange;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiMessage;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiToModel;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class AuthorsProvider extends Provider<AuthorValues> implements Observer {

    private AuthorValues authors;

    public AuthorsProvider(GuiToModel guiToModel, NotificationOnChange<AuthorValues> notificationOnChange) {
        super(guiToModel, notificationOnChange);
        this.notificationOnChange.registerProvider(this);
        this.guiToModel.registerObserver(Module.AUTHORS, this);
    }

    @Override
    protected ArrayList<AuthorValues> getData() {
        return null;
    }

    @Override
    protected void processMessage(GuiMessage<AuthorValues> guiMessage) {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
