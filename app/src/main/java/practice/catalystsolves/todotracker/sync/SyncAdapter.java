package practice.catalystsolves.todotracker.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by g on 3/3/16.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
  private final String TAG = getClass().getSimpleName();
  // Global variables
  // Define a variable to contain a content resolver instance
  ContentResolver mContentResolver;

  /**
   * Set up the sync adapter
   */
  public SyncAdapter(Context context, boolean autoInitialize) {
    super(context, autoInitialize);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
    mContentResolver = context.getContentResolver();
  }


  /**
   * Set up the sync adapter. This form of the
   * constructor maintains compatibility with Android 3.0
   * and later platform versions
   */
  public SyncAdapter(
      Context context,
      boolean autoInitialize,
      boolean allowParallelSyncs) {
    super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
    mContentResolver = context.getContentResolver();

  }

  @Override
  public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
    Log.i(TAG, "Beginning network synchronization");
  }
}