package practice.catalystsolves.todotracker.listener;

import org.greenrobot.eventbus.EventBus;

import okhttp3.ResponseBody;
import practice.catalystsolves.todotracker.event.LoginErrorEvent;
import practice.catalystsolves.todotracker.event.LoginSuccessEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by g on 3/4/16.
 */
public class LoginListener implements Callback<ResponseBody> {
  @Override
  public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
    EventBus.getDefault().post(new LoginSuccessEvent(response));

  }

  @Override
  public void onFailure(Call<ResponseBody> call, Throwable t) {
    EventBus.getDefault().post(new LoginErrorEvent(t));
  }
}
