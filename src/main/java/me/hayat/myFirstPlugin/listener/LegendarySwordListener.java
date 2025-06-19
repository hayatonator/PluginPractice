package me.hayat.myFirstPlugin.listener;

import me.hayat.myFirstPlugin.MyFirstPlugin;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.Map;
import java.util.UUID;

public class LegendarySwordListener implements Listener {

    private MyFirstPlugin plugin; // プラグインのインスタンスを保持するフィールド

    // コンストラクタでプラグインのインスタンスを受け取る
    public LegendarySwordListener(MyFirstPlugin plugin) {
        this.plugin = plugin;
    }

    // クールダウンの時間をミリ秒で定義 (例: 5秒 = 5000ミリ秒)
    private static final long COOLDOWN_TIME_MILLIS = 5 * 1000;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = event.getItem();

        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (itemInHand == null || !itemInHand.hasItemMeta()) {
            return;
        }

        ItemMeta meta = itemInHand.getItemMeta();

        if (meta == null || !meta.hasDisplayName() || !meta.getDisplayName().equals("§6§l伝説の剣")) {
            return;
        }

        event.setCancelled(true); // 余計な挙動（ブロック配置など）を防ぐ

        UUID playerUUID = player.getUniqueId();
        Map<UUID, Long> cooldowns = plugin.getCooldowns(); // メインプラグインからクールダウンマップを取得

        long currentTime = System.currentTimeMillis();

        // クールダウン中かどうかのチェック
        if (cooldowns.containsKey(playerUUID) && cooldowns.get(playerUUID) > currentTime) {
            long timeLeft = (cooldowns.get(playerUUID) - currentTime) / 1000 + 1; // 残り秒数を計算 (+1で切り上げ表示)
            player.sendMessage("§c伝説の剣はクールダウン中です！残り " + timeLeft + " 秒...");
            return; // クールダウン中のため処理を中断
        }

        // --- ファイヤーボールを飛ばす処理 ---
        Vector direction = player.getEyeLocation().getDirection();

        // SmallFireball を使用する場合
        SmallFireball fireball = player.getWorld().spawn(player.getEyeLocation().add(direction.multiply(1.5)), SmallFireball.class);
        fireball.setYield(0.5f); // 爆発の威力
        fireball.setIsIncendiary(true); // 着弾地点を燃やすか
        fireball.setDirection(direction.multiply(2.0)); // 速度
        fireball.setShooter(player);

        player.sendMessage("§e伝説の剣からファイヤーボールを放ちました！");

        // クールダウンを設定 (次の使用可能時刻をマップに記録)
        cooldowns.put(playerUUID, currentTime + COOLDOWN_TIME_MILLIS);
    }
}
