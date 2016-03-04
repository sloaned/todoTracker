/*
package practice.catalystsolves.todotracker.authentication.login;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.IOException;

import practice.catalystsolves.todotracker.R;
import practice.catalystsolves.todotracker.entity.LoginEntity;
import practice.catalystsolves.todotracker.network.UserService;

*/
/**
 * Represents an asynchronous login/registration task used to authenticate
 * the user.
 *//*

public class UserLoginTask extends AsyncTask<Void, Void, Void> {

  private LoginActivity loginActivity;
  private final String TAG = getClass().getSimpleName();
  private final String mEmail;
  private final String mPassword;

  private void authenticate(String name,String password) {
    Log.v(TAG, "authenticate");

      //new UserService().login(new LoginEntity(name, password)).enqueue(new LoginListener());
          //.headers().get("X-AUTH-TOKEN");

  }
  UserLoginTask(LoginActivity loginActivity, String email, String password) {
    this.loginActivity = loginActivity;
    mEmail = email;
    mPassword = password;
  }

  @Override
  protected Void doInBackground(Void... params) {
    // TODO: attempt authentication against a network service.

      // Simulate network access.
      authenticate(mEmail,mPassword);
    return null;
  }

  @Override
  protected void onPostExecute(final Void token) {
    loginActivity.setAuthTask(null);
    loginActivity.showProgressBar(false);
    Log.v(TAG,token);
    if (token != null) {
      Log.v(TAG,"token not null");
      Account account = new Account(loginActivity.getString(R.string.accountType), loginActivity.getString(R.string.accountType));
      AccountManager am = AccountManager.get(loginActivity.getBaseContext());
      boolean accountCreated = am.addAccountExplicitly(account, mPassword, null);

      Bundle extras = loginActivity.getIntent().getExtras();
      Intent result = new Intent();
      if (extras != null) {
        if (accountCreated) {  //Pass the new account back to the account manager

          ContentResolver.setSyncAutomatically(account, ContactsContract.AUTHORITY, true);
          AccountAuthenticatorResponse response = extras.getParcelable(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);

          result.putExtra(AccountManager.KEY_ACCOUNT_NAME, loginActivity.getString(R.string.accountType));
          result.putExtra(AccountManager.KEY_ACCOUNT_TYPE, loginActivity.getString(R.string.accountType));
          result.putExtra(AccountManager.KEY_AUTHTOKEN, token);
          //response.onResult(result);
          loginActivity.setAccountAuthenticatorResult(result.getExtras());
          loginActivity.setResult(Activity.RESULT_OK, result);
        }
//        else {
////          ContentResolver.setSyncAutomatically(account, ContactsContract.AUTHORITY, true);
////          am.setPassword(account,mPassword);
////          result.putExtra(AccountManager.KEY_ACCOUNT_NAME, loginActivity.getString(R.string.accountType));
////          result.putExtra(AccountManager.KEY_ACCOUNT_TYPE, loginActivity.getString(R.string.accountType));
////          result.putExtra(AccountManager.KEY_AUTHTOKEN, token);
////          loginActivity.setAccountAuthenticatorResult(result.getExtras());
//          loginActivity.setResult(Activity.RESULT_OK, result);
//
//        }
        loginActivity.finish();
      }

    } else {
      loginActivity.setError(loginActivity.getString(R.string.error_incorrect_password));
      loginActivity.requestPasswordFocus();
    }
  }

  @Override
  protected void onCancelled() {
    loginActivity.setAuthTask(null);
    loginActivity.showProgressBar(false);
  }
}
*/
