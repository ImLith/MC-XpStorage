package com.lith.xpstorage.events;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import com.lith.lithcore.utils.PlayerUtil;

public class ShiftClickEvent implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player == null || !player.isSneaking() || event.getAction() != Action.RIGHT_CLICK_AIR)
            return;

        int playerCurrentExp = PlayerUtil.getCurrentExp(player);
        if (playerCurrentExp <= 0)
            return;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || !item.containsEnchantment(Enchantment.MENDING))
            return;

        Material itemType = item.getType();
        if (itemType == null || itemType == Material.AIR)
            return;

        Damageable itemDamageable = (Damageable) item.getItemMeta();
        if (itemDamageable == null || !itemDamageable.hasDamage())
            return;

        int itemDamage = itemDamageable.getDamage();
        int expToConsume = Math.min((int) Math.ceil((double) itemDamage / 2), playerCurrentExp);
        if (expToConsume <= 0)
            return;

        itemDamageable.setDamage(Math.min(Math.max(0, itemDamage - 2 * expToConsume), itemType.getMaxDurability()));
        item.setItemMeta((ItemMeta) itemDamageable);
        player.giveExp(-expToConsume);
    }
}
