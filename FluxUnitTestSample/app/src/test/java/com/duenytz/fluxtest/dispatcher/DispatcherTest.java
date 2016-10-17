package com.duenytz.fluxtest.dispatcher;

import com.duenytz.fluxtest.action.Action;
import com.duenytz.fluxtest.action.Actions;
import com.duenytz.fluxtest.action.Keys;
import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class) //
public class DispatcherTest {
  private static final int NO_VALID_ACTION = -1;

  @Mock EventBus bus;

  @Rule public final ExpectedException exception = ExpectedException.none();

  private Dispatcher dispatcher;

  @Before public void setUp() {
    dispatcher = spy(new Dispatcher(bus));
  }

  @Test public void testDispatcherInvalidAction() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Type must not be empty");
    dispatcher.dispatch(NO_VALID_ACTION);
  }

  @Test public void testDispatcherInvalidData() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Data must be a valid list of key,value pairs");
    dispatcher.dispatch(Actions.MESSAGE_RECEIVED, Keys.MESSAGE);
  }

  @Test public void testDispatcherValidAction() throws Exception {
    ArgumentCaptor<Object> captor = ArgumentCaptor.forClass(Object.class);
    dispatcher.dispatch(Actions.MESSAGE_RECEIVED, Keys.MESSAGE, "some message", Keys.EXCEPTION, new IllegalStateException("some error"));
    verify(bus).post(captor.capture());
    Object capturedObject = captor.getValue();
    assertTrue(capturedObject instanceof Action);
    Action action = (Action) capturedObject;
    assertEquals(Actions.MESSAGE_RECEIVED, action.getType());
    assertTrue(action.getData().containsKey(Keys.MESSAGE));
    assertTrue(action.getData().containsKey(Keys.EXCEPTION));
    assertEquals("some message", action.getData().get(Keys.MESSAGE));
    assertTrue(action.getData().get(Keys.EXCEPTION) instanceof IllegalStateException);
    assertEquals("some error", ((IllegalStateException) action.getData().get(Keys.EXCEPTION)).getMessage());
  }
}