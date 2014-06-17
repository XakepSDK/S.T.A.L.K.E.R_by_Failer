package ru.stalker;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Timer {
	static int rnd = 10;
	static int i = 10;
	static int num;
	static int x = 0;

	public static void timer(final Plugin plugin) {
		i = 120;
		Boolean rande = plugin.getConfig().getBoolean("stalker.rand");
		if (rande == true) {
			Random rand = new Random();
			for (int i = 0; i < 40; ++i) {
				rnd = rand.nextInt(900) + 1;
			}

		} else {
			int time = plugin.getConfig().getInt("stalker.time");
			rnd = time;
		}
		plugin.getServer().getScheduler()
				.runTaskTimerAsynchronously(plugin, new Runnable() {
					@Override
					public void run() {
 
						rnd--;

						if (rnd == 0) {
							plugin.getServer().getScheduler().cancelAllTasks();
							vibros(plugin);
						}

					}

				}, 0L, 20);
	}

	public static int vibros(final Plugin plugin) {
		x = 0;
		final String world = plugin.getConfig().getString("stalker.world");
		num = plugin.getServer().getWorld(world).getPlayers().size();
		while (x < num) {
			Player player = plugin.getServer().getWorld(world).getPlayers()
					.get(x).getPlayer();
			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,
					180, 1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,
					12000, 1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,
					6000, 1));
			x++;
		}
		plugin.getServer().getScheduler()
				.runTaskTimerAsynchronously(plugin, new Runnable() {
					@Override
					public void run() {

						i--;

						if (i % 2 == 0) {
							plugin.getServer().getWorld(world).setTime(0);
						} else {
							plugin.getServer().getWorld(world).setTime(15000);
						}

						if (i == 0) {
							plugin.getServer().getScheduler().cancelAllTasks();
							plugin.getServer().getWorld(world)
									.setWeatherDuration(1);
							CheckLocation.checkLocation(plugin);
						}

					}

				}, 0L, 20);
		return 0;
	}
}
