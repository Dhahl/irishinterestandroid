package com.irishinterest.irishinterest.fragments.mainScreen;

import com.irishinterest.irishinterest.model.books.Book;
import com.irishinterest.irishinterest.model.books.BookValues;
import com.irishinterest.irishinterest.model.provider.Provider;
import com.irishinterest.irishinterest.network.api.irishInterest.NotificationOnChange;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Action;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiMessage;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiToModel;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.InternalAction;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.ScrollAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class MainScreenProvider.
 * This class provides all the necessary information for MainScreen (Books).
 */
public class MainScreenProvider extends Provider<BookValues> implements Observer {

    private BookValues bookValues;
    private AtomicBoolean dataIsReady = new AtomicBoolean();

    /**
     * Normal constructor.
     * @param guiToModel - guiToModel interface
     * @param notificationOnChange - notificationOnChange reference
     */
    public MainScreenProvider(GuiToModel guiToModel, NotificationOnChange<BookValues> notificationOnChange) {
        super(guiToModel, notificationOnChange);
        this.notificationOnChange.registerProvider(this);
        this.guiToModel.registerObserver(Module.MAIN_SCREEN, this);
        dataIsReady.set(false);
        notification = true;
    }

    /**
     * Update action.
     * @param o - observable object.
     * @param arg - object argument.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof GuiMessage) {
            GuiMessage guiMessage = (GuiMessage) arg;
            if (guiMessage.getModule().equals(Module.MAIN_SCREEN) && guiMessage.getInternalAction().equals(InternalAction.RESPONSE)) {
                processMessage(guiMessage);
            }
        }
    }

    @Override
    protected ArrayList<BookValues> getData() {
        ArrayList<BookValues> arrayList = new ArrayList<>();
        arrayList.add(this.bookValues);
        return arrayList;
    }

    @Override
    protected void processMessage(GuiMessage<BookValues> guiMessage) {
        if (guiMessage.getAction().equals(Action.INIT)) {
            init(guiMessage);
        } else if (guiMessage.getAction().equals(Action.ADD)) {
            add(guiMessage);
        } else if (guiMessage.getAction().equals(Action.MODIFY)) {
            modify(guiMessage);
        }
    }

    @Override
    protected void init(GuiMessage<BookValues> guiMessage) {
        //this.bookValues = guiMessage.getObject();
        dataIsReady.set(true);
        add(guiMessage);
    }

    @Override
    protected void add(GuiMessage<BookValues> guiMessage) {
        if(this.bookValues == null) {
            this.bookValues = guiMessage.getObject();
        } else {
            if(guiMessage.getScrollAction() == ScrollAction.SCROLL_DOWN && bookValues.getBooks().size() == 90){
                ArrayList<Book> tmp = new ArrayList<Book>(removeFirst30());
                this.bookValues.setBooks(tmp);
                bookValues.getBooks().addAll(guiMessage.getObject().getBooks());
            }else if(guiMessage.getScrollAction() == ScrollAction.SCROLL_UP && bookValues.getBooks().size() == 90){
                guiMessage.getObject().getBooks().addAll(removeLast30());
                bookValues.setBooks(guiMessage.getObject().getBooks());
            }else{
                bookValues.getBooks().addAll(guiMessage.getObject().getBooks());
            }
            //bookValues.getBooks().addAll(guiMessage.getObject().getBooks());
        }

        if(notification){
            notificationOnChange.add(bookValues);
        }
    }

    @Override
    protected void modify(GuiMessage<BookValues> guiMessage) {
        //TODO
        this.bookValues = guiMessage.getObject();
    }

    public AtomicBoolean dataReady(){
        return dataIsReady;
    }

    public void getInitialData(){
        pushMessage(new GuiMessage(Module.MAIN_SCREEN, Action.INIT, InternalAction.REQUEST));
    }

    private List<Book> removeFirst30(){
        if(bookValues.getBooks().size() == 90) {
            return this.bookValues.getBooks().subList(30, 90);
        }else {
            return this.bookValues.getBooks();
        }
    }

    private List<Book> removeLast30(){
        return this.bookValues.getBooks().subList(0, 60);
    }

    
}
