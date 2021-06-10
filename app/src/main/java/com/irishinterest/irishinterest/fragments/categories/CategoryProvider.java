package com.irishinterest.irishinterest.fragments.categories;

import com.irishinterest.irishinterest.model.categories.CategoryValues;
import com.irishinterest.irishinterest.model.provider.Provider;
import com.irishinterest.irishinterest.network.api.irishInterest.NotificationOnChange;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Action;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiMessage;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiToModel;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class CategoryProvider extends Provider<CategoryValues> implements Observer {

    private CategoryValues categories;

    public CategoryProvider(GuiToModel guiToModel, NotificationOnChange<CategoryValues> notificationOnChange) {
        super(guiToModel, notificationOnChange);
        this.notificationOnChange.registerProvider(this);
        this.guiToModel.registerObserver(Module.CATEGORIES, this);
        //Request initial data.
        //this.pushMessage(new GuiMessage<CategoryValues>(Module.CATEGORIES, Action.INIT));
    }

    @Override
    protected ArrayList<CategoryValues> getData() {
        ArrayList<CategoryValues> arrayList = new ArrayList<>();
        arrayList.add(categories);
        return arrayList;
    }

    @Override
    protected void processMessage(GuiMessage<CategoryValues> guiMessage) {
        if (guiMessage.getAction().equals(Action.INIT)) {
            init(guiMessage);
        } else if (guiMessage.getAction().equals(Action.ADD)) {
            add(guiMessage);
        } else if (guiMessage.getAction().equals(Action.MODIFY)) {
            modify(guiMessage);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof GuiMessage) {
            GuiMessage guiMessage = (GuiMessage) arg;
            if (guiMessage.getModule().equals(Module.CATEGORIES)) {
                processMessage(guiMessage);
            }
        }
    }

    @Override
    protected void init(GuiMessage<CategoryValues> guiMessage) {
        this.categories = guiMessage.getObject();
        notificationOnChange.add(categories);
    }
}
