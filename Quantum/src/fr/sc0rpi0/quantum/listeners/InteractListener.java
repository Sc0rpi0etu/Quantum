package fr.sc0rpi0.quantum.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.sc0rpi0.quantum.CustomItems;
import fr.sc0rpi0.quantum.Main;
import fr.sc0rpi0.quantum.classes.PlayerRole;
import fr.sc0rpi0.quantum.methodsfunctions.AuraMethodsFunctions;
import fr.sc0rpi0.quantum.methodsfunctions.LocationFunction;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;
import fr.sc0rpi0.quantum.methodsfunctions.ScoreboardMethods;

public class InteractListener implements Listener {
	private Main main;
	
	public InteractListener(Main main) {
		this.main = main;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	private void OnInteract(PlayerInteractEvent event) {
		if (RoleMethodsFunctions.getRole(event.getPlayer()) != null && event.getHand().equals(EquipmentSlot.HAND)) {
			Player player = event.getPlayer();
			PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
			
			try {
				ItemStack itemStack = player.getItemInHand();
				if (itemStack.getItemMeta().getLore().equals(CustomItems.loreWizard) && (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					
					if (itemMeta.getDisplayName().equals("Wizard : Lightning")) {
						itemMeta.setDisplayName("Wizard : Fireball");
						
					} else if (itemMeta.getDisplayName().equals("Wizard : Fireball")) {
						itemMeta.setDisplayName("Wizard : Iceball");
						
					} else if (itemMeta.getDisplayName().equals("Wizard : Iceball") && (playerRole.getName().equalsIgnoreCase("Wizard") || playerRole.getName().equalsIgnoreCase("Admin"))) {
						itemMeta.setDisplayName("Wizard : Ulti");
						
					} else if (itemMeta.getDisplayName().equalsIgnoreCase("Wizard : Iceball")) {
						itemMeta.setDisplayName("Wizard : Lightning");
						
					} else if (itemMeta.getDisplayName().contains("Wizard : Ulti")) {
						itemMeta.setDisplayName("Wizard : Lightning");
					}
					
					itemStack.setItemMeta(itemMeta);
					
				} else if (itemStack.getItemMeta().getLore().equals(CustomItems.loreCleric) && (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					
					if (itemMeta.getDisplayName().equals("Cleric : Strength")) {
						itemMeta.setDisplayName("Cleric : Resistance");
						
					} else if (itemMeta.getDisplayName().equals("Cleric : Resistance")) {
						itemMeta.setDisplayName("Cleric : Heal");
						
					} else if (itemMeta.getDisplayName().equals("Cleric : Heal") && (playerRole.getName().equalsIgnoreCase("Cleric") || playerRole.getName().equalsIgnoreCase("Admin"))) {
						itemMeta.setDisplayName("Cleric : Ulti");
						
					} else if (itemMeta.getDisplayName().equals("Cleric : Heal")) {
						itemMeta.setDisplayName("Cleric : Strength");
						
					} else if (itemMeta.getDisplayName().contains("Cleric : Ulti")) {
						itemMeta.setDisplayName("Cleric : Strength");
					}
					
					itemStack.setItemMeta(itemMeta);
					
				} else if (itemStack.getItemMeta().getLore().equals(CustomItems.loreWizard) && (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) && (playerRole.getName().equalsIgnoreCase("Admin") || playerRole.getName().equalsIgnoreCase("Wizard"))) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					Entity ent;
					Location locationView = LocationFunction.getLocationView(player, 50);
					
					switch (itemMeta.getDisplayName()) {
						case "Wizard : Fireball":
							if (playerRole.getMana() >= 50) {
								ent = player.getWorld().spawnEntity(player.getEyeLocation().add(player.getEyeLocation().getDirection().getX() * 5, player.getEyeLocation().getDirection().getY() * 5, player.getEyeLocation().getDirection().getZ() * 5), EntityType.FIREBALL);
								ent.setVelocity(new Vector(player.getEyeLocation().getDirection().getX() * 2, player.getEyeLocation().getDirection().getY() * 2, player.getEyeLocation().getDirection().getZ() * 2));
								
								ent.setCustomName(player.getUniqueId().toString());
								Location location = player.getLocation();
								new BukkitRunnable() {
									
									@Override
									public void run() {
										if (ent.getLocation().distance(location) > 50) {
											ent.remove();
											cancel();
										}
									}
								}.runTaskTimer(main, 20, 0);
								
								playerRole.setMana(playerRole.getMana() - 50);
							}
							break;
						
						case "Wizard : Iceball":
							if (playerRole.getMana() >= 20) {
								ent = player.getWorld().spawnEntity(player.getEyeLocation().add(player.getEyeLocation().getDirection().getX() * 5, player.getEyeLocation().getDirection().getY() * 5, player.getEyeLocation().getDirection().getZ() * 5), EntityType.SNOWBALL);
								ent.setCustomName(player.getUniqueId().toString());
								ent.setVelocity(player.getEyeLocation().getDirection());
								playerRole.setMana(playerRole.getMana() - 20);
							}
							break;
						
						case "Wizard : Lightning":
							if (locationView.distance(player.getLocation()) < 16 && playerRole.getMana() >= 70) { 
								ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
								String command = "summon minecraft:lightning_bolt " + locationView.getX() + " " + locationView.getY() + " " + locationView.getZ();
								
								if (playerRole.getName().equals("Admin")) {
									for (int i = 0; i < 10; i++) {
										Bukkit.dispatchCommand(console, command);
									}
									
								} else {
									for (int i = 0; i < playerRole.getMagic() * 0.039 + 1; i++) {
										Bukkit.dispatchCommand(console, command);
									}
								}
								
								playerRole.setMana(playerRole.getMana() - 70);
							}
							break;
							
						case "Wizard : Ulti Ready":
							if (playerRole.getMana() >= 200 && (LocationFunction.hasEntityLocation(locationView) || !locationView.getBlock().getType().isAir())) {
								
								if (playerRole.getName().equals("Admin")) {
									for (int i = 0; i < 10; i++) {
										player.getWorld().createExplosion(locationView, 4f);
									}
									
								} else {
									for (int i = 0; i < playerRole.getMagic() * 0.039 + 1; i++) {
										player.getWorld().createExplosion(locationView, 4f);
									}
								}
								
								playerRole.setMana(playerRole.getMana() - 200);
							}
							break;
							
						default:
							break;
					}
					
				} else if (itemStack.getItemMeta().getLore().equals(CustomItems.loreCleric) && (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) && (playerRole.getName().equalsIgnoreCase("Admin") || playerRole.getName().equalsIgnoreCase("Cleric"))) {
					ItemMeta itemMeta = itemStack.getItemMeta();
					DustOptions options;
					int index;
					int count = 100;
					Location location = LocationFunction.getLocationView(player, 50);
					
					switch (itemMeta.getDisplayName()) {
						case "Cleric : Resistance":
							if (playerRole.getMana() >= 50 && (LocationFunction.hasEntityLocation(location) || !location.getBlock().getType().isAir())) {
								options = new DustOptions(Color.BLACK, 1);
								index = AuraMethodsFunctions.addAuraResistance(location, player);
								player.spawnParticle(Particle.REDSTONE, location, count, 1, 0, 1, options);
								
								new BukkitRunnable() {
									int i = 0;
									@Override
									public void run() {
										player.spawnParticle(Particle.REDSTONE, location, count, 1, 0, 1, options);
										i ++;
										if (i > 10) {
											AuraMethodsFunctions.removeAuraResistance(index);
											cancel();
										}
									}
								}.runTaskTimer(main, 20, 20);
								playerRole.setMana(playerRole.getMana() - 50);
							}
							break;
						
						case "Cleric : Heal":
							if (playerRole.getMana() > 30) {
								if (LocationFunction.getEntityView(player, 25) != null) {
									LivingEntity entityFind = LocationFunction.getEntityView(player, 25);
									
									if (entityFind.getType().equals(EntityType.SKELETON) || entityFind.getType().equals(EntityType.STRAY) || entityFind.getType().equals(EntityType.WITHER_SKELETON) || entityFind.getType().equals(EntityType.ZOMBIE) || entityFind.getType().equals(EntityType.DROWNED) || entityFind.getType().equals(EntityType.HUSK) || entityFind.getType().equals(EntityType.PIG_ZOMBIE) || entityFind.getType().equals(EntityType.PHANTOM) || entityFind.getType().equals(EntityType.WITHER) || entityFind.getType().equals(EntityType.SKELETON_HORSE) || entityFind.getType().equals(EntityType.ZOMBIE_HORSE) || entityFind.getType().equals(EntityType.ZOMBIE_VILLAGER)) {
										entityFind.damage(5);
									
									} else if (entityFind instanceof Player && RoleMethodsFunctions.getRole((Player) entityFind) != null) {
										PlayerRole playerFindRole = RoleMethodsFunctions.getRole((Player) entityFind);
										
										if (playerFindRole.getHealth() + 50 > ScoreboardMethods.getHealthMax((Player) entityFind)) {
											playerFindRole.setHealth(ScoreboardMethods.getHealthMax((Player) entityFind));
											
										} else {
											playerFindRole.setHealth(playerFindRole.getHealth() + 50);
										}
										
										((Player) entityFind).setHealth(playerFindRole.getHealth() * 20 / ScoreboardMethods.getHealthMax((Player) entityFind));
										
									} else {
										if (entityFind.getHealth() + 5 > entityFind.getMaxHealth()) {
											entityFind.setHealth(entityFind.getMaxHealth());
											
										} else {
											entityFind.setHealth(entityFind.getHealth() + 5);
										}
									}
									
								} else {
									if (playerRole.getHealth() + 50 > ScoreboardMethods.getHealthMax(player)) {
										playerRole.setHealth(ScoreboardMethods.getHealthMax(player));
										
									} else {
										playerRole.setHealth(playerRole.getHealth() + 50);
									}
									
									player.setHealth(playerRole.getHealth() * 20 / ScoreboardMethods.getHealthMax(player));
								}
								playerRole.setMana(playerRole.getMana() - 30);
							}
							break;
						
						case "Cleric : Strength":
							if (playerRole.getMana() >= 50 && (LocationFunction.hasEntityLocation(location) || !location.getBlock().getType().isAir())) {
								options = new DustOptions(Color.PURPLE, 1);
								index = AuraMethodsFunctions.addAuraStrength(location, player);
								player.spawnParticle(Particle.REDSTONE, location, count, 1, 0, 1, options);
								
								new BukkitRunnable() {
									int i = 0;
									@Override
									public void run() {
										player.spawnParticle(Particle.REDSTONE, location, count, 1, 0, 1, options);
										i ++;
										if (i > 10) {
											AuraMethodsFunctions.removeAuraStrength(index);
											cancel();
										}
									}
								}.runTaskTimer(main, 20, 20);
								playerRole.setMana(playerRole.getMana() - 50);
							}
							break;
						
						case "Cleric : Ulti Ready":
							if (playerRole.getMana() >= 200 && (LocationFunction.hasEntityLocation(location) || !location.getBlock().getType().isAir())) {
								options = new DustOptions(Color.fromRGB(0, 251, 255), 1);
								index = AuraMethodsFunctions.addAuraRegeneration(location, player);
								int indexStrength = AuraMethodsFunctions.addAuraStrength(location, player);
								int indexNoEffect = AuraMethodsFunctions.addAuraNoEffect(location);
								playerRole.setUlti(0);
								player.spawnParticle(Particle.REDSTONE, location, count, 1, 0, 1, options);
								
								new BukkitRunnable() {
									int i = 0;
									@Override
									public void run() {
										player.spawnParticle(Particle.REDSTONE, location, count, 1, 0, 1, options);
										i ++;
										if (i >= 30) {
											AuraMethodsFunctions.removeAuraStrength(indexStrength);
											AuraMethodsFunctions.removeAuraRegeneration(index);
											AuraMethodsFunctions.removeAuraNoEffect(indexNoEffect);
											cancel();
										}
									}
								}.runTaskTimer(main, 20, 20);
								
								playerRole.setMana(playerRole.getMana() - 200);
							}
							break;
							
						default:
							break;
					}
					
				} else if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("Ulti : Ready") && itemStack.getItemMeta().getLore().equals(CustomItems.loreUlti) && (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) && (playerRole.getName().equalsIgnoreCase("Admin") || (!playerRole.getName().equalsIgnoreCase("Wizard") && !playerRole.getName().equalsIgnoreCase("Cleric")))) {
					RoleMethodsFunctions.getRole(player).setUlti(0);
					if (RoleMethodsFunctions.getArcher(player.getUniqueId().toString()) != null) {
						RoleMethodsFunctions.getArcher(player.getUniqueId().toString()).setUltiTime(20);
						
					} else if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Murderer")) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, (int) (RoleMethodsFunctions.getRole(player).getStrength() / 3 + 1)));
						player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20, 10));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, (int) (RoleMethodsFunctions.getRole(player).getDexterity() / 10 + 1)));
						
					} else if (RoleMethodsFunctions.getWarrior(player.getUniqueId().toString()) != null) {
						RoleMethodsFunctions.getWarrior(player.getUniqueId().toString()).setUltiTime(10);
						
					} else if (RoleMethodsFunctions.getTank(player.getUniqueId().toString()) != null) {
						RoleMethodsFunctions.getTank(player.getUniqueId().toString()).setUltiTime(10);
					}
				}
				
				ScoreboardMethods.createScoreboard(player);
			} catch (Exception e) {
				
			}
		}
	}
}
