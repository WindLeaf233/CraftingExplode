package ml.windleaf.craftingexplode.events;

import ml.windleaf.craftingexplode.CraftingExplode;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

public class PlayerCloseTable implements Listener {
  private final CraftingExplode plugin;

  public PlayerCloseTable(CraftingExplode plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onInventoryClose(InventoryCloseEvent e) {
    if (e.getInventory().getType() == InventoryType.WORKBENCH) {
      HumanEntity human = e.getPlayer();
      String uuid = human.getUniqueId().toString();
      plugin.warningList.cancel(uuid);
      plugin.process.cancelTask(uuid);
    }
  }
}
