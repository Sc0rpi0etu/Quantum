package fr.sc0rpi0.quantum.run;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.sc0rpi0.quantum.methodsfunctions.ScoreboardMethods;
import net.md_5.bungee.api.ChatColor;

public class ShowHealthRun extends BukkitRunnable {

	@Override
	public void run() {
		for (LivingEntity entity : Bukkit.getWorld("world").getLivingEntities()) {
			entity.setCustomName((int) (entity.getHealth()) + "" + ChatColor.RED + " ♥");
			entity.setCustomNameVisible(true);
			
			if (entity instanceof Player) {
				Player player = (Player) entity;
				player.setScoreboard(ScoreboardMethods.setScoreboardBelowName(player));
			}
		}
	}

}
