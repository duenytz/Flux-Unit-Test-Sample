package com.duenytz.fluxtest.event;

/**
 * Created by duenytz on 6/8/16.
 */
public class OnMessageError {
  public final Throwable throwable;

  public OnMessageError(Throwable throwable) {
    this.throwable = throwable;
  }
}
