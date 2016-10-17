package com.duenytz.fluxtest.page;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by duenytz on 6/15/16.
 */

public class MainPageObject {

  public static void clickView(int viewRes) {
    onView(withId(viewRes)).perform(click());
  }

  public static void checkTextOnView(String text, int viewRes) {
    checkTextOnView(equalTo(text), viewRes);
  }

  public static void checkTextOnView(Matcher<String> matcher, int viewRes) {
    onView(withId(viewRes)).check(matches(withText(matcher)));
  }
}
