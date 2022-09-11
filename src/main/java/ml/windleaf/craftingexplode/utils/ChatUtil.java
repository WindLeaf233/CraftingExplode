package ml.windleaf.craftingexplode.utils;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ChatUtil {
  @Contract("_ -> new")
  public static @NotNull String translateColor(String string) {
    return ChatColor.translateAlternateColorCodes('&', string);
  }
}
