package ml.windleaf.craftingexplode;

import ml.windleaf.craftingexplode.events.PlayerCloseTable;
import ml.windleaf.craftingexplode.events.PlayerOpenTable;
import ml.windleaf.craftingexplode.process.PlayerProcess;
import ml.windleaf.craftingexplode.process.warning.WarningList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CraftingExplode extends JavaPlugin {
  public PlayerProcess process;
  public WarningList warningList;

  @Override
  public void onEnable() {
    this.process = new PlayerProcess(this);
    this.warningList = new WarningList();

    PluginManager pluginManager = this.getServer().getPluginManager();
    pluginManager.registerEvents(new PlayerOpenTable(this), this);
    pluginManager.registerEvents(new PlayerCloseTable(this), this);
  }
}
