package ml.windleaf.craftingexplode.process;

import org.bukkit.Location;

public class ExplodeProcess {
  private final ScheduledTask task;
  private final Location location;

  public ExplodeProcess(ScheduledTask task, Location location) {
    this.task = task;
    this.location = location;
  }

  public ScheduledTask getTask() {
    return this.task;
  }

  public Location getLocation() {
    return this.location;
  }
}
