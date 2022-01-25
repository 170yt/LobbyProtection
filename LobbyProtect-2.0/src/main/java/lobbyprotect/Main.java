package lobbyprotect;

import lobbyprotect.commands.BuildCommand;
import lobbyprotect.commands.DmgCommand;
import lobbyprotect.listeners.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onLoad() {
        instance = this;

        saveConfig();
        if (!getConfig().contains("disablePlayerDamage")) { getConfig().set("disablePlayerDamage", true); }
        if (!getConfig().contains("disableInventoryClickEvent")) { getConfig().set("disableInventoryClickEvent", true); }
        if (!getConfig().contains("disableBlockBreak")) { getConfig().set("disableBlockBreak", true); }
        if (!getConfig().contains("disableBlockPlace")) { getConfig().set("disableBlockPlace", true); }
        if (!getConfig().contains("disablePlayerDropItem")) { getConfig().set("disablePlayerDropItem", true); }
        if (!getConfig().contains("disablePlayerPickupItem")) { getConfig().set("disablePlayerPickupItem", true); }
        if (!getConfig().contains("disableFoodLevelChange")) { getConfig().set("disableFoodLevelChange", true); }
        if (!getConfig().contains("disablePlayerJoinMessage")) { getConfig().set("disablePlayerJoinMessage", true); }
        if (!getConfig().contains("disablePlayerQuitMessage")) { getConfig().set("disablePlayerQuitMessage", true); }
        if (!getConfig().contains("disablePlayerKickMessage")) { getConfig().set("disablePlayerKickMessage", true); }
        if (!getConfig().contains("clearInventoryOnJoin")) { getConfig().set("clearInventoryOnJoin", true); }
        if (!getConfig().contains("clearArmorOnJoin")) { getConfig().set("clearArmorOnJoin", true); }
        if (!getConfig().contains("xpLevelResetOnJoin")) { getConfig().set("xpLevelResetOnJoin", true); }
        if (!getConfig().contains("setWeatherToClearOnStart")) { getConfig().set("setWeatherToClearOnStart", true); }
        if (!getConfig().contains("disableDaylightCycle")) { getConfig().set("disableDaylightCycle", true); }
        if (!getConfig().contains("setTimeOnStart")) { getConfig().set("setTimeOnStart", true); }
        if (!getConfig().contains("time")) { getConfig().set("time", 12000); }
        if (!getConfig().contains("keepInventory")) { getConfig().set("keepInventory", true); }

        saveConfig();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new Listeners(), this);

        getCommand("build").setExecutor(new BuildCommand());
        getCommand("dmg").setExecutor(new DmgCommand());
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "<< Build plugin loaded >>");


        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
            if (getConfig().getBoolean("setWeatherToClearOnStart")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather clear 1000000");
            if (getConfig().getBoolean("disableDaylightCycle")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doDaylightCycle false");
            if (getConfig().getBoolean("setTimeOnStart")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "time set " + getConfig().getInt("time"));
            if (getConfig().getBoolean("keepInventory")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule keepInventory true");
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }

}
