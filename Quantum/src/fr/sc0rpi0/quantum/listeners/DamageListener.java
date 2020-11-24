package fr.sc0rpi0.quantum.listeners;

import java.util.Hashtable;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

import fr.sc0rpi0.quantum.Main;
import fr.sc0rpi0.quantum.classes.PlayerRole;
import fr.sc0rpi0.quantum.methodsfunctions.AuraMethodsFunctions;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;
import fr.sc0rpi0.quantum.methodsfunctions.ScoreboardMethods;

public class DamageListener implements Listener {
	private Main main;
	private HealthListener healthListener;
	private TargetListener targetListener;
	
	public DamageListener(Main main, HealthListener healthListener, TargetListener targetListener) {
		this.main = main;
		this.healthListener = healthListener;
		this.targetListener = targetListener;
	}
	
	private int idEntityShotByIceball = 0;
	public Hashtable<Integer, Player> damageByIceballHashtable = new Hashtable<Integer, Player>();
	
	@EventHandler (priority=EventPriority.HIGH)
	private void OnHit(EntityDamageByEntityEvent event) {
		Entity entity = event.getDamager();
		Entity victimEntity = event.getEntity();
		double damage = event.getDamage();
		double variante = 0.02;
		
		if (entity.getType() == EntityType.PLAYER && !(event.getEntity() instanceof ArmorStand) && RoleMethodsFunctions.getRole(((Player)entity)) != null) {
			Player damager = (Player) entity;
			PlayerRole playerRole = RoleMethodsFunctions.getRole(damager);
			
			if (playerRole.getName().equalsIgnoreCase("Warrior")) {
				
			} else if (playerRole.getName().equalsIgnoreCase("Archer") || playerRole.getName().equalsIgnoreCase("Assassin")) {
				variante = variante*2/3;
				
			} else if (playerRole.getName().equalsIgnoreCase("Tank") || playerRole.getName().equalsIgnoreCase("Cleric")) {
				variante = variante/3;
				
			} else {
				variante = variante/6;
			}
			
			damage = damage + damage * variante * playerRole.getStrength();
			
		} else if (entity instanceof Projectile) {
			
			Projectile projectile = (Projectile) entity;
			ProjectileSource projectileSource = projectile.getShooter();
			
			if (projectileSource instanceof Player && RoleMethodsFunctions.getRole(((Player)projectileSource)) != null) {
				Player damager = (Player) projectileSource;
				PlayerRole damagerRole = RoleMethodsFunctions.getRole(damager);
				
				variante = 0.015;
				if (damagerRole.getName().equalsIgnoreCase("Archer")) {
					if (RoleMethodsFunctions.getArcher(damager.getUniqueId().toString()).getUltiTime() > 0 && projectile instanceof Arrow && entity.getLocation().distance(victimEntity.getLocation().add(0, 1.4, 0)) <= 0.5) {
						variante *= 2;
					}
					
				} else {
					variante = variante/2;
				}
				
				damage = damage + damage * variante * damagerRole.getDexterity();
				
				ArmorStand am = (ArmorStand) event.getEntity().getWorld().spawnEntity(new Location(event.getEntity().getWorld(), event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ()), EntityType.ARMOR_STAND);
				am.setCustomName("§4-" + Math.round(damage));
				am.setVisible(false);
				am.setCustomNameVisible(true);
				
				long delayTicks = 0;
				long periodTicks = 5;
				new BukkitRunnable() {
					int i = 0;
				    @Override
				    public void run() {
				        i++;
				        if (i >= 2) {
				        	am.remove();
				        	cancel();
				        }
				    }
				}.runTaskTimer(main, delayTicks, periodTicks);
				
			} else if (projectile instanceof Snowball) {
				LivingEntity livingEntity = (LivingEntity) victimEntity;
				livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10 * 20, 3));
				
				if (projectile.getCustomName() != null && RoleMethodsFunctions.getRole(projectile.getCustomName()) != null) {
					PlayerRole playerRole = RoleMethodsFunctions.getRole(projectile.getCustomName());
					damage = playerRole.getMagic() * 0.5;
				}
				
				if (livingEntity instanceof Player) {
					int index = getIdEntityShotByIceball();
					
					damageByIceballHashtable.put(index, (Player) livingEntity);
					
					new BukkitRunnable() {
						int i = 0;
						@Override
						public void run() {
							i++;
							if (i >= 10) {
								damageByIceballHashtable.remove(index);
								cancel();
							}
						}
					}.runTaskTimer(main, 20, 20);
				}
				
				if (victimEntity.getUniqueId().equals(projectile.getCustomName())) {
					damage = 0;
				}
				
			} else if (projectile instanceof Fireball && projectile.getCustomName() != null) {
				PlayerRole damagerRole = RoleMethodsFunctions.getRole(projectile.getCustomName());
				damage = 10 + damagerRole.getMagic() * 0.5;
				if (victimEntity.getUniqueId().equals(projectile.getCustomName())) {
					damage = 0;
				}
				
			}
		} else if (entity instanceof LightningStrike && entity.getCustomName() != null) {
			PlayerRole damagerRole = RoleMethodsFunctions.getRole(entity.getCustomName());
			damage = 10 + damagerRole.getMagic() * 0.5;
			
			if (victimEntity.getUniqueId().equals(entity.getCustomName())) {
				damage = 0;
			}
		}
		
		ArmorStand am = (ArmorStand) event.getEntity().getWorld().spawnEntity(new Location(event.getEntity().getWorld(), event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ()), EntityType.ARMOR_STAND);
		am.setCustomName("§4-" + Math.round(damage));
		am.setVisible(false);
		am.setCustomNameVisible(true);
		
		long delayTicks = 0;
		long periodTicks = 5;
		new BukkitRunnable() {
			int i = 0;
		    @Override
		    public void run() {
		        i++;
		        if (i >= 2) {
		        	am.remove();
		        	cancel();
		        }
		    }
		}.runTaskTimer(main, delayTicks, periodTicks);
		
		variante = 0.25;
		
		if (victimEntity.getType() == EntityType.PLAYER && !(entity instanceof Projectile) && RoleMethodsFunctions.getRole(((Player)victimEntity)) != null) {
			Player victim = (Player) victimEntity;
			PlayerRole playerRole = RoleMethodsFunctions.getRole(victim);
			
			if (playerRole.getName().equalsIgnoreCase("Tank")) {
				
			} else {
				variante = variante / 6;
			}
			
			double defense = variante * playerRole.getDefense();
			
			if (playerRole.getName().equalsIgnoreCase("Tank") && RoleMethodsFunctions.getTank(victim.getUniqueId().toString()).getUltiTime() > 0) {
				defense = damage - (damage / 10);
				
				if (entity.getType() == EntityType.PLAYER && !(event.getEntity() instanceof ArmorStand)) {
					if (RoleMethodsFunctions.getRole(((Player)entity)).getHealth() - (defense / 2) >= 0) {
						RoleMethodsFunctions.getRole(((Player)entity)).setHealth(RoleMethodsFunctions.getRole(((Player)entity)).getHealth() - (defense / 2));
					
					} else {
						RoleMethodsFunctions.getRole(((Player)entity)).setHealth(0);
					}
					
					((Player)entity).damage(0);
					
				} else if (entity instanceof Projectile) {
					Projectile projectile = (Projectile) entity;
					ProjectileSource projectileSource = projectile.getShooter();
					
					if (projectileSource instanceof Player && RoleMethodsFunctions.getRole(((Player)projectileSource)) != null) {
						if (RoleMethodsFunctions.getRole(((Player)projectileSource)).getHealth() - (defense / 2) >= 0) {
							RoleMethodsFunctions.getRole(((Player)projectileSource)).setHealth(RoleMethodsFunctions.getRole(((Player)projectileSource)).getHealth() - (defense / 2));
						
						} else {
							RoleMethodsFunctions.getRole(((Player)projectileSource)).setHealth(0);
						}
						
						((Player)projectileSource).damage(0);
						
					} else if (projectile instanceof Fireball && projectile.getCustomName() != null) {
						if (RoleMethodsFunctions.getRole(projectile.getCustomName()).getHealth() - (defense / 2) >= 0) {
							RoleMethodsFunctions.getRole(projectile.getCustomName()).setHealth(RoleMethodsFunctions.getRole(((Player)projectileSource)).getHealth() - (defense / 2));
						
						} else {
							RoleMethodsFunctions.getRole(projectile.getCustomName()).setHealth(0);
						}
					}
				}
			}
			
			if (damage - defense < 0) {
				defense = damage;
			}
			
			playerRole.setHealth(playerRole.getHealth() - damage + defense);
			damage = playerRole.getHealth() * 20 / healthListener.getHealthMax(victim);
			ArmorStand amStand = (ArmorStand) event.getEntity().getWorld().spawnEntity(new Location(victim.getWorld(), victim.getLocation().getX(), victim.getLocation().getY(), victim.getLocation().getZ()), EntityType.ARMOR_STAND);
			amStand.setCustomName("§a+" + Math.round(defense));
			amStand.setVisible(false);
			amStand.setCustomNameVisible(true);
			
			new BukkitRunnable() {
				int i = 0;
			    @Override
			    public void run() {
			        i++;
			        if (i >= 2) {
			        	amStand.remove();
			        	cancel();
			        }
			    }
			}.runTaskTimer(main, delayTicks, periodTicks);
			ScoreboardMethods.createScoreboard(victim);
			
			event.setDamage(0);
			victim.setHealth(playerRole.getHealth() * 20 / healthListener.getHealthMax(victim));
			
		} else {
			if (targetListener.targetMonsterList.size() > 0) {
				for (Entry<Integer, String> entry : targetListener.targetMonsterList.entrySet()) {
					if (entry.getValue().equals(victimEntity.getUniqueId()) && entity instanceof Player) {
						
						Player beforeTargetPlayer = targetListener.targetPlayerList.get(entry.getKey());
						PlayerRole beforeTargetPlayerRole = RoleMethodsFunctions.getRole(beforeTargetPlayer);
						
						Player player = (Player) entity;
						PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
						
						if (playerRole.getName().equalsIgnoreCase("Tank") && !beforeTargetPlayerRole.getName().equalsIgnoreCase("Tank")) {
							targetListener.targetPlayerList.replace(entry.getKey(), player);
						}
						break;
					}
				}
			}
			
			event.setDamage(damage);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	private void OnDamage(final EntityDamageEvent event) {
		Entity ent = event.getEntity();
		double damage = event.getDamage();
		
		if (ent instanceof Player && RoleMethodsFunctions.getRole(((Player)ent)) != null) {
			Player player = (Player) ent;
			PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
			
			double variante = 0.4;
			
			if (playerRole.getName().equalsIgnoreCase("Tank") || playerRole.getName().equalsIgnoreCase("Admin")) {
				
			} else {
				variante = variante / 6;
			}
			
			double defense = variante * playerRole.getDefense();
			
			if (AuraMethodsFunctions.auraResistanceLocation.size() > 0) {
				for (Entry<Integer, Location> entry : AuraMethodsFunctions.auraResistanceLocation.entrySet()) {
					if (entry.getValue().distance(player.getLocation()) <= 5) {
						defense += (AuraMethodsFunctions.getResistanceAmplifier(entry.getKey()) / 2);
					}
				}
			}
			
			double armorPoints = player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
			double armorToughness = player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();
			
			if (event.getCause() == DamageCause.POISON || event.getCause() == DamageCause.WITHER) {
				armorPoints = 0;
				armorToughness = 0;
			}
			
			damage = damage * (1-(Math.min(20, Math.max(armorPoints/5, armorPoints-(damage/(2+(armorToughness/4)))))/25));
			
			if (damage - defense < 0) {
				defense = damage;
			}
			
			if (event.getCause() == DamageCause.POISON || event.getCause() == DamageCause.WITHER) {
				defense = 0;
			}
			
			playerRole.setHealth(playerRole.getHealth() + defense - damage);
			event.setDamage(0);
			
			if (playerRole.getHealth() <= 0) {
				playerRole.setHealth(0);
				player.setHealth(0);
				
			} else {
				player.setHealth(playerRole.getHealth() * 20 / healthListener.getHealthMax(player));
			}
			
			ScoreboardMethods.createScoreboard(player);
		}
	}
	
	public int getIdEntityShotByIceball() {
		idEntityShotByIceball ++;
		return idEntityShotByIceball;
	}
}
