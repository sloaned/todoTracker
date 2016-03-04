package practice.catalystsolves.todotracker.entity;

import java.util.List;

/**
 * Created by g on 3/3/16.
 */
public class User {

  private int userId;
  private String userEmail;
  private String password;
  private String firstName;
  private String lastName;
  private List<Task> task;

  public List<Task> getTask() {
    return task;
  }

  public void setTask(List<Task> task) {
    this.task = task;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getPassword() {
    return password;
  }


}
