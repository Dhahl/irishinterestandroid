package com.irishinterest.irishinterest.fragments.detailedBooks;

import com.irishinterest.irishinterest.model.books.BookValues;
import com.irishinterest.irishinterest.model.provider.Provider;
import com.irishinterest.irishinterest.network.api.irishInterest.NotificationOnChange;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiMessage;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiToModel;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class BookDetailedProvider extends Provider<BookValues> implements Observer {

    public BookDetailedProvider(GuiToModel guiToModel, NotificationOnChange<BookValues> notificationOnChange) {
        super(guiToModel, notificationOnChange);
        this.notificationOnChange.registerProvider(this);
        this.guiToModel.registerObserver(Module.BOOK_DETAILED, this);
    }

    @Override
    protected ArrayList<BookValues> getData() {
        return null;
    }

    @Override
    protected void processMessage(GuiMessage<BookValues> guiMessage) {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
