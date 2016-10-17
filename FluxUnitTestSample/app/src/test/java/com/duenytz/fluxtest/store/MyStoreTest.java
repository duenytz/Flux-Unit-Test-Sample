package com.duenytz.fluxtest.store;

import com.duenytz.fluxtest.BaseTest;
import com.duenytz.fluxtest.action.Action;
import com.duenytz.fluxtest.action.Actions;
import com.duenytz.fluxtest.action.Keys;
import com.duenytz.fluxtest.contants.JUnitTestConstants;
import com.duenytz.fluxtest.event.OnMessageError;
import com.duenytz.fluxtest.event.OnMessageReceived;
import com.duenytz.fluxtest.store.impl.MyStoreImpl;
import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(PowerMockRunner.class) //
@PrepareForTest({ MyStore.class }) //
public class MyStoreTest extends BaseTest {
  private static final int INVALID_ACTION = 30000;
  @Mock EventBus bus;

  private MyStore myStore;
  private ArgumentCaptor<Object> captor;

  @Before public void setUp() {
    myStore = spy(new MyStoreImpl(bus));
    captor = ArgumentCaptor.forClass(Object.class);
  }

  @Test public void testStoreMessageSucceeded() throws Exception {
    ((MyStoreImpl) myStore).onEvent(new Action.Builder().with(Actions.MESSAGE_RECEIVED).bundle(Keys.MESSAGE, "Test message").build());
    sleep(JUnitTestConstants.DELAY_FOR_ASYNC_OBSERVABLE_TO_RUN);
    verify(bus).post(captor.capture());
    Object capturedObject = captor.getValue();
    assertTrue(capturedObject instanceof OnMessageReceived);
    assertEquals("Test message", ((OnMessageReceived) capturedObject).message);
  }

  @Test public void testStoreMessageError() throws Exception {
    ((MyStoreImpl) myStore).onEvent(new Action.Builder().with(Actions.MESSAGE_ERROR).bundle(Keys.EXCEPTION, new Throwable("error")).build());
    sleep(JUnitTestConstants.DELAY_FOR_ASYNC_OBSERVABLE_TO_RUN);
    verify(bus).post(captor.capture());
    Object capturedObject = captor.getValue();
    assertTrue(capturedObject instanceof OnMessageError);
    assertNotEquals("Test message", ((OnMessageError) capturedObject).throwable.getMessage());
    assertEquals("error", ((OnMessageError) capturedObject).throwable.getMessage());
  }

  @Test public void testNoAction() throws Exception {
    ((MyStoreImpl) myStore).onEvent(new Action.Builder().with(INVALID_ACTION).build());
    sleep(JUnitTestConstants.DELAY_FOR_ASYNC_OBSERVABLE_TO_RUN);
    verify(bus, never()).post(anyObject());
  }
}