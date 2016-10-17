package com.duenytz.fluxtest.dagger.component;

import com.duenytz.fluxtest.dagger.module.AppModule;
import com.duenytz.fluxtest.dagger.module.StoreModule;
import com.duenytz.fluxtest.dagger.module.TestModule;
import com.duenytz.fluxtest.view.activity.MainActivityTest;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { TestModule.class, AppModule.class, StoreModule.class }) //
public interface TestAppComponent extends AppComponent {
  void inject(MainActivityTest mainActivityTest);
}
