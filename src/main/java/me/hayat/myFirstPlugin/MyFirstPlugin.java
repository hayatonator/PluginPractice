package me.hayat.myFirstPlugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyFirstPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup login
        getLogger().info("------------------------------------");
        getLogger().info(" MyFirstPlugin が有効化されました！ ");
        getLogger().info("------------------------------------");

        Bukkit.getPluginManager().registerEvents(new HelloWorld(), this);
        }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("------------------------------------");
        getLogger().info(" MyFirstPlugin が無効化されました。 ");
        getLogger().info("------------------------------------");
    }
}
