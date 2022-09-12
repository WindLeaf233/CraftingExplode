package ml.windleaf.craftingexplode.process;

import ml.windleaf.craftingexplode.CraftingExplode;
import org.bukkit.Location;

import java.util.HashMap;

public class PlayerProcess {
  private final CraftingExplode plugin;
  private final HashMap<String, ExplodeProcess> processes = new HashMap<>();

  public PlayerProcess(CraftingExplode plugin) {
    this.plugin = plugin;
  }

  public ScheduledTask getTask(String uuid) {
    ExplodeProcess process = this.processes.getOrDefault(uuid, null);
    return process == null ? null : process.getTask();
  }

  public void addProcess(String uuid, ExplodeProcess process, Long delay) {
    if (!this.isTaskRunning(uuid)) {
      this.processes.put(uuid, process);
      process.getTask().runTaskLater(this.plugin, delay / 1000 * 20); // ticks
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

  public String cancelTask(Location location) {
    String finalUUID = "";
    for (String uuid: this.processes.keySet()) {
      ExplodeProcess process = this.processes.get(uuid);
      if (process.getLocation().equals(location)) {
        finalUUID = uuid;
        break;
      }
    }
    this.cancelTask(finalUUID);
    return finalUUID;
  }
}
