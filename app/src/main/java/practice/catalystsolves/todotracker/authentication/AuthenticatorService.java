package practice.catalystsolves.todotracker.authentication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by g on 3/3/16.
 */
public class AuthenticatorService extends Service {
  @Override
  public IBinder onBind(Intent intent) {
    Log.v("AuthenticatorService", "onBind");
    return new AccountAuthenticator(this).getIBinder();
  }
}
