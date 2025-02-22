package boinsoft.example01.command;

import boinsoft.example01.*;
import org.osgi.service.component.annotations.*;

@Component(
    property = {"osgi.command.scope=provision", "osgi.command.function=status"},
    immediate = true,
    service = Object.class)
public class StatusCommand {

  @Reference private ProvisionService provisionService_;

  public String status(Handle h) {
    return provisionService_.checkStatus(h).message();
  }
}
