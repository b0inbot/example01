package boinsoft.example01;

/**
 * A Handle is a reference to some provisioning action being done that may take an extended length
 * of time.
 */
public interface Handle {
  /**
   * @return the underlying identifying object for the given task.
   */
  public Object getUnderlyingObject();
}
