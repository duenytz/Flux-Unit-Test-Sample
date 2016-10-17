package com.duenytz.fluxtest.dagger.module;

import android.content.Context;
import com.duenytz.fluxtest.App;
import com.duenytz.fluxtest.BuildConfig;
import com.duenytz.fluxtest.dispatcher.Dispatcher;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.greenrobot.eventbus.EventBus;

@Singleton @Module //
public final class AppModule {
  private final App app;

  public AppModule(App app) {
    this.app = app;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return app;
  }

  @Provides @Singleton EventBus provideEventBus() {
    return EventBus.builder().throwSubscriberException(BuildConfig.DEBUG).installDefaultEventBus();
  }

  @Provides @Singleton Dispatcher provideDispatcher(EventBus bus) {
    return new Dispatcher(bus);
  }
}
