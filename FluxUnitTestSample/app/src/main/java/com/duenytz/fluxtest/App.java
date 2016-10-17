package com.duenytz.fluxtest;

import android.app.Application;
import com.duenytz.fluxtest.dagger.component.AppComponent;
import com.duenytz.fluxtest.dagger.component.DaggerAppComponent;
import com.duenytz.fluxtest.dagger.module.ApiModule;
import com.duenytz.fluxtest.dagger.module.AppModule;
import com.duenytz.fluxtest.dagger.module.StoreModule;
import timber.log.Timber;

/**
 * Created by duenytz on 6/8/16.
 */

public class App extends Application {
  private AppComponent component;

  @Override public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    component = createComponent();
  }

  protected AppComponent createComponent() {
    return DaggerAppComponent.builder().appModule(new AppModule(this)).apiModule(new ApiModule()).storeModule(new StoreModule()).build();
  }

  public AppComponent getComponent() {
    return component;
  }
}
