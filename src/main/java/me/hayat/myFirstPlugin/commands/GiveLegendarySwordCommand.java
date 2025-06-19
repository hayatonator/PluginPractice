package me.hayat.myFirstPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;

public class GiveLegendarySwordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // コマンド実行者がプレイヤーであるか確認
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cこのコマンドはプレイヤーのみが実行できます。");
            return true; // コマンド処理を終了
        }

        Player player = (Player) sender;

        // コマンド名が正しいか確認
        // plugin.ymlで定義したコマンド名 ('givelegendarysword') と一致するかを確認します。
        if (command.getName().equalsIgnoreCase("givelegendarysword")) {

            // 1. 伝説の剣を作成
            ItemStack legendarySword = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta meta = legendarySword.getItemMeta();

            if (meta != null) {
                meta.setDisplayName("§6§l伝説の剣"); // アイテム名
                meta.setLore(Arrays.asList("§7この剣は古の力を持つ。", "§7触れるもの全てを切り裂く。")); // 説明文
                // 必要であればCustomModelDataなども設定できます
                // meta.setCustomModelData(12345);
                legendarySword.setItemMeta(meta);
            }

            // 2. アイテムをプレイヤーのインベントリに追加
            player.getInventory().addItem(legendarySword);
            player.sendMessage("§aカスタムアイテム「伝説の剣」を手に入れました！");

            return true; // コマンドが正常に処理されたことを示す
        }

        return false; // コマンドが認識されなかったことを示す（この場合は到達しないはず）
    }
}