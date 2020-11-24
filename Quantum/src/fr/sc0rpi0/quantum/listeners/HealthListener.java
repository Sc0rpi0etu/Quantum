package fr.sc0rpi0.quantum.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import fr.sc0rpi0.quantum.classes.PlayerRole;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;
import fr.sc0rpi0.quantum.methodsfunctions.ScoreboardMethods;

public class HealthListener implements Listener {
	@EventHandler
	private void OnRegeneration(EntityRegainHealthEvent event) {
		Entity ent = event.getEntity();
		double health = event.getAmount();
		
		if (ent instanceof Player && RoleMethodsFunctions.getRole(((Player)ent)) != null) {
			Player player = (Player) ent;
			PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
			
			if (playerRole.getHealth() + health > getHealthMax(player)) {
				playerRole.setHealth(getHealthMax(player));
				
			} else {
				playerRole.setHealth(playerRole.getHealth() + health);
			}
			
			player.setHealth(playerRole.getHealth() * 20 / getHealthMax(player));
			event.setAmount(0);
			ScoreboardMethods.createScoreboard(player);
		}
	}
	
	public int getHealthMax(Player player) {
		PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
		
		double variante = 2;
		
		if (playerRole.getName().equalsIgnoreCase("Tank")) {
			
		} else if (playerRole.getName().equalsIgnoreCase("Cleric")) {
			variante = variante * 2 / 3;
			
		} else {
			variante = variante / 6;
		}
		
		return (int) (variante * 20 * playerRole.getVit());
	}
}
