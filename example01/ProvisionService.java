package boinsoft.example01;

/**
 * An example of what a service might look like, in this case a service which "provisions" some
 * asset, like a virtual machine, by receiving a description of the machine, processing it
 * asynchronously while returning a handle for later status checks.
 */
public interface ProvisionService {
  public Handle start(MachineSpecification spec);

  public Status checkStatus(Handle handle);
}
