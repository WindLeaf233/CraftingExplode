package ml.windleaf.craftingexplode;

import ml.windleaf.craftingexplode.commands.CraftingExplodeCommand;
import ml.windleaf.craftingexplode.events.PlayerCloseTable;
import ml.windleaf.craftingexplode.events.PlayerOpenTable;
import ml.windleaf.craftingexplode.process.PlayerProcess;
import ml.windleaf.craftingexplode.process.warning.WarningList;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CraftingExplode extends JavaPlugin {
  public PlayerProcess process;
  public WarningList warningList;

  @Override
  public void onEnable() {
    this.saveDefaultConfig();

    this.process = new PlayerProcess(this);
    this.warningList = new WarningList();

    Server server = this.getServer();
    PluginManager pluginManager = server.getPluginManager();

    pluginManager.registerEvents(new PlayerOpenTable(this), this);
    pluginManager.registerEvents(new PlayerCloseTable(this), this);

    Objects.requireNonNull(server.getPluginCommand("craftingexplode")).setExecutor(new CraftingExplodeCommand(this));
  }
}
