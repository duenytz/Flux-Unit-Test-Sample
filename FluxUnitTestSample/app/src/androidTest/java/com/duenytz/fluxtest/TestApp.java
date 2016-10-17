package com.duenytz.fluxtest;

import com.duenytz.fluxtest.dagger.component.AppComponent;
import com.duenytz.fluxtest.dagger.component.DaggerTestAppComponent;
import com.duenytz.fluxtest.dagger.module.AppModule;
import com.duenytz.fluxtest.dagger.module.StoreModule;
import com.duenytz.fluxtest.dagger.module.TestModule;

/**
 * Created by duenytz on 6/8/16.
 */

public class TestApp extends App {
  @Override protected AppComponent createComponent() {
    return DaggerTestAppComponent.builder().appModule(new AppModule(this)).testModule(new TestModule()).storeModule(new StoreModule()).build();
  }
}
