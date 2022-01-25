package lobbyprotect.listeners;

import lobbyprotect.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Listeners implements Listener {
//---------------------------------------------------------------------------------------------------------------------
    private static Map<UUID, Boolean> map = new HashMap<>();
    private static boolean dmg = false;
//---------------------------------------------------------------------------------------------------------------------
    public void onCommand(Player player) {
        if (map.get(player.getUniqueId())) {
            map.put(player.getUniqueId(), false);
            player.setGameMode(GameMode.SURVIVAL);
        } else {
            map.put(player.getUniqueId(), true);
            player.setGameMode(GameMode.CREATIVE);
        }
    }
//---------------------------------------------------------------------------------------------------------------------
    public boolean onDmgCommand() {
        if (dmg) dmg = false;
        else dmg = true;
        return dmg;
    }
//---------------------------------------------------------------------------------------------------------------------
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        map.put(event.getPlayer().getUniqueId(), false);
        event.getPlayer().setGameMode(GameMode.SURVIVAL);
        event.getPlayer().setHealth(event.getPlayer().getMaxHealth());
        event.getPlayer().setFoodLevel(20);
        if (Main.getInstance().getConfig().getBoolean("disablePlayerJoinMessage")) event.setJoinMessage(null);
        if (Main.getInstance().getConfig().getBoolean("clearInventoryOnJoin")) event.getPlayer().getInventory().clear();
        if (Main.getInstance().getConfig().getBoolean("clearArmorOnJoin")) {
            event.getPlayer().getInventory().setHelmet(null);
            event.getPlayer().getInventory().setChestplate(null);
            event.getPlayer().getInventory().setLeggings(null);
            event.getPlayer().getInventory().setBoots(null);
        }
        if (Main.getInstance().getConfig().getBoolean("xpLevelResetOnJoin")) {
            event.getPlayer().setExp(0);
            event.getPlayer().setLevel(0);
        }
    }
//---------------------------------------------------------------------------------------------------------------------
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!Main.getInstance().getConfig().getBoolean("disableBlockPlace")) return;
        map.putIfAbsent(event.getPlayer().getUniqueId(), false);
        if (!map.get(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!Main.getInstance().getConfig().getBoolean("disableBlockBreak")) return;
        map.putIfAbsent(event.getPlayer().getUniqueId(), false);
        if (!map.get(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!Main.getInstance().getConfig().getBoolean("disablePlayerDamage")) return;
        if (!dmg) event.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!Main.getInstance().getConfig().getBoolean("disableFoodLevelChange")) return;
        map.putIfAbsent((event.getEntity().getUniqueId()), false);
        if (!map.get((event.getEntity().getUniqueId()))) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        if (!Main.getInstance().getConfig().getBoolean("disablePlayerPickupItem")) return;
        map.putIfAbsent(event.getPlayer().getUniqueId(), false);
        if (!map.get(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (!Main.getInstance().getConfig().getBoolean("disablePlayerDropItem")) return;
        map.putIfAbsent(event.getPlayer().getUniqueId(), false);
        if (!map.get(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!Main.getInstance().getConfig().getBoolean("disableInventoryClickEvent")) return;
        map.putIfAbsent(event.getWhoClicked().getUniqueId(), false);
        if (!map.get(event.getWhoClicked().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (Main.getInstance().getConfig().getBoolean("disablePlayerQuitMessage")) event.setQuitMessage(null);
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        if (Main.getInstance().getConfig().getBoolean("disablePlayerKickMessage")) event.setLeaveMessage(null);
    }
}
