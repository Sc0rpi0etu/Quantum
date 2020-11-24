package fr.sc0rpi0.quantum.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.sc0rpi0.quantum.classes.Admin;
import fr.sc0rpi0.quantum.listeners.HealthListener;
import fr.sc0rpi0.quantum.listeners.JoinQuitListener;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;
import fr.sc0rpi0.quantum.methodsfunctions.ScoreboardMethods;
import net.md_5.bungee.api.ChatColor;

public class AdminCommand implements CommandExecutor {
	private JoinQuitListener joinQuitListener;
	private HealthListener healthListener;
	
	public AdminCommand(JoinQuitListener joinQuitListener, HealthListener healthListener) {
		this.joinQuitListener = joinQuitListener;
		this.healthListener = healthListener;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player && msg.equalsIgnoreCase("qadmin")) {
			Player player = (Player) sender;
			
			if (player.hasPermission("quantum.admin")) {
				if (args[0].equalsIgnoreCase("setrole")) {
					RoleMethodsFunctions.setRole(player, new Admin());
					player.sendMessage("Now you are an Admin");
					
				} else if (args[0].equalsIgnoreCase("heal")) {
					if (args.length > 1 && args[1] != null) {
						for (Player playerConnected : joinQuitListener.playerConnected) {
							if (playerConnected.getName().equalsIgnoreCase(args[1])) {
								RoleMethodsFunctions.getRole(playerConnected).setHealth(healthListener.getHealthMax(playerConnected));
								playerConnected.setHealth(20);
								player.sendMessage("Heal " + playerConnected + " !");
							}
						}
						
					} else {
						if (RoleMethodsFunctions.getRole(player).toString() != null) {
							RoleMethodsFunctions.getRole(player).setHealth(healthListener.getHealthMax(player));
						}
						player.setHealth(20);
						player.sendMessage("Heal !");
					}
					
				} else if (args[0].equalsIgnoreCase("help")) {
					
					player.sendMessage("Admin help command:\n/qadmin setrole (to set your role)\n/qadmin heal (to heal yourself)\n/qadmin heal [player] (to heal someone)\n/qadmin spawn (to create an egg of an entity)");
					
				} else {
					player.sendMessage(ChatColor.RED + "This is a wrong command !");
				}
				ScoreboardMethods.createScoreboard(player);
				
			} else {
				player.sendMessage(ChatColor.RED + "You don't have the permission to perform this command !");
			}
		}
		return false;
	}
}
