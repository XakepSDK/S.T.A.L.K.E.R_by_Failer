package ru.stalker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import ru.stalker.Timer;

public class CheckLocation {

	static int num;
	static int x = 0;
	static int cx;
	static int cz;

	public static void checkLocation(Plugin plugin) {
		x = 0;
		String world = plugin.getConfig().getString("stalker.world");
		plugin.getServer().getScheduler().cancelAllTasks();

		for (Player p : Bukkit.getOnlinePlayers()) {

			cx = p.getLocation().getBlockX();
			cz = p.getLocation().getBlockZ();

			if (plugin.getServer().getWorld(world).getBlockAt(cx, 0, cz).getType() != Material.REDSTONE_BLOCK) {

				p.damage(999999.0D);

			}
		}

		Timer.timer(plugin);
	}
}
