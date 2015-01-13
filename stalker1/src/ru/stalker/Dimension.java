package ru.stalker;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import ru.packetapi.WrapperPlayServerRespawn;

import com.comphenix.protocol.wrappers.EnumWrappers.Difficulty;
import com.comphenix.protocol.wrappers.EnumWrappers.NativeGameMode;

public class Dimension {
	public static void setDimension(Plugin plugin, String world, int dimension, WorldType type) {
		WrapperPlayServerRespawn wp = new WrapperPlayServerRespawn();
		wp.setLevelType(type);
		wp.setDimension(dimension);
		wp.setGameMode(NativeGameMode.SURVIVAL);
		wp.setDifficulty(Difficulty.NORMAL);

		for (Player p : Bukkit.getOnlinePlayers()) {

			Location location = p.getLocation();
			int viewDistance = plugin.getServer().getViewDistance();

			int xMin = location.getChunk().getX() - viewDistance;
			int xMax = location.getChunk().getX() + viewDistance;
			int zMin = location.getChunk().getZ() - viewDistance;
			int zMax = location.getChunk().getZ() + viewDistance;
			wp.setGameMode(getGamemode(p.getGameMode()));
			wp.sendPacket(p);
			for (int x = xMin; x < xMax; ++x) {
				for (int z = zMin; z < zMax; ++z) {
					Bukkit.getWorld(world).refreshChunk(x, z);

				}
			}
			p.updateInventory();

		}
	}

	public static void setDimensionOnPlayer(Plugin plugin2, String world2, int dimension2, WorldType type2, Player player2) {

		WrapperPlayServerRespawn wp1 = new WrapperPlayServerRespawn();
		wp1.setLevelType(type2);
		wp1.setDimension(dimension2);
		wp1.setGameMode(NativeGameMode.SURVIVAL);
		wp1.setDifficulty(Difficulty.NORMAL);

		wp1.setGameMode(getGamemode(player2.getGameMode()));
		Location location = player2.getLocation();
		int viewDistance = plugin2.getServer().getViewDistance();

		int xMin = location.getChunk().getX() - viewDistance;
		int xMax = location.getChunk().getX() + viewDistance;
		int zMin = location.getChunk().getZ() - viewDistance;
		int zMax = location.getChunk().getZ() + viewDistance;

		wp1.sendPacket(player2);
		for (int x = xMin; x < xMax; ++x) {
			for (int z = zMin; z < zMax; ++z) {
				Bukkit.getWorld(world2).refreshChunk(x, z);

			}
		}
		player2.updateInventory();

	}

	public static NativeGameMode getGamemode(GameMode gm) {

		NativeGameMode gamemode = NativeGameMode.SURVIVAL;
		if (gm.equals(GameMode.ADVENTURE)) {
			gamemode = NativeGameMode.ADVENTURE;
		}
		if (gm.equals(GameMode.CREATIVE)) {
			gamemode = NativeGameMode.CREATIVE;
		}
		if (gm.equals(GameMode.SURVIVAL)) {
			gamemode = NativeGameMode.SURVIVAL;
		}

		return gamemode;
	}
}
