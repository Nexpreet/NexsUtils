package net.nexpreet.nexsutils.armor;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;

public class BlazingBootsListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItem(EquipmentSlot.FEET) != null && player.getInventory().getItem(EquipmentSlot.FEET).getType() == Material.LEATHER_BOOTS && player.getInventory().getItem(EquipmentSlot.FEET).getItemMeta() != null) {
            if (player.getInventory().getItem(EquipmentSlot.FEET).getItemMeta().getDisplayName().equalsIgnoreCase("§fBlazing Boots")) {

                World world = player.getWorld();
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(207, 14, 0), 1.0F);
                Location location = player.getEyeLocation().subtract(0.0D, 1.1D, 0.0D);
                world.spawnParticle(Particle.REDSTONE, location, 1, dustOptions);
            }
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            if (((Player) entity).getInventory().getItem(EquipmentSlot.FEET) != null && ((Player) entity).getInventory().getItem(EquipmentSlot.FEET).getType() == Material.LEATHER_BOOTS) {
                if (((Player) entity).getInventory().getItem(EquipmentSlot.FEET).getItemMeta() != null && ((Player) entity).getInventory().getItem(EquipmentSlot.FEET).getItemMeta().getDisplayName().equalsIgnoreCase("Blazing Boots")) {

                    if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
