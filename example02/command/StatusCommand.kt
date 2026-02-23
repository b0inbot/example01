package boinsoft.example02.command;

import boinsoft.example02.*;
import org.osgi.service.component.annotations.*;

@Component(
    property = ["osgi.command.scope=example02", "osgi.command.function=status"],
    immediate = true,
    service = [Object::class])
class StatusCommand {

  @Reference
  var provisionService_: ProvisionService? = null;

  fun status(h: Handle): String {
    var x = provisionService_?.checkStatus(h)
    if (x != null) { return x.message() }
    else return ""
  }
}
