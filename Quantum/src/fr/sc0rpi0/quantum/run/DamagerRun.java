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

public class DamagerRun extends BukkitRunnable {
	@Override
	public void run() {
		for (World world : Bukkit.getWorlds()) {
			for (Entity entity : world.getEntities()) {
				if (entity instanceof LivingEntity) {
					LivingEntity livingEntity = (LivingEntity) entity;
					addDamage(livingEntity);
				}
			}
		}
	}
	
	private void addDamage(LivingEntity livingEntity) {
		if (AuraMethodsFunctions.auraExplosionLocation.size() > 0) {
			for (Entry<Integer, Location> entry : AuraMethodsFunctions.auraExplosionLocation.entrySet()) {
				if (livingEntity.getWorld().equals(entry.getValue().getWorld()) && entry.getValue().distance(livingEntity.getLocation()) <= 10) {
					livingEntity.setHealth(livingEntity.getHealth() - 5);
				}
			}
		}
	}
}
