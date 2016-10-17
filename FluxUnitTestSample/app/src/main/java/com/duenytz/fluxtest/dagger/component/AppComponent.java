package com.duenytz.fluxtest.dagger.component;

import com.duenytz.fluxtest.dagger.module.ApiModule;
import com.duenytz.fluxtest.dagger.module.AppModule;
import com.duenytz.fluxtest.dagger.module.StoreModule;
import com.duenytz.fluxtest.view.activity.MainActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { AppModule.class, ApiModule.class, StoreModule.class, }) //
public interface AppComponent {
  void inject(MainActivity mainActivity);
}
