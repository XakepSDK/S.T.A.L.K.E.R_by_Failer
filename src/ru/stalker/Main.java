package ru.stalker;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener {
	public static final Logger _log = Logger.getLogger("Minecraft");

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		System.out.println("S.T.A.L.K.E.R by Failer activated!");
		Bukkit.getPluginManager().registerEvents(this, this);
		Timer.timer(this);
	} 
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent event)
	{
		if(event.getEntity() instanceof Player)
		{
			if(event.getDamager().getType().equals(EntityType.ZOMBIE))
			{
				Player player = (Player) event.getEntity();
				int poison = this.getConfig().getInt("stalker.poison");
				player.addPotionEffect(new PotionEffect(PotionEffectType.POISON,poison, 1));
			}
		}
	}
}
