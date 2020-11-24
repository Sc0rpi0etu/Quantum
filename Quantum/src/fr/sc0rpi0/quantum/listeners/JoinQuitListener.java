package fr.sc0rpi0.quantum.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.sc0rpi0.quantum.Main;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;
import fr.sc0rpi0.quantum.methodsfunctions.ScoreboardMethods;

public class JoinQuitListener implements Listener {
	private Main main;
	public JoinQuitListener(Main main) {
		this.main = main;
	}
	
	public List<Player> playerConnected = new ArrayList<Player>();
	
	@EventHandler
	private void OnJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		main.permissionsSetter(player);
		
		if (RoleMethodsFunctions.getRole(player) == null) {
			player.sendMessage("Choose your role:\n(/q setrole archer)\nArcher\nCleric\nAssassin\nTank\nWarrior\nWizard");
		} else {
			ScoreboardMethods.createScoreboard(player);
		}
		playerConnected.add(player);
	}

	@EventHandler
	private void OnLeave(PlayerQuitEvent event) {
		Bukkit.broadcastMessage("Player leave");
		int index = playerConnected.indexOf(event.getPlayer());
		playerConnected.remove(index);
	}
}
