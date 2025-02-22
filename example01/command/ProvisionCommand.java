package boinsoft.example01.command;

import boinsoft.example01.*;
import org.osgi.service.component.annotations.*;

@Component(
    property = {"osgi.command.scope=provision", "osgi.command.function=provision"},
    immediate = true,
    service = Object.class)
public class ProvisionCommand {

  @Reference private ProvisionService provisionService_;

  public Handle start() {
    return provisionService_.start(new MachineSpecification(256));
  }
}
