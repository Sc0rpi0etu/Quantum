package fr.sc0rpi0.quantum.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;
import fr.sc0rpi0.quantum.methodsfunctions.ScoreboardMethods;

public class ExperienceListener implements Listener {
	@EventHandler
	private void OnChangeExp(PlayerExpChangeEvent e) {
		if (RoleMethodsFunctions.getRole(e.getPlayer()) != null) {
			Player player = e.getPlayer();
			int exp = e.getAmount();
			e.setAmount(0);
			int totalExpPlayer = RoleMethodsFunctions.getRole(player).getExperience() + exp;
			int expBar = RoleMethodsFunctions.getRole(player).getExperienceBar();
			
			while(totalExpPlayer >= expBar) {
				totalExpPlayer = totalExpPlayer - expBar;
				RoleMethodsFunctions.getRole(player).setStatsPoint(RoleMethodsFunctions.getRole(player).getStatsPoint() + 2);
				RoleMethodsFunctions.getRole(player).setLevel(RoleMethodsFunctions.getRole(player).getLevel() + 1);
				expBar = RoleMethodsFunctions.getRole(player).getExperienceBar();
			}
			RoleMethodsFunctions.getRole(player).setExperience(totalExpPlayer);
			
			player.setExp((float) 0.3);
			float calculExp = (float) (((double)totalExpPlayer) / ((double)expBar));
			player.setExp(calculExp);
			player.setLevel(RoleMethodsFunctions.getRole(player).getLevel());
			ScoreboardMethods.createScoreboard(player);
		}
	}
}
