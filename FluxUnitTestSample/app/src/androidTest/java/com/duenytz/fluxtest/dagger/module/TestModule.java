package com.duenytz.fluxtest.dagger.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Singleton @Module //
public final class TestModule {

  //We can inject test objects here

  @Provides @Singleton String provideString() {
    return "Some dummy string";
  }
}
