package ml.windleaf.craftingexplode.commands;

import ml.windleaf.craftingexplode.CraftingExplode;
import ml.windleaf.craftingexplode.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CraftingExplodeCommand implements CommandExecutor, TabCompleter {
  private final CraftingExplode plugin;
  private CommandSender sender;

  public CraftingExplodeCommand(CraftingExplode plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    this.sender = sender;
    String first = Arrays.stream(args).findFirst().orElse("");
    int size = args.length;
    if (size == 0) help();
    else if (size == 1) {
      if (first.equals("help")) help();
      else error();
    }
    else if (size == 3) {
      if (first.equals("config")) {
        HashMap<String, Function<String, Object>> map = new HashMap<>();
        map.put("process.delay", Long::valueOf);
        map.put("process.power", Integer::valueOf);
        map.put("warning.tick-sound", Boolean::valueOf);
        map.put("warning.message", String::valueOf);

        String path = args[1];
        String value = args[2];
        FileConfiguration cf = plugin.getConfig();

        if (map.containsKey(path)) {
          cf.set(path, map.get(path).apply(value));
          plugin.saveConfig();
        } else sender.sendMessage(ChatUtil.translateColor("&c没有这个路径!"));
      }
      else error();
    }
    else error();
    return false;
  }

  private void error() {
    sender.sendMessage(ChatUtil.translateColor("&c这是一个错误的命令, 请使用 &6/ce help &c来获取帮助!"));
  }

  private void help() {
    Arrays.asList(
            "&a--- [CraftingExplode Help] ---",
            "&2/ce [help] &f- &6查看此帮助",
            "&2/ce config <path> <value> &f- &6配置 config.yml"
    ).forEach((s) -> sender.sendMessage(ChatUtil.translateColor(s)));
  }

  @Nullable
  @Override
  public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    int length = args.length;
    if (length <= 1) {
      String[] subCommands = {"help", "config"};
      return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    } else if (length <= 3) {
      if (args[0].equals("config")) {
        return Arrays.stream(new String[]{"<path>", "<value>"}).filter(s -> s.startsWith(args[length - 1])).collect(Collectors.toList());
      } else {
        return null;
      }
    } else {
      return null;
    }
  }
}
