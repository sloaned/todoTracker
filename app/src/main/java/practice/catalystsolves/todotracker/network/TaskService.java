package practice.catalystsolves.todotracker.network;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import practice.catalystsolves.todotracker.entity.LoginEntity;
import practice.catalystsolves.todotracker.entity.Task;
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
public class TaskService {
  private ItemApi service;

  public TaskService(String authtoken) {
    service = new Instance(authtoken).getInstance();
  }

  private interface ItemApi {
    @GET("/allTasks")
    Call<ResponseBody> getAllTasks();

    @GET("/task/{id}")
    Call<Task> getTask(@Path("id") Integer id);

    @DELETE("/task/{id}")
    Boolean deleteTask(Integer id);

    @PUT("/task")
    Boolean editTask(Task task);

    @POST("/task")
    Boolean createTask(Task task);
  }

  public Call<ResponseBody> getAllTasks(){
    return service.getAllTasks();
  }


  private class Instance{
    private String API_URL = "http://192.168.0.23:8080";
    //private String API_URL = "http://pc30122.catalystsolves.com:8080";

    private ItemApi instance;

    public Instance(String authtoken) {
      instance = new Retrofit.Builder()
          .baseUrl(API_URL).client(new OkHttpClient().newBuilder().addInterceptor(new Interceptor(authtoken)).build())
          .addConverterFactory(GsonConverterFactory.create())
          .build().create(ItemApi.class);
    }

    public ItemApi getInstance() {
      return instance;
    }
  }
}
