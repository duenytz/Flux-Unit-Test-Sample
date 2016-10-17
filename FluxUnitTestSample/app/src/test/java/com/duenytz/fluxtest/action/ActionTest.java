package com.duenytz.fluxtest.action;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class) //
public class ActionTest {

  @Rule public final ExpectedException exception = ExpectedException.none();

  @Test public void testActionInvalidNullValue() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Value may not be null.");
    new Action.Builder().with(Actions.MESSAGE_RECEIVED).bundle(Keys.MESSAGE, null).build();
  }
}