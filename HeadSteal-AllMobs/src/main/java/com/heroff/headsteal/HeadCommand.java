package com.heroff.headsteal;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadCommand implements CommandExecutor {
    private final HeadSteal plugin;

    public HeadCommand(HeadSteal plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        if (args.length < 2) {
            sender.sendMessage("/headsteal give <player> <MOB>");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("Player not found");
            return true;
        }
        String mob = args[1].toUpperCase();
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setDisplayName(mob + " Head");
        head.setItemMeta(meta);
        target.getInventory().addItem(head);
        sender.sendMessage("Gave " + mob + " head to " + target.getName());
        return true;
    }
}
