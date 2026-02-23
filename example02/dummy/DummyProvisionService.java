package boinsoft.example02.dummy;

import boinsoft.example02.*;
import java.util.concurrent.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.osgi.service.component.annotations.*;

class StatusImpl implements Status {
  private final boolean finished_;

  public StatusImpl(boolean finished) {
    finished_ = finished;
  }

  public String message() {
    if (finished_) {
      return "FINISHED";
    } else {
      return "RUNNING";
    }
  }

  public int statusCode() {
    if (finished_) {
      return 0;
    } else {
      return 1;
    }
  }

  public boolean finished() {
    return finished_;
  }

  public boolean success() {
    if (finished()) {
      return true;
    } else {
      return false;
    }
  }
}

class HandleImpl implements Handle, Runnable {
  private static final Logger logger = LogManager.getLogger("HandleImpl");

  protected StatusImpl status_;
  protected ScheduledFuture<?> fut_;

  public HandleImpl() {
    status_ = new StatusImpl(/* finished= */ false);
  }

  public Object getUnderlyingObject() {
    return this;
  }

  // NOTE: if you call this 'get', something calls it, maybe from gogo
  // and auto-waits for future complete
  public Object waitFut() throws Exception {
    return fut_.get();
  }

  public void run() {
    logger.warn("finished provisioning of " + this);
    status_ = new StatusImpl(/* finished= */ true);
  }

  public void setFuture(ScheduledFuture<?> fut) {
    this.fut_ = fut;
  }
}

/** A provision service implementation that does nothing. */
@Component(immediate = true, service = ProvisionService.class)
public class DummyProvisionService implements ProvisionService {
  private static final Logger logger = LogManager.getLogger("DummyProvisionService");

  private ScheduledExecutorService scheduler;

  @Activate
  public void start() {
    scheduler = Executors.newScheduledThreadPool(1);
  }

  @Deactivate
  public void stop() {
    scheduler.shutdown();
    scheduler = null;
  }

  public Handle start(MachineSpecification spec) {
    logger.warn("running new provisioning");
    HandleImpl i = new HandleImpl();
    i.setFuture(scheduler.schedule(i, 4, TimeUnit.SECONDS));
    return i;
  }

  public Status checkStatus(Handle handle) {
    HandleImpl ix = (HandleImpl) handle.getUnderlyingObject();
    return ix.status_;
  }
}
