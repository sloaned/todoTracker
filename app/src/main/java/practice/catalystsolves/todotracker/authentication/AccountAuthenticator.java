package practice.catalystsolves.todotracker.authentication;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import practice.catalystsolves.todotracker.R;
import practice.catalystsolves.todotracker.authentication.login.LoginActivity;
import practice.catalystsolves.todotracker.entity.LoginEntity;
import practice.catalystsolves.todotracker.network.UserService;

/**
 * Created by g on 3/3/16.
 */
public class AccountAuthenticator extends AbstractAccountAuthenticator {
  private String TAG = "AccountAuthenticator";
  private Context context;
  private final String TOKENTYPE;
  public AccountAuthenticator(Context ctx) {
    super(ctx);
    context = ctx;
    TOKENTYPE = context.getString(R.string.accountType);
  }

  @Override
  public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
    Log.v(TAG, "editProperties()");
    return null;
  }

  @Override
  public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
    Log.v(TAG, "addAccount()");
    AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
    Account[] accounts = accountManager.getAccountsByType(TOKENTYPE);
    if (accounts.length == 0) {
      Account newAccount = new Account(TOKENTYPE, TOKENTYPE);
      accountManager.addAccountExplicitly(newAccount, null, null);
    }
    Bundle reply = new Bundle();

    Intent intent = new Intent(context, LoginActivity.class);
    intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//    intent.putExtra("account", accountType);
//    intent.putExtra("token", authTokenType);
//    intent.putExtra("new", true);

    // return our AccountAuthenticatorActivity
    reply.putParcelable(AccountManager.KEY_INTENT, intent);

    return reply;
  }

  @Override
  public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
    Log.v(TAG, "confirmCredentials()");
    return null;
  }

  @Override
  public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
    Log.v(TAG, "getAuthToken()");
    if (!authTokenType.equals(TOKENTYPE)) {
      Log.v(TAG, "does not match");
      Bundle  result = new Bundle();
      result.putString(AccountManager.KEY_ERROR_MESSAGE, "invalid authTokenType");
      return result;
    }
    // Extract the username and password from the Account Manager, and ask
    // the server for an appropriate AuthToken.
    Log.v(TAG, "does match");
    AccountManager am = AccountManager.get(context);
    String password = am.getPassword(account);
    //Log.v(TAG, password);
    if (password != null) {
      Log.v(TAG, "no password not exsist");
      String authToken = authenticate(account.name, password);
      if (!TextUtils.isEmpty(authToken)) {
        Bundle result = new Bundle();
        result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
        result.putString(AccountManager.KEY_ACCOUNT_TYPE, TOKENTYPE);
        result.putString(AccountManager.KEY_AUTHTOKEN, authToken);
        return result;
      }
    }
    // If we get here, then we couldn't access the user's password - so we
    // need to re-prompt them for their credentials. We do that by creating
    // an intent to display our AuthenticatorActivity panel.
    Log.d(TAG,"Login ACtivity");
    Intent intent = new Intent(context, LoginActivity.class);
    //intent.putExtra("username", account.name);
    intent.putExtra(TOKENTYPE, authTokenType);
    intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
    Bundle bundle = new Bundle();
    bundle.putParcelable(AccountManager.KEY_INTENT, intent);
    return bundle;

  }

  @Override
  public String getAuthTokenLabel(String authTokenType) {
    Log.v(TAG, "getAuthTokenLabel()");
    return null;
  }

  @Override
  public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
    Log.v(TAG, "updateCredentials()");
    return null;
  }

  @Override
  public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
    Log.v(TAG, "hasFeatures()");
    return null;
  }
  private String authenticate(String name,String password) {
    Log.v(TAG,"authenticate");
    try {
      return new UserService().login(new LoginEntity(name, password)).execute().headers().get("X-AUTH-TOKEN");
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
