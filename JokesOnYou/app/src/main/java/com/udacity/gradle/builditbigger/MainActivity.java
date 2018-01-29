package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jokesonyou.jokestage.StageActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if(id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void tellJoke(View view) {
    new EndpointsAsyncTask().setListener(new EndpointsAsyncTask.GetJokeTaskListener() {
      @Override
      public void onComplete(String joke, Exception e) {
        startActivityStage(joke);
      }
    }).execute("");
  }

  private void startActivityStage(String joke) {
    Intent intent = new Intent(MainActivity.this, StageActivity.class);
    Bundle b = new Bundle();
    b.putString(StageActivity.THE_JOKE, joke);
    intent.putExtras(b);
    startActivity(intent);
  }
}
