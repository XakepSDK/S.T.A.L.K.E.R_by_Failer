package ru.stalker;

import org.bukkit.plugin.Plugin;

public class Runnable_Bleeding {
	static int i = 0;
	static int dmg;

	public static void start(Plugin plugin) {
		dmg = plugin.getConfig().getInt("stalker.dmg_bleeding");
		plugin.getServer().getScheduler()
				.runTaskTimerAsynchronously(plugin, new Runnable() {
					@Override
					public void run() {
						i=0;
						while (i != Main.Players.size()) {
							if (Main.Players.get(i).use == false) {
								Main.Players.remove(i);
							}
							else{
								
							
							Main.Players.get(i).player.damage(dmg);
							i++;
							}
						}
					}

				}, 0L, 20);
	}

}
