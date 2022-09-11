package ml.windleaf.craftingexplode.events;

import ml.windleaf.craftingexplode.CraftingExplode;
import ml.windleaf.craftingexplode.process.ExplodeProcess;
import ml.windleaf.craftingexplode.process.ScheduledTask;
import ml.windleaf.craftingexplode.process.warning.WarningThread;
import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class PlayerOpenTable implements Listener {
  private final CraftingExplode plugin;

  public PlayerOpenTable(CraftingExplode plugin) {
    this.plugin = plugin;
  }

  @EventHandler(priority = EventPriority.HIGH)
  public void onInventoryOpenEvent(InventoryOpenEvent e) {
    if (e.getInventory().getType() == InventoryType.WORKBENCH) {
      HumanEntity human = e.getPlayer();
      String uuid = human.getUniqueId().toString();
      Location location = e.getInventory().getLocation();
      plugin.warningList.addWarningThread(uuid, new WarningThread(this.plugin, human, this.plugin.getConfig().getInt("process.delay")));
      plugin.process.addProcess(uuid, new ExplodeProcess(new ScheduledTask(() -> {
        assert location != null;
        human.getWorld().createExplosion(location, this.plugin.getConfig().getInt("process.power"));
        plugin.process.cancelTask(uuid);
      }), location));
    }
  }
}
