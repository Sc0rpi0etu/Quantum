package fr.sc0rpi0.quantum.run;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.sc0rpi0.quantum.classes.PlayerRole;
import fr.sc0rpi0.quantum.methodsfunctions.AuraMethodsFunctions;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;

public class AuraStrength extends BukkitRunnable {
	@Override
	public void run() {
		for (World world : Bukkit.getWorlds()) {
			for (Entity entity : world.getEntities()) {
				if (entity instanceof LivingEntity) {
					LivingEntity livingEntity = (LivingEntity) entity;
					addStrength(livingEntity);
				}
			}
		}
	}

	private void addStrength(LivingEntity livingEntity) {
		if (AuraMethodsFunctions.auraStrengthLocation.size() > 0) {
			for (Entry<Integer, Location> entry : AuraMethodsFunctions.auraStrengthLocation.entrySet()) {
				if (entry.getValue().distance(livingEntity.getLocation()) <= 5) {
					livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3 * 20, getStrengthAmplifier(entry.getKey()) - 1));
				}
			}
		}
	}
	
	private int getStrengthAmplifier(int index) {
		Player player = AuraMethodsFunctions.auraStrengthPlayer.get(index);
		PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
		
		double variante = 0.2;
		
		if (playerRole.getName().equalsIgnoreCase("Wizard")) {
			
		} else if (playerRole.getName().equalsIgnoreCase("Cleric")) {
			variante = variante * 2 / 3;
			
		} else {
			variante = variante / 3;
		}
		
		return (int) (variante * playerRole.getMagic() + 1);
	}
}
