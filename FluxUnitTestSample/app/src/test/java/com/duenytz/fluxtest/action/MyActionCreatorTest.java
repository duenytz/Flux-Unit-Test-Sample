package com.duenytz.fluxtest.action;

import com.duenytz.fluxtest.action.creators.MyActionsCreator;
import com.duenytz.fluxtest.contants.JUnitTestConstants;
import com.duenytz.fluxtest.dispatcher.Dispatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.lang.Thread.sleep;
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
public class MyActionCreatorTest {
  @Mock Dispatcher dispatcher;

  private MyActionsCreator actionsCreator;
  private ArgumentCaptor<Integer> actionCaptor;
  private ArgumentCaptor<Object> argumentsCaptor;

  @Before public void setUp() {
    actionsCreator = spy(new MyActionsCreator(dispatcher));
    actionCaptor = ArgumentCaptor.forClass(Integer.class);
    argumentsCaptor = ArgumentCaptor.forClass(Object.class);
  }

  @Test public void testActionCreator() throws Exception {
    actionsCreator.showRandomToast();
    sleep(JUnitTestConstants.DELAY_FOR_ASYNC_OBSERVABLE_TO_RUN);
    verify(dispatcher).dispatch(actionCaptor.capture(), argumentsCaptor.capture());
    assertEquals(Actions.MESSAGE_RECEIVED, (Object) actionCaptor.getValue());
    assertEquals(Keys.MESSAGE, argumentsCaptor.getAllValues().get(0));
    assertTrue(argumentsCaptor.getAllValues().get(1).toString().contains("Toast"));
  }
}