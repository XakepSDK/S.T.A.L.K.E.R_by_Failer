package ru.stalker;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CheckLocation {
	static int num;
	static int x = 0;
	static int cx;
	static int cz;

	public static void checkLocation(Plugin plugin) {
		x = 0;
		String world = plugin.getConfig().getString("stalker.world");
		num = plugin.getServer().getWorld(world).getPlayers().size();
		plugin.getServer().getScheduler().cancelAllTasks();
		while (x < num) {
			Player player = plugin.getServer().getWorld(world).getPlayers()
					.get(x).getPlayer();
			cx = player.getLocation().getBlockX();
			cz = player.getLocation().getBlockZ();
			if (plugin.getServer().getWorld(world).getBlockAt(cx, 0, cz)
					.getType() != Material.REDSTONE_BLOCK) { 
				player.damage(999999);
			}

			x++;
		}
		Timer.timer(plugin);
	}
}
