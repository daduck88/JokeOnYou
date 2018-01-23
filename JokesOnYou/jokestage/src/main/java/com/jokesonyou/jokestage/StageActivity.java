package com.jokesonyou.jokestage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class StageActivity extends AppCompatActivity {

  public static final java.lang.String THE_JOKE = "THE_JOKE";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stage);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Bundle b = getIntent().getExtras();
    String theJoke = "";
    if(b != null)
      theJoke = b.getString(THE_JOKE);
    TextView fab = findViewById(R.id.tVTheJoke);
    fab.setText(theJoke);
  }
}
