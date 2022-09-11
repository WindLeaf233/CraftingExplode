package ml.windleaf.craftingexplode;

import ml.windleaf.craftingexplode.events.PlayerCloseTable;
import ml.windleaf.craftingexplode.events.PlayerOpenTable;
import ml.windleaf.craftingexplode.process.PlayerProcess;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CraftingExplode extends JavaPlugin {
  public PlayerProcess process;

  @Override
  public void onEnable() {
    this.process = new PlayerProcess(this);

    PluginManager pluginManager = this.getServer().getPluginManager();
    pluginManager.registerEvents(new PlayerOpenTable(this), this);
    pluginManager.registerEvents(new PlayerCloseTable(this), this);
  }
}
