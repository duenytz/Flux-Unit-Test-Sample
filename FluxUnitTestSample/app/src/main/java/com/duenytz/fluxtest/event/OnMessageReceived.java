package com.duenytz.fluxtest.event;

/**
 * Created by duenytz on 6/8/16.
 */
public class OnMessageReceived {
  public final String message;

  public OnMessageReceived(String message) {
    this.message = message;
  }
}
