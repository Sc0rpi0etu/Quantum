package fr.sc0rpi0.quantum.run;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.sc0rpi0.quantum.methodsfunctions.AuraMethodsFunctions;

public class AuraNoEffect extends BukkitRunnable {
	
	@Override
	public void run() {
		for (World world : Bukkit.getWorlds()) {
			for (Entity entity : world.getEntities()) {
				if (entity instanceof LivingEntity) {
					LivingEntity livingEntity = (LivingEntity) entity;
					clearEffect(livingEntity);
				}
			}
		}
	}

	private void clearEffect(LivingEntity livingEntity) {
		if (AuraMethodsFunctions.auraNoEffectLocation.size() > 0) {
			for (Entry<Integer, Location> entry : AuraMethodsFunctions.auraNoEffectLocation.entrySet()) {
				if (livingEntity.getWorld().equals(entry.getValue().getWorld()) && entry.getValue().distance(livingEntity.getLocation()) <= 5) {
					livingEntity.removePotionEffect(PotionEffectType.BAD_OMEN);
					livingEntity.removePotionEffect(PotionEffectType.BLINDNESS);
					livingEntity.removePotionEffect(PotionEffectType.CONFUSION);
					livingEntity.removePotionEffect(PotionEffectType.HUNGER);
					livingEntity.removePotionEffect(PotionEffectType.POISON);
					livingEntity.removePotionEffect(PotionEffectType.SLOW);
					livingEntity.removePotionEffect(PotionEffectType.SLOW_DIGGING);
					livingEntity.removePotionEffect(PotionEffectType.UNLUCK);
					livingEntity.removePotionEffect(PotionEffectType.WEAKNESS);
					livingEntity.removePotionEffect(PotionEffectType.WITHER);
				}
			}
		}
	}

}
