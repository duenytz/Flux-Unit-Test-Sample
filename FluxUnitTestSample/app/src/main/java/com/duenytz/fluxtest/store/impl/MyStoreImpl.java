package com.duenytz.fluxtest.store.impl;

import com.duenytz.fluxtest.action.Action;
import com.duenytz.fluxtest.action.Actions;
import com.duenytz.fluxtest.action.Keys;
import com.duenytz.fluxtest.event.OnMessageError;
import com.duenytz.fluxtest.event.OnMessageReceived;
import com.duenytz.fluxtest.store.MyStore;
import com.duenytz.fluxtest.store.Store;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by duenytz on 2/5/16.
 */
@Singleton //
public class MyStoreImpl extends Store implements MyStore {

  @Inject public MyStoreImpl(EventBus bus) {
    super(bus);
  }

  @Override @Subscribe public void onEvent(Action action) {
    switch (action.getType()) {
      case Actions.MESSAGE_RECEIVED:
        String message = (String) action.getData().get(Keys.MESSAGE);
        emitStoreChange(new OnMessageReceived(message));
        break;
      case Actions.MESSAGE_ERROR:
        Throwable e = (Throwable) action.getData().get(Keys.EXCEPTION);
        emitStoreChange(new OnMessageError(e));
        break;
      default:
        //Nothing happens
    }
  }
}
