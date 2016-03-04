package practice.catalystsolves.todotracker.entity;

/**
 * Created by g on 3/3/16.
 */
public class LoginEntity {
  private final String username;
  private final String password;

  public LoginEntity(String name, String password) {
    username = name;
    this.password = password;
  }
}
