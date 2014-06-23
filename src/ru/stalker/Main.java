package ru.stalker;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener {
	public static final Logger _log = Logger.getLogger("Minecraft");
	public static ArrayList<Bleeding> Players = new ArrayList<Bleeding>();
	Random rand = new Random();
	Bleeding tempPlayer = new Bleeding();

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		System.out.println("S.T.A.L.K.E.R by Failer activated!");
		Bukkit.getPluginManager().registerEvents(this, this);
		Timer.timer(this);
		Runnable_Bleeding.start(this);
	}

	// отравление когда зомби бьет игрока
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent event) {

		if (event.getEntity() instanceof Player) {
			if (event.getDamager().getType().equals(EntityType.ZOMBIE)) {
				Player player = (Player) event.getEntity();
				Random r = new Random();
				boolean ra = r.nextInt(500) < 50;
				if (ra == true) {
					if (this.getConfig().getBoolean("stalker.main_poison") == true) {
						int poison = this.getConfig().getInt("stalker.poison");
						player.addPotionEffect(new PotionEffect(
								PotionEffectType.POISON, poison, 1));
					}
					if (this.getConfig().getBoolean("stalker.bleeding") == true) {
						if (Players.size() != 0) {

							int k = 0;
							while (k != Players.size()) {
								if (Players.get(k).player.getName().equals(
										player.getName())) {

								} else {

									if (Players.get(k).player == null)
										tempPlayer.player = player;
									tempPlayer.use = true;
									Players.add(tempPlayer);
								}
								k++;
							}
						} else {
							tempPlayer.player = player;
							tempPlayer.use = true;
							Players.add(tempPlayer);
						}
					}

				}

			}
		}
	}

	@EventHandler
	public void onInterract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if (p.getItemInHand() == null) {
			return;
		}
		if (p.getItemInHand().getType() == Material.PAPER) {
			if ((event.getAction() == Action.RIGHT_CLICK_BLOCK)
					|| (event.getAction() == Action.RIGHT_CLICK_AIR)) {
				int i = 0;
				while (i < Players.size()) {
					if (Players.get(i).player.getName().equals(p.getName())) {
						Players.get(i).use = false;

						int z = p.getItemInHand().getAmount();

						z--;
						p.getItemInHand().setAmount(z);
					}
					i++;
				}

			}
		}
	}
}
