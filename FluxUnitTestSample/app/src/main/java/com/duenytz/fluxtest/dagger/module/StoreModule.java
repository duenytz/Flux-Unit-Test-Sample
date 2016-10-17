package com.duenytz.fluxtest.dagger.module;

import com.duenytz.fluxtest.store.MyStore;
import com.duenytz.fluxtest.store.impl.MyStoreImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by duenytz on 6/8/16.
 */

@Singleton @Module //
public final class StoreModule {
  @Provides @Singleton MyStore provideTestStore(EventBus bus) {
    return new MyStoreImpl(bus);
  }
}
