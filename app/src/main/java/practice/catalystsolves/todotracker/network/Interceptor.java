package practice.catalystsolves.todotracker.network;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.DownloadManager;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import practice.catalystsolves.todotracker.R;

/**
 * Created by g on 3/3/16.
 */
public class Interceptor implements okhttp3.Interceptor {
  public Interceptor(String key) {
    this.key = key;
  }

  private String key;

  @Override
  public Response intercept(Chain chain) throws IOException {
    return chain.proceed(chain.request().newBuilder().addHeader("X-AUTH-TOKEN",key).build());
  }
}
