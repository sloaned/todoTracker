package practice.catalystsolves.todotracker.network;

import okhttp3.ResponseBody;
import practice.catalystsolves.todotracker.entity.LoginEntity;
import practice.catalystsolves.todotracker.entity.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by g on 3/3/16.
 */
public class UserService {
  private UserApi service = new Instance().getInstance();

  private interface UserApi {
    @GET("/user/")
    Call<User> getUser();

    @DELETE("/user/{id}")
    Boolean deleteUser(@Path("id") Integer id);

//    @PUT("/user")
//    fun editUser(task: Task): Boolean
//
//    @POST("/user")
//    fun createUser(task: Task): Boolean

    @POST("/login")
    Call<ResponseBody> login(@Body LoginEntity login);
  }

  public Call<ResponseBody> login(@Body LoginEntity login){
    return service.login(login);
  }
  public Call<User> getUser(){
    return service.getUser();
  }

  private class Instance{
    private String API_URL = "http://192.168.0.23:8080";
    //private String API_URL = "http://pc30122.catalystsolves.com:8080";

    private UserApi instance = new Retrofit.Builder()
        .baseUrl(API_URL)//.client(client)
    .addConverterFactory(GsonConverterFactory.create())
        .build().create(UserApi.class);

    public UserApi getInstance() {
      return instance;
    }
  }
}
