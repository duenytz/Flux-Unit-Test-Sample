package com.duenytz.fluxtest.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.duenytz.fluxtest.App;
import com.duenytz.fluxtest.R;
import com.duenytz.fluxtest.action.creators.MyActionsCreator;
import com.duenytz.fluxtest.event.OnMessageError;
import com.duenytz.fluxtest.event.OnMessageReceived;
import com.duenytz.fluxtest.store.MyStore;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  @Inject EventBus bus;
  @Inject MyActionsCreator myActionsCreator;
  @Inject MyStore myStore;

  // Butterknife bindings
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.drawer_layout) DrawerLayout drawer;
  @Bind(R.id.nav_view) NavigationView navigationView;
  @Bind(R.id.text_view) TextView textView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((App) getApplicationContext()).getComponent().inject(this);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);

    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override protected void onResume() {
    super.onResume();
    bus.register(this);
    bus.register(myStore);
  }

  @Override protected void onPause() {
    bus.unregister(myStore);
    bus.unregister(this);
    super.onPause();
  }

  @Override public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody") //
  @Override public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @OnClick(R.id.fab) void onFabClicked(View view) {
    Snackbar.make(view, "Handling toast!", Snackbar.LENGTH_SHORT).setAction("Ok", null).show();
    myActionsCreator.showRandomToast();
  }

  @Subscribe public void onEvent(OnMessageReceived event) {
    textView.setText(event.message);
    Toast.makeText(this, "The message was: " + event.message, Toast.LENGTH_SHORT).show();
  }

  @Subscribe public void onEvent(OnMessageError event) {
    textView.setText("Error");
    Toast.makeText(this, "There was an error: " + event.throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
  }
}
