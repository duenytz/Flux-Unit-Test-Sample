package com.duenytz.fluxtest.view.activity;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.duenytz.fluxtest.App;
import com.duenytz.fluxtest.R;
import com.duenytz.fluxtest.dagger.component.TestAppComponent;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.duenytz.fluxtest.page.MainPageObject.checkTextOnView;
import static com.duenytz.fluxtest.page.MainPageObject.clickView;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.startsWith;

@MediumTest //
@RunWith(AndroidJUnit4.class) //
public class MainActivityTest {

  @Inject EventBus bus;         //Injected from main graph modules
  @Inject String someMessage;   //Injected from test graph modules (this is just an example)

  @Rule public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, false);

  @Before public void setUp() {
    App app = (App) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    ((TestAppComponent) app.getComponent()).inject(this);   //Injecting dependencies
  }

  @Test public void useAppContext() throws Exception {
    activityRule.launchActivity(null);
    checkTextOnView("Hello World!", R.id.text_view);
    clickView(R.id.fab);
    checkTextOnView(startsWith("Toast"), R.id.text_view);
    assertEquals("Some dummy string", someMessage); //Checking injected string by dagger
    assertNotNull(bus); //Checking injected object by dagger
  }
}