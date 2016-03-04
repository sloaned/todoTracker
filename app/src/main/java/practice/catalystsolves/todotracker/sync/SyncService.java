package practice.catalystsolves.todotracker.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by g on 3/3/16.
 */
public class SyncService extends Service {
  private static final Object sSyncAdapterLock = new Object();
  private final String TAG = getClass().getSimpleName();
  private static SyncAdapter sSyncAdapter = null;
  @Override
  public void onCreate() {
    synchronized (sSyncAdapterLock) {
      if (sSyncAdapter == null) {
        sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
      }
    }
  }
  @Override
  public IBinder onBind(Intent intent) {
    Log.v(TAG, "BIND");
    return sSyncAdapter.getSyncAdapterBinder();
  }
}