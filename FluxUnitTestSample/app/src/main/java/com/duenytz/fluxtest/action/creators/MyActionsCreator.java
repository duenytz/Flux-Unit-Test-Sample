package com.duenytz.fluxtest.action.creators;

import com.duenytz.fluxtest.action.Actions;
import com.duenytz.fluxtest.action.Keys;
import com.duenytz.fluxtest.dispatcher.Dispatcher;
import java.util.Random;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * Created by duenytz on 06/02/16.
 */
@Singleton //
public class MyActionsCreator extends ActionsCreator {

  @Inject public MyActionsCreator(Dispatcher dispatcher) {
    super(dispatcher);
  }

  public void showRandomToast() {
    executeTask(getTextToShow(), response -> dispatcher.dispatch(Actions.MESSAGE_RECEIVED, Keys.MESSAGE, response),
        e -> dispatcher.dispatch(Actions.MESSAGE_ERROR, Keys.EXCEPTION, e));
  }

  private Observable<String> getTextToShow() {
    final String[] array =
        new String[] { "Toast 1", "Toast 2", "Toast 3", "Toast 4", "Toast 5", "Toast 6", "Toast 7", "Toast 8", "Toast 9", "Toast 10", };
    return Observable.create(subscriber -> {
      try {
        subscriber.onNext(array[new Random().nextInt(array.length)]);
        subscriber.onCompleted();
      } catch (Exception e) {
        subscriber.onError(e);
      }
    });
  }
}