package net.nexpreet.nexsutils.misc;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.Random;

public class CoolExplosionListener implements Listener {

    private static ArrayList<TNTPrimed> tntList = new ArrayList<>();

    @EventHandler
    public void onInteraction(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (!event.getPlayer().hasPermission("nexsutils.weapons")) return;
        if (event.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (event.getPlayer().getInventory().getItemInMainHand().getType() != Material.TNT) return;
        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) return;
        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§fCool Explosion") && action == Action.RIGHT_CLICK_AIR) {

            World world = player.getWorld();
            TNTPrimed tnt = world.spawn(player.getLocation(), TNTPrimed.class);
            tnt.setFuseTicks(100);
            tntList.add(tnt);
            tnt.setYield(tnt.getYield() * 2.0F);
        }
    }

    @EventHandler
    public void onTntExplosion(EntityExplodeEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof TNTPrimed && tntList.contains(entity)) {

            for (Block block : event.blockList()) {
                Location loc = block.getLocation();
                World world = entity.getWorld();
                BlockData blockData = block.getBlockData();
                double min = -1.0D;
                double max = 1.0D;
                Random randomX = new Random();
                double x = min + (max - min) * randomX.nextDouble();
                Random randomY = new Random();
                double y = min + (max - min) * randomY.nextDouble();
                Random randomZ = new Random();
                double z = min + (max - min) * randomZ.nextDouble();
                FallingBlock fallingBlock = world.spawnFallingBlock(loc, blockData);
                fallingBlock.setVelocity(fallingBlock.getVelocity().setY(Math.abs(y)).setX(x).setZ(z));
                fallingBlock.setDropItem(false);

            }
            tntList.remove(entity);
        }
    }


}
