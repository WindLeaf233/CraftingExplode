package ml.windleaf.craftingexplode.process.warning;

import ml.windleaf.craftingexplode.CraftingExplode;
import ml.windleaf.craftingexplode.utils.ChatUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

public class WarningThread extends Thread {
  private final CraftingExplode plugin;
  private final HumanEntity human;
  private Long time;
  private final Long interval;

  public WarningThread(CraftingExplode plugin, HumanEntity human, Long time, Long interval) {
    this.plugin = plugin;
    this.human = human;
    this.time = time;
    this.interval = interval;
  }

  private String map(String message, @NotNull Location location) {
    return message
            .replace("{time}", this.time.toString())
            .replace("{player}", this.human.getName())
            .replace("{x}", String.valueOf(location.getX()))
            .replace("{y}", String.valueOf(location.getY()))
            .replace("{z}", String.valueOf(location.getZ()));
  }

  @Override
  public void run() {
    while (time > 0) {
      try {
        boolean soundWarning = plugin.getConfig().getBoolean("warning.tick-sound");
        String message = plugin.getConfig().getString("warning.message");

        Location location = human.getLocation();
        if (soundWarning) human.getWorld().playSound(location, Sound.BLOCK_DISPENSER_FAIL, 1.0F, 1.0F);
        if (!Objects.equals(message, "")) human.sendMessage(ChatUtil.translateColor(map(message, location)));

        Thread.sleep(this.interval);
        time = time - this.interval;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    this.interrupt();
  }
}
