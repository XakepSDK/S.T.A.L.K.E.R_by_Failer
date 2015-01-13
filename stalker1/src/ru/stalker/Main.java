package ru.stalker;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	String world;

	public void onEnable() {
		this.saveDefaultConfig();
		System.out.println("S.T.A.L.K.E.R by Failer activated!");
		Bukkit.getPluginManager().registerEvents(this, this);
		Timer.timer(this);

	}

	@EventHandler
	public void onRespawn(final PlayerRespawnEvent event) {
		final Player player;
		world = this.getConfig().getString("stalker.world");
		player = event.getPlayer();
		this.getServer().getScheduler().runTask(this, new Runnable() {

			@Override
			public void run() {
				Dimension.setDimensionOnPlayer(Main.this, world, -1, Bukkit.getWorld(world).getWorldType(), player);
			}

		});

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player;
		world = this.getConfig().getString("stalker.world");
		player = event.getPlayer();
		if (Timer.work == true) {
			Dimension.setDimensionOnPlayer(this, world, -1, Bukkit.getWorld(world).getWorldType(), player);
		}

	}
}
