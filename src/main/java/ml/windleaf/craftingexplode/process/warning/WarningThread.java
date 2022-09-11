package ml.windleaf.craftingexplode.process.warning;

import ml.windleaf.craftingexplode.CraftingExplode;
import ml.windleaf.craftingexplode.utils.ChatUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;

import java.util.HashMap;
import java.util.Objects;

public class WarningThread extends Thread {
  private final CraftingExplode plugin;
  private final HumanEntity human;
  private Integer time;

  public WarningThread(CraftingExplode plugin, HumanEntity human, Integer time) {
    this.plugin = plugin;
    this.human = human;
    this.time = time;
  }

  private String map(String message, Location location) {
    final String[] copy = { message };
    HashMap<String, Object> map = new HashMap<>();
    map.put("{time}", this.time);
    map.put("{player}", this.human.getName());
    map.put("{x}", location.getX());
    map.put("{y}", location.getY());
    map.put("{z}", location.getZ());
    map.forEach((key, value) -> copy[0] = copy[0].replaceAll(key, value.toString()));
    return copy[0];
  }

  @Override
  public void run() {
    while (time != 0) {
      try {
        boolean soundWarning = plugin.getConfig().getBoolean("warning.tick-sound");
        String message = plugin.getConfig().getString("warning.message");

        Location location = human.getLocation();
        if (soundWarning) human.getWorld().playSound(location, Sound.BLOCK_DISPENSER_FAIL, 1.0F, 1.0F);
        if (!Objects.equals(message, "")) human.sendMessage(ChatUtil.translateColor(map(message, location)));

        Thread.sleep(1000L);
        time--;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    this.interrupt();
  }
}
