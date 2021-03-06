package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by destevancardozoj on 1/29/18.
 */

public class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
  private GetJokeTaskListener mListener;

  @Override
  protected String doInBackground(String... params) {
    MyApi myApiService;
    MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
        new AndroidJsonFactory(), null)
        // options for running against local devappserver
        // - 10.0.2.2 is localhost's IP address in Android emulator
        // - turn off compression when running against local devappserver
        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
          @Override
          public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
            abstractGoogleClientRequest.setDisableGZipContent(true);
          }
        });
    // end options for devappserver

    myApiService = builder.build();

    try {
      return myApiService.getJoke().execute().getData();
    } catch(IOException e) {
      return e.getMessage();
    }
  }

  @Override
  protected void onCancelled() {
    if (this.mListener != null) {
      Exception error = new InterruptedException("AsyncTask cancelled");
      this.mListener.onComplete(null, error);
    }
  }

  @Override
  protected void onPostExecute(String theJoke) {
    mListener.onComplete(theJoke, null);
  }

  public EndpointsAsyncTask setListener(GetJokeTaskListener listener) {
    this.mListener = listener;
    return this;
  }

  public interface GetJokeTaskListener {
    void onComplete(String joke, Exception e);
  }
}

