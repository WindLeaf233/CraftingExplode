package ml.windleaf.craftingexplode.events;

import ml.windleaf.craftingexplode.CraftingExplode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlayerBreakTable implements Listener {
  private final CraftingExplode plugin;

  public PlayerBreakTable(CraftingExplode plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onBlockBreak(@NotNull BlockBreakEvent e) {
    Block block = e.getBlock();
    if (block.getType() == Material.CRAFTING_TABLE) {
      Location location = block.getLocation();
      String uuid = plugin.process.cancelTask(location);
      if (!Objects.equals(uuid, "")) {
        plugin.warningList.cancel(uuid);
      }
    }
  }
}
