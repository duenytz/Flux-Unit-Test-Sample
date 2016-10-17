package com.duenytz.fluxtest;

import org.junit.Before;
import org.powermock.core.classloader.annotations.PrepareForTest;
import rx.android.schedulers.AndroidSchedulers;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.newThread;

/**
 * Created by duenytz on 6/10/16.
 */
@PrepareForTest({ AndroidSchedulers.class }) //
public class BaseTest {

  @Before public void beforeClass() {
    mockStatic(AndroidSchedulers.class);
    when(mainThread()).thenReturn(newThread());
  }
}
