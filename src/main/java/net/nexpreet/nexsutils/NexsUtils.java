package net.nexpreet.nexsutils;

import net.nexpreet.nexsutils.armor.BlazingBootsListener;
import net.nexpreet.nexsutils.misc.CoolExplosionListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NexsUtils extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new BlazingBootsListener(), this);
        Bukkit.getPluginManager().registerEvents(new CoolExplosionListener(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
