package ml.windleaf.craftingexplode.process;

import ml.windleaf.craftingexplode.CraftingExplode;

import java.util.HashMap;

public class PlayerProcess {
  private final CraftingExplode plugin;
  private final HashMap<String, ScheduledTask> processes = new HashMap<>();

  public PlayerProcess(CraftingExplode plugin) {
    this.plugin = plugin;
  }

  public ScheduledTask getTask(String uuid) {
    return this.processes.getOrDefault(uuid, null);
  }

  public void addTask(String uuid, ScheduledTask task) {
    if (!this.isTaskRunning(uuid)) {
      this.processes.put(uuid, task);
      task.runTaskLater(this.plugin, plugin.getConfig().getLong("process.delay"));
    }
  }

  public boolean isTaskRunning(String uuid) {
    return this.processes.containsKey(uuid);
  }

  public void cancelTask(String uuid) {
    if (this.isTaskRunning(uuid)) {
      this.getTask(uuid).cancel();
      this.processes.remove(uuid);
    }
  }
}
