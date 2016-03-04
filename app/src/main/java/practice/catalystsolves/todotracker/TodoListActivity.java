package practice.catalystsolves.todotracker;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

import practice.catalystsolves.todotracker.network.TaskService;

public class TodoListActivity extends AppCompatActivity implements AccountManagerCallback<Bundle> {

  private final String TAG = getClass().getSimpleName();
  private AccountManager accountManager;
  private String type;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_todo_list);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    type = getString(R.string.accountType);
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
    accountManager = AccountManager.get(this);
    Account acc = new Account(type,type);

    //accountManager.getAuthToken(acc, type, null,false, this, null);

      accountManager.getAuthToken(acc, type, null,this, this, null);

  }

  @Override
  protected void onResume() {
    Log.d(TAG, "RESUMED");

    super.onResume();

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_todo_list, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
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

  @Override
  public void run(final AccountManagerFuture<Bundle> future) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {

          Bundle bnd = future.getResult();
          final String authtoken = bnd.getString(AccountManager.KEY_AUTHTOKEN);
          if (authtoken != null) {
            Log.v(TAG,new TaskService(authtoken).getAllTasks().execute().body().string());
           // return;
          }
          // this callback interface method
         // logoutCallback.onLogoutFinished(authtoken);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();
//    Log.v(TAG,b.getString(AccountManager.KEY_AUTHTOKEN));
  }
}
