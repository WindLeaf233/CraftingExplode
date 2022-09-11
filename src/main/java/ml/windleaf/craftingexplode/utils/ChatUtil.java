package ml.windleaf.craftingexplode.utils;

import org.bukkit.ChatColor;

public class ChatUtil {
  public static String translateColor(String string) {
    return ChatColor.translateAlternateColorCodes('&', string);
  }
}
