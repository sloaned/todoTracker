package practice.catalystsolves.todotracker.entity;

import java.util.List;

/**
 * Created by g on 3/3/16.
 */
public class Task {


  private int id;
  private String taskTitle;
  private String taskDetails;
  private String dueDate;
  private List<User> user;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTaskTitle() {
    return taskTitle;
  }

  public void setTaskTitle(String taskTitle) {
    this.taskTitle = taskTitle;
  }

  public String getTaskDetails() {
    return taskDetails;
  }

  public void setTaskDetails(String taskDetails) {
    this.taskDetails = taskDetails;
  }

  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  public List<User> getUser() {
    return user;
  }

  public void setUser(List <User> user) {
    this.user = user;
  }
}
