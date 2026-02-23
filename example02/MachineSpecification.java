package boinsoft.example02;

/** A specification of the machine we are provioning. */
public class MachineSpecification {
  private final int ramMegabytes_;

  public MachineSpecification(int ramMegabytes) {
    this.ramMegabytes_ = ramMegabytes;
  }

  public int getRamMegabytes() {
    return ramMegabytes_;
  }
}
