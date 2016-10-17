package com.duenytz.fluxtest;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * Created by duenytz on 6/8/16.
 */

public class MockTestRunner extends AndroidJUnitRunner {
  @Override public Application newApplication(ClassLoader cl, String className, Context context)
      throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return super.newApplication(cl, TestApp.class.getName(), context);
  }
}
