package me.hayat.myFirstPlugin;

import me.hayat.myFirstPlugin.commands.GiveLegendarySwordCommand;
import me.hayat.myFirstPlugin.listener.LegendarySwordListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class MyFirstPlugin extends JavaPlugin {
    private Map<UUID, Long> cooldowns = new HashMap<>();

    // クールダウンマップを他のクラスから取得できるようにするゲッター
    public Map<UUID, Long> getCooldowns() {
        return cooldowns;
    }
    @Override
    public void onEnable() {
        // Plugin startup login
        this.getCommand("givelegendarysword").setExecutor(new GiveLegendarySwordCommand());
        getServer().getPluginManager().registerEvents(new LegendarySwordListener(this), this);
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
