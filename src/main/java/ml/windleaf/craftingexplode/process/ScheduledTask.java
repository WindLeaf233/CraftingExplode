package ml.windleaf.craftingexplode.process;

import org.bukkit.scheduler.BukkitRunnable;

public class ScheduledTask extends BukkitRunnable {
  private final Runnable runnable;

  public ScheduledTask(Runnable runnable) {
    this.runnable = runnable;
  }

  @Override
  public void run() {
    this.runnable.run();
  }
}
