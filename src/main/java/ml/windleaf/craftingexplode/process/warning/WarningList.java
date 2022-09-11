package ml.windleaf.craftingexplode.process.warning;

import java.util.HashMap;

public class WarningList {
  private final HashMap<String, WarningThread> list = new HashMap<>();

  public void addWarningThread(String uuid, WarningThread thread) {
    this.list.put(uuid, thread);
  }

  public boolean isThreadRunning(String uuid) {
    return this.list.containsKey(uuid);
  }

  public WarningThread getWarningThread(String uuid) {
    return this.list.getOrDefault(uuid, null);
  }

  public void cancel(String uuid) {
    if (this.isThreadRunning(uuid)) {
      this.getWarningThread(uuid).interrupt();
    }
  }
}
