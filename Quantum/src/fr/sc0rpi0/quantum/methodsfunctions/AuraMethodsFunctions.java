package fr.sc0rpi0.quantum.methodsfunctions;

import java.util.Hashtable;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.sc0rpi0.quantum.classes.PlayerRole;

public class AuraMethodsFunctions {
	private static int idAuraStrength = 0;
	private static int idAuraRegeneration = 0;
	private static int idAuraResistance = 0;
	private static int idAuraNoEffect = 0;
	private static int idAuraExplosion = 0;
	
	public static Hashtable<Integer, Location> auraStrengthLocation = new Hashtable<Integer, Location>();
	public static Hashtable<Integer, Player> auraStrengthPlayer = new Hashtable<Integer, Player>();
	
	public static Hashtable<Integer, Location> auraRegenerationLocation = new Hashtable<Integer, Location>();
	public static Hashtable<Integer, Player> auraRegenerationPlayer = new Hashtable<Integer, Player>();
	
	public static Hashtable<Integer, Location> auraResistanceLocation = new Hashtable<Integer, Location>();
	public static Hashtable<Integer, Player> auraResistancePlayer = new Hashtable<Integer, Player>();
	
	public static Hashtable<Integer, Location> auraNoEffectLocation = new Hashtable<Integer, Location>();
	
	public static Hashtable<Integer, Location> auraExplosionLocation = new Hashtable<Integer, Location>();
	public static Hashtable<Integer, Player> auraExplosionPlayer = new Hashtable<Integer, Player>();
	
	public static int getIdAuraStrength() {
		idAuraStrength ++;
		return idAuraStrength;
	}
	
	public static int getIdAuraRegeneration() {
		idAuraRegeneration ++;
		return idAuraRegeneration;
	}
	
	public static int getIdAuraResistance() {
		idAuraResistance ++;
		return idAuraResistance;
	}
	
	public static int getIdAuraExplosion() {
		idAuraExplosion ++;
		return idAuraExplosion;
	}
	
	public static int getIdAuraNoEffect() {
		idAuraNoEffect ++;
		return idAuraNoEffect;
	}
	
	public static int addAuraStrength(Location location, Player player) {
		int index = getIdAuraStrength();
		auraStrengthLocation.put(index, location);
		auraStrengthPlayer.put(index, player);
		return index;
	}
	
	public static void removeAuraStrength(int index) {
		auraStrengthLocation.remove(index);
		auraStrengthPlayer.remove(index);
	}
	
	public static int addAuraResistance(Location location, Player player) {
		int index = getIdAuraResistance();
		auraResistanceLocation.put(index, location);
		auraResistancePlayer.put(index, player);
		return index;
	}
	
	public static void removeAuraResistance(int index) {
		auraResistanceLocation.remove(index);
		auraResistancePlayer.remove(index);
	}
	
	public static int addAuraRegeneration(Location location, Player player) {
		int index = getIdAuraRegeneration();
		auraRegenerationLocation.put(index, location);
		auraRegenerationPlayer.put(index, player);
		return index;
	}
	
	public static void removeAuraRegeneration(int index) {
		auraRegenerationLocation.remove(index);
		auraRegenerationPlayer.remove(index);
	}
	
	public static int addAuraExplosion(Location location, Player player) {
		int index = getIdAuraExplosion();
		auraExplosionLocation.put(index, location);
		auraExplosionPlayer.put(index, player);
		return index;
	}
	
	public static void removeAuraExplosion(int index) {
		auraExplosionLocation.remove(index);
		auraExplosionPlayer.remove(index);
	}
	
	public static int addAuraNoEffect(Location location) {
		int index = getIdAuraNoEffect();
		auraNoEffectLocation.put(index, location);
		return index;
	}
	
	public static void removeAuraNoEffect(int index) {
		auraNoEffectLocation.remove(index);
	}
	
	public static int getResistanceAmplifier(int index) {
		Player player = auraResistancePlayer.get(index);
		PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
		
		double variante = 0.5;
		
		if (playerRole.getName().equalsIgnoreCase("Wizard")) {
			
		} else if (playerRole.getName().equalsIgnoreCase("Cleric")) {
			variante = variante * 2 / 3;
			
		} else {
			variante = variante / 3;
		}
		
		return (int) (variante * playerRole.getMagic() + 1);
	}
}
