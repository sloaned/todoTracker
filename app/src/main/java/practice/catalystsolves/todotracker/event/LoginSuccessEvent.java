package practice.catalystsolves.todotracker.event;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by g on 3/4/16.
 */
public class LoginSuccessEvent {
  private final Response<ResponseBody> response;

  public LoginSuccessEvent(Response<ResponseBody> response) {
    this.response = response;
  }

  public Response<ResponseBody> getResponse() {
    return response;
  }
}
