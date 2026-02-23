package boinsoft.example02.command;

import boinsoft.example02.*;
import org.osgi.service.component.annotations.*;


@Component(
    property = ["osgi.command.scope=example02", "osgi.command.function=provision"],
    immediate = true,
    service = [Object::class])
class ProvisionCommand {
  
  @Reference
  var provisionService_: ProvisionService? = null

  fun start(): Handle? {
    return provisionService_?.start(MachineSpecification(256))
  }
}
