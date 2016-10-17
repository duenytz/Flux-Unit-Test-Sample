package com.duenytz.fluxtest.action.creators;

import com.duenytz.fluxtest.dispatcher.Dispatcher;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public abstract class ActionsCreator {
  protected Dispatcher dispatcher;

  public ActionsCreator(Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }

  @SuppressWarnings("unchecked") //
  protected void executeTask(Observable observable, Action1<?> success, Action1<Throwable> error) {
    observable.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe(success, error);
  }
}
