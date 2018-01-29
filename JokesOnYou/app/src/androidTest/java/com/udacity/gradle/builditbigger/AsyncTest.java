package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.text.TextUtils;

import java.util.concurrent.CountDownLatch;

/**
 * Created by destevancardozoj on 1/29/18.
 */

public class AsyncTest extends ApplicationTestCase<Application> {

  String mJoke = null;
  Exception mError = null;
  CountDownLatch signal = null;

  public AsyncTest() {
    super(Application.class);
  }

  @Override
  protected void setUp() throws Exception {
    signal = new CountDownLatch(1);
  }

  @Override
  protected void tearDown() throws Exception {
    signal.countDown();
  }

  public void testAlbumGetTask() throws InterruptedException {
    EndpointsAsyncTask task = new
        EndpointsAsyncTask();
    task.setListener(new EndpointsAsyncTask.GetJokeTaskListener() {
      @Override
      public void onComplete(String joke, Exception e) {
        mJoke = joke;
        mError = e;
        signal.countDown();
      }
    }).execute("");
    signal.await();
    assertNull(mError);
    assertFalse(TextUtils.isEmpty(mJoke));
  }
}
