package fr.sc0rpi0.quantum.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.sc0rpi0.quantum.CustomItems;
import fr.sc0rpi0.quantum.classes.Archer;
import fr.sc0rpi0.quantum.classes.Cleric;
import fr.sc0rpi0.quantum.classes.Assassin;
import fr.sc0rpi0.quantum.classes.PlayerRole;
import fr.sc0rpi0.quantum.classes.Tank;
import fr.sc0rpi0.quantum.classes.Warrior;
import fr.sc0rpi0.quantum.classes.Wizard;
import fr.sc0rpi0.quantum.listeners.HealthListener;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;
import fr.sc0rpi0.quantum.methodsfunctions.ScoreboardMethods;

public class Commands implements CommandExecutor {
	private HealthListener healthListener;
	private CustomItems customItems;
	
	public Commands(HealthListener healthListener, CustomItems customItems) {
		this.healthListener = healthListener;
		this.customItems = customItems;
		
	}

	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player && msg.equalsIgnoreCase("q")) {
			Player player = (Player) sender;
			PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
			if (RoleMethodsFunctions.getRole(player) != null && args[0].equalsIgnoreCase("stats")) {
				player.sendMessage("Your stats :\n" + RoleMethodsFunctions.getRole(player.getName()).ToString());
			
			} else if (RoleMethodsFunctions.getRole(player) != null && args[0].equalsIgnoreCase("role")) {
				player.sendMessage("This is your role : " + RoleMethodsFunctions.getRole(player.getName()).getName());
			
			} else if (args[0].equalsIgnoreCase("help")) {
				if (RoleMethodsFunctions.getRole(player) != null) {
					player.sendMessage("You can do these commands :\n/q role (to get your role)\n/q stats (to get your stats)\n/q setPoints (to use your points)\n/q restart [role] (to restart with a new role)");
				} else {
					player.sendMessage("You can do these commands :\n/q setrole [role] (to set your role)");
				}
					
			} else if (args[0].equalsIgnoreCase("setrole") && (args[1].equalsIgnoreCase("warrior") || args[1].equalsIgnoreCase("archer") || args[1].equalsIgnoreCase("Cleric") || args[1].equalsIgnoreCase("Assassin") || args[1].equalsIgnoreCase("tank") || args[1].equalsIgnoreCase("Wizard"))) {
				if (args[1].equalsIgnoreCase("Warrior")) {
					RoleMethodsFunctions.setRole(player, new Warrior());
					player.sendMessage("Now you are a Warrior");
					player.getInventory().addItem(getItemStack(Material.DIAMOND_SWORD, "Sword of Warrior"));
					player.getInventory().addItem(getItemStack(Material.BOW, "Bow of Warrior"));
					player.getInventory().setBoots(getItemStack(Material.IRON_BOOTS, "Boots of Warrior"));
					player.getInventory().setLeggings(getItemStack(Material.IRON_LEGGINGS, "Leggings of Warrior"));
					player.getInventory().setChestplate(getItemStack(Material.IRON_CHESTPLATE, "Chestplate of Warrior"));
					player.getInventory().setHelmet(getItemStack(Material.IRON_HELMET, "Helmet of Warrior"));
					player.getInventory().addItem(getItemStack(Material.ARROW, "Arrow of Warrior", 40));
					
					RoleMethodsFunctions.setWarrior(player.getUniqueId().toString(), 0);
					
				} else if (args[1].equalsIgnoreCase("Archer")) {
					RoleMethodsFunctions.setRole(player, new Archer());
					player.sendMessage("Now you are an Archer");
					player.getInventory().addItem(getItemStack(Material.BOW, "Bow of Archer"));
					player.getInventory().addItem(getItemStack(Material.CROSSBOW, "CrossBow of Archer"));
					player.getInventory().addItem(getItemStack(Material.TRIDENT, "Trident of Archer"));
					player.getInventory().addItem(getItemStack(Material.ARROW, "Arrow of Archer", 64));
					
					RoleMethodsFunctions.setArcher(player.getUniqueId().toString(), 0);
					
				} else if (args[1].equalsIgnoreCase("Cleric")) {
					RoleMethodsFunctions.setRole(player, new Cleric());
					player.sendMessage("Now you are a Cleric");
					player.getInventory().addItem(customItems.clericStick());
					
				} else if (args[1].equalsIgnoreCase("Assassin")) {
					RoleMethodsFunctions.setRole(player, new Assassin());
					player.sendMessage("Now you are an Assassin");
					player.getInventory().addItem(getItemStack(Material.IRON_SWORD, "Sword of Assassin"));
					player.getInventory().addItem(getItemStack(Material.CROSSBOW, "Crossbow of Assassin"));
					player.getInventory().setBoots(getItemStack(Material.IRON_BOOTS, "Boots of Assassin"));
					player.getInventory().setLeggings(getItemStack(Material.IRON_LEGGINGS, "Leggings of Assassin"));
					player.getInventory().setChestplate(getItemStack(Material.IRON_CHESTPLATE, "Chestplate of Assassin"));
					player.getInventory().setHelmet(getItemStack(Material.IRON_HELMET, "Helmet of Assassin"));
					player.getInventory().addItem(getItemStack(Material.ARROW, "Arrow of Assassin", 40));
					
				} else if (args[1].equalsIgnoreCase("Tank")) {
					RoleMethodsFunctions.setRole(player, new Tank());
					player.sendMessage("Now you are a Tank");
					player.getInventory().addItem(getItemStack(Material.IRON_SWORD, "Sword of Tank"));
					player.getInventory().addItem(getItemStack(Material.SHIELD, "Shield of Tank"));
					player.getInventory().setBoots(getItemStack(Material.DIAMOND_BOOTS, "Boots of Tank"));
					player.getInventory().setLeggings(getItemStack(Material.DIAMOND_LEGGINGS, "Leggings of Tank"));
					player.getInventory().setChestplate(getItemStack(Material.DIAMOND_CHESTPLATE, "Chestplate of Tank"));
					player.getInventory().setHelmet(getItemStack(Material.DIAMOND_HELMET, "Helmet of Tank"));
					
					RoleMethodsFunctions.setTank(player.getUniqueId().toString(), 0);
					
				} else if (args[1].equalsIgnoreCase("Wizard")) {
					RoleMethodsFunctions.setRole(player, new Wizard());
					player.sendMessage("Now you are a Wizard");
					player.getInventory().addItem(customItems.wizardStick());
				}
				RoleMethodsFunctions.getRole(player).setHealth(healthListener.getHealthMax(player));
				player.setExp(0);
				player.setLevel(1);
				
			} else if (RoleMethodsFunctions.getRole(player) != null && args[0].equalsIgnoreCase("setPoint") && playerRole.getStatsPoint() > 0) {
				if (args[1].equalsIgnoreCase("INT")) {
					playerRole.setMagic(playerRole.getMagic() + playerRole.getAugmentMagic());
					playerRole.setStatsPoint(playerRole.getStatsPoint() - 1);
					player.sendMessage("You set your point in INT");
					
				} else if (args[1].equalsIgnoreCase("STR")) {
					playerRole.setStrength(playerRole.getStrength() + playerRole.getAugmentStrength());
					playerRole.setStatsPoint(playerRole.getStatsPoint() - 1);
					player.sendMessage("You set your point in STR");
					
				} else if (args[1].equalsIgnoreCase("VIT")) {
					playerRole.setVit(playerRole.getVit() + playerRole.getAugmentVit());
					playerRole.setStatsPoint(playerRole.getStatsPoint() - 1);
					player.setHealth(playerRole.getHealth() * 20 / healthListener.getHealthMax(player));
					player.sendMessage("You set your point in VIT");
					
				} else if (args[1].equalsIgnoreCase("DEX")) {
					playerRole.setDexterity(playerRole.getDexterity() + playerRole.getAugmentDexterity());
					playerRole.setStatsPoint(playerRole.getStatsPoint() - 1);
					
				} else if (args[1].equalsIgnoreCase("DEF")) {
					playerRole.setDefense(playerRole.getDefense() + playerRole.getAugmentDefense());
					playerRole.setStatsPoint(playerRole.getStatsPoint() - 1);
					player.sendMessage("You set your point in DEF");
					
				} else if (args[1].equalsIgnoreCase("CRIT")) {
					playerRole.setCritical(playerRole.getCritical() + playerRole.getAugmentCritical());
					playerRole.setStatsPoint(playerRole.getStatsPoint() - 1);
					player.sendMessage("You set your point in CRIT");
				}
				
			} else if (RoleMethodsFunctions.getRole(player) != null && args[0].equalsIgnoreCase("restart") && (args[1].equalsIgnoreCase("warrior") || args[1].equalsIgnoreCase("archer") || args[1].equalsIgnoreCase("Cleric") || args[1].equalsIgnoreCase("Assassin") || args[1].equalsIgnoreCase("tank") || args[1].equalsIgnoreCase("Wizard")) && RoleMethodsFunctions.getRole(player).getLevel() >= 10) {
				if (args[1].equalsIgnoreCase("Warrior")) {
					if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Archer")) {
						RoleMethodsFunctions.removeArcher(player.getUniqueId().toString());
						
					} else if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Warrior")) {
						RoleMethodsFunctions.removeWarrior(player.getUniqueId().toString());
						
					} else if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Tank")) {
						RoleMethodsFunctions.removeTank(player.getUniqueId().toString());
					}
					
					RoleMethodsFunctions.setRole(player, new Warrior());
					player.sendMessage("Now you are a Warrior");
					
					RoleMethodsFunctions.setWarrior(player.getUniqueId().toString(), 0);
					
				} else if (args[1].equalsIgnoreCase("Archer")) {
					if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Archer")) {
						RoleMethodsFunctions.removeArcher(player.getUniqueId().toString());
						
					} else if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Warrior")) {
						RoleMethodsFunctions.removeWarrior(player.getUniqueId().toString());
						
					} else if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Tank")) {
						RoleMethodsFunctions.removeTank(player.getUniqueId().toString());
					}
					
					RoleMethodsFunctions.setRole(player, new Archer());
					player.sendMessage("Now you are a Archer");
					
					RoleMethodsFunctions.setArcher(player.getUniqueId().toString(), 0);
					
				} else if (args[1].equalsIgnoreCase("Cleric")) {
					if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Archer")) {
						RoleMethodsFunctions.removeArcher(player.getUniqueId().toString());
						
					} else if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Warrior")) {
						RoleMethodsFunctions.removeWarrior(player.getUniqueId().toString());
						
					} else if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Tank")) {
						RoleMethodsFunctions.removeTank(player.getUniqueId().toString());
					}
					
					RoleMethodsFunctions.setRole(player, new Cleric());
					player.sendMessage("Now you are a Cleric");
					
				} else if (args[1].equalsIgnoreCase("Assassin")) {
					if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Archer")) {
						RoleMethodsFunctions.removeArcher(player.getUniqueId().toString());
						
					} else if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Warrior")) {
						RoleMethodsFunctions.removeWarrior(player.getUniqueId().toString());
						
					} else if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Tank")) {
						RoleMethodsFunctions.removeTank(player.getUniqueId().toString());
					}
					
					RoleMethodsFunctions.setRole(player, new Assassin());
					player.sendMessage("Now you are an Assassin");
					
				} else if (args[1].equalsIgnoreCase("Tank")) {
					if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Archer")) {
						RoleMethodsFunctions.removeArcher(player.getUniqueId().toString());
						
					} else if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Warrior")) {
						RoleMethodsFunctions.removeWarrior(player.getUniqueId().toString());
						
					} else if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Tank")) {
						RoleMethodsFunctions.removeTank(player.getUniqueId().toString());
					}
					
					RoleMethodsFunctions.setRole(player, new Tank());
					player.sendMessage("Now you are a Tank");
					
					RoleMethodsFunctions.setTank(player.getUniqueId().toString(), 0);
					
				} else if (args[1].equalsIgnoreCase("Wizard")) {
					if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Archer")) {
						RoleMethodsFunctions.removeArcher(player.getUniqueId().toString());
						
					} else if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Warrior")) {
						RoleMethodsFunctions.removeWarrior(player.getUniqueId().toString());
						
					} else if (RoleMethodsFunctions.getRole(player).getName().equalsIgnoreCase("Tank")) {
						RoleMethodsFunctions.removeTank(player.getUniqueId().toString());
					}
					
					RoleMethodsFunctions.setRole(player, new Wizard());
					player.sendMessage("Now you are a Wizard");
				}
				RoleMethodsFunctions.getRole(player).setHealth(healthListener.getHealthMax(player));
				player.setExp(0);
				player.setLevel(1);
				
			} else if (RoleMethodsFunctions.getRole(player) != null && args[0].equalsIgnoreCase("restart") && (args[1].equalsIgnoreCase("warrior") || args[1].equalsIgnoreCase("archer") || args[1].equalsIgnoreCase("Cleric") || args[1].equalsIgnoreCase("Assassin") || args[1].equalsIgnoreCase("tank") || args[1].equalsIgnoreCase("Wizard"))) {
				player.sendMessage("Reach level 10 to restart again");
			}
			ScoreboardMethods.createScoreboard(player);
			return true;
		}
		return false;
	}
	
	private ItemStack getItemStack(Material material, String name) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(name);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	private ItemStack getItemStack(Material material, String name, int amount) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(name);
		itemStack.setItemMeta(itemMeta);
		itemStack.setAmount(amount);
		return itemStack;
	}
}
