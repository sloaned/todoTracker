package practice.catalystsolves.todotracker.event;

/**
 * Created by g on 3/4/16.
 */
public class LoginErrorEvent {
  private final Throwable throwable;

  public LoginErrorEvent(Throwable t) {
    throwable = t;
  }

  public Throwable getThrowable() {
    return throwable;
  }
}
