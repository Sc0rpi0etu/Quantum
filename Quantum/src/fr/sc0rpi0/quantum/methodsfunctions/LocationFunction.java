package fr.sc0rpi0.quantum.methodsfunctions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class LocationFunction {
	private static double x;
	private static double y;
	private static double z;
	private static double k;
	private static double j;
	private static double i;
	
	public static Location getLocationView(Player player, int distance) {
		x = player.getEyeLocation().getX();
		y = player.getEyeLocation().getY();
		z = player.getEyeLocation().getZ();
		
		k = player.getEyeLocation().getDirection().getY();
		j = player.getEyeLocation().getDirection().getZ();
		i = player.getEyeLocation().getDirection().getX();
		
		Location location = new Location(player.getWorld(), x, y, z);
		
		boolean hasEntity = false;
		
		while (location.getBlock().getType().isAir() && location.getY() > 0 && location.distance(player.getLocation()) < distance && !hasEntity) {
			x = x + 0.1 * i;
			y = y + 0.1 * k;
			z = z + 0.1 * j;
			location = new Location(player.getWorld(), x, y, z);
			
			for (LivingEntity entity : location.getWorld().getLivingEntities()) {
				if (entity.getBoundingBox().contains(location.getX(), location.getY(), location.getZ()) && !entity.getUniqueId().equals(player.getUniqueId())) {
					hasEntity = true;
				}
			}
		}
		
		return location;
	}
	
	public static LivingEntity getEntityView(Player player, int distance) {
		x = player.getEyeLocation().getX();
		y = player.getEyeLocation().getY();
		z = player.getEyeLocation().getZ();
		
		k = player.getEyeLocation().getDirection().getY();
		j = player.getEyeLocation().getDirection().getZ();
		i = player.getEyeLocation().getDirection().getX();
		
		Location location = new Location(player.getWorld(), x, y, z);
		
		boolean hasEntity = false;
		LivingEntity entityFind = null;
		
		while (location.getBlock().getType().isAir() && location.getY() > 0 && location.distance(player.getLocation()) < distance && !hasEntity) {
			x = x + 0.1 * i;
			y = y + 0.1 * k;
			z = z + 0.1 * j;
			location = new Location(player.getWorld(), x, y, z);
			
			for (LivingEntity entity : location.getWorld().getLivingEntities()) {
				if (entity.getBoundingBox().contains(location.getX(), location.getY(), location.getZ()) && !entity.getUniqueId().equals(player.getUniqueId())) {
					entityFind = entity;
					hasEntity = true;
				}
			}
		}
		
		return entityFind;
	}
	
	public static List<LivingEntity> getEntityAround(Location location, int distance) {
		List<LivingEntity> entityList = new ArrayList<LivingEntity>();
		
		for (LivingEntity entity : location.getWorld().getLivingEntities()) {
			if (entity.getLocation().distance(location) < distance) {
				LivingEntity livingEntity = (LivingEntity) entity;
				entityList.add(livingEntity);
			}
		}
		
		return entityList;
	}
	
	public static boolean hasEntityLocation(Location location) {
		for (Entity entity : location.getWorld().getEntities()) {
			if (entity.getBoundingBox().contains(location.getX(), location.getY(), location.getZ())) {
				return true;
			}
		}
		
		return false;
	}
}
