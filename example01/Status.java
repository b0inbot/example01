package boinsoft.example01;

public interface Status {
  public String message();

  public int statusCode();

  public boolean finished();

  public boolean success();
}
