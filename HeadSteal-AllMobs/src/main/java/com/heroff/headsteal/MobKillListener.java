package com.heroff.headsteal;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Random;

public class MobKillListener implements Listener {
    private final HeadSteal plugin;
    private final Random random = new Random();

    public MobKillListener(HeadSteal plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) return;
        Player killer = event.getEntity().getKiller();
        EntityType type = event.getEntityType();
        double chance = plugin.getConfig().getDouble("drops." + type.name(), 0.05);
        if (random.nextDouble() <= chance) {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setDisplayName(type.name() + " Head");
            head.setItemMeta(meta);
            event.getDrops().add(head);
        }
    }
}
