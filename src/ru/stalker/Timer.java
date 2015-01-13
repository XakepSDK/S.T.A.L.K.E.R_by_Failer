package ru.stalker;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Timer {
	// переменные
	public static Plugin plg;
	static int rnd = 10;
	static int i = 10;
	static int num;
	static int x = 0;
	static int time;
	static String world = "";
	public static boolean work;

	public static void timer(final Plugin plugin) {

		plg = plugin;
		i = 120;
		Boolean rande = Boolean.valueOf(plugin.getConfig().getBoolean("stalker.rand"));

		if (rande.booleanValue()) {
			Random time = new Random();

			for (int i = 0; i < 40; ++i) {
				rnd = time.nextInt(900) + 1;
			}

		} else {
			time = plugin.getConfig().getInt("stalker.time");
			rnd = time;
		}

		plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
			int time_msg = plugin.getConfig().getInt("stalker.time_msg");

			@Override
			public void run() {
				rnd--;
				if (rnd == time_msg) {
					String msg = plugin.getConfig().getString("stalker.msg");
					Bukkit.broadcastMessage(msg);

				}
				if (rnd == 0) {
					plugin.getServer().getScheduler().cancelAllTasks();
					vibros(plugin);
				}
			}
		}, 0L, 20L);
	}

	public static void vibros(final Plugin plugin) {
		work = true;
		x = 0;
		i = plugin.getConfig().getInt("stalker.time_stl");
		world = plugin.getConfig().getString("stalker.world");

		Player player;
		for (num = plugin.getServer().getWorld(world).getPlayers().size(); x < num; ++x) {
			player = ((Player) plugin.getServer().getWorld(world).getPlayers().get(x)).getPlayer();
			player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, i, 1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, i, 1));
		}

		Dimension.setDimension(plugin, world, -1, Bukkit.getWorld(world).getWorldType());
		plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {

			@Override
			public void run() {

				i--;
				if (i % 2 == 0) {

					Location location = null;
					for (Player p : Bukkit.getOnlinePlayers()) {

						location = p.getLocation();
						Bukkit.getWorld("world").strikeLightningEffect(location);
					}

				}
				if (i == 0) {
					plugin.getServer().getScheduler().cancelAllTasks();
					plugin.getServer().getWorld(world).setWeatherDuration(1);
					Dimension.setDimension(plugin, world, 0, Bukkit.getWorld(world).getWorldType());
					CheckLocation.checkLocation(plugin);
					work = false;
				}
			}
		}, 0L, 20L);
	}

}
