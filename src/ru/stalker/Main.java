package ru.stalker;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	public static final Logger _log = Logger.getLogger("Minecraft");

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		System.out.println("S.T.A.L.K.E.R by Failer activated!");
		Bukkit.getPluginManager().registerEvents(this, this);
		Timer.timer(this);
	} 

}
