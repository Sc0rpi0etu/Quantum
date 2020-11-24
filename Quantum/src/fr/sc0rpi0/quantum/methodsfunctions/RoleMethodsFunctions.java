package fr.sc0rpi0.quantum.methodsfunctions;

import java.util.Hashtable;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import fr.sc0rpi0.quantum.classes.Archer;
import fr.sc0rpi0.quantum.classes.PlayerRole;
import fr.sc0rpi0.quantum.classes.Tank;
import fr.sc0rpi0.quantum.classes.Warrior;

public class RoleMethodsFunctions {
	public static Hashtable<String, PlayerRole> playerAndRoleByName = new Hashtable<String, PlayerRole>();
	public static Hashtable<String, PlayerRole> playerAndRole = new Hashtable<String, PlayerRole>();
	public static Hashtable<String, Archer> playerAndArcher = new Hashtable<String, Archer>();
	public static Hashtable<String, Tank> playerAndTank = new Hashtable<String, Tank>();
	public static Hashtable<String, Warrior> playerAndWarrior = new Hashtable<String, Warrior>();
	
	public static PlayerRole getRole(Player player) {
		return playerAndRole.get(player.getUniqueId().toString());
	}
	
	public static PlayerRole getRole(String uuid) {
		return playerAndRole.get(uuid);
	}
	
	public static Archer getArcher(String uuid) {
		return playerAndArcher.get(uuid);
	}
	
	public static Warrior getWarrior(String uuid) {
		return playerAndWarrior.get(uuid);
	}
	
	public static void setWarrior(String uuid, Integer ultiTime) {
		playerAndWarrior.put(uuid, new Warrior(RoleMethodsFunctions.getRole(uuid), ultiTime));
	}
	
	public static Tank getTank(String uuid) {
		return playerAndTank.get(uuid);
	}
	
	public static void setTank(String uuid, Integer ultiTime) {
		playerAndTank.put(uuid, new Tank(RoleMethodsFunctions.getRole(uuid), ultiTime));
	}
	
	public static void removeArcher(String uuid) {
		playerAndArcher.remove(uuid);
	}
	
	public static void removeTank(String uuid) {
		playerAndTank.remove(uuid);
	}
	
	public static void removeWarrior(String uuid) {
		playerAndWarrior.remove(uuid);
	}
	
	public static PlayerRole getRoleByName(String name) {
		for (Entry<String, PlayerRole> entry : playerAndRoleByName.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(name)) {
				return entry.getValue();
			}
		}
		
		return null;
	}
	
	public static void setRole(String uuid, PlayerRole playerRole) {
		playerAndRole.put(uuid, playerRole);
	}
	
	public static void setArcher(String uuid, Integer ultiTime) {
		playerAndArcher.put(uuid, new Archer(RoleMethodsFunctions.getRole(uuid), ultiTime));
	}
	
	public static void setRole(Player player, PlayerRole playerRole) {
		playerAndRole.put(player.getUniqueId().toString(), playerRole);
		playerAndRoleByName.put(player.getName(), playerRole);
	}
}
