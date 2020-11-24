package fr.sc0rpi0.quantum.run;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.sc0rpi0.quantum.CustomItems;
import fr.sc0rpi0.quantum.classes.PlayerRole;
import fr.sc0rpi0.quantum.listeners.JoinQuitListener;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;

public class UltiRun extends BukkitRunnable {
	private JoinQuitListener joinQuitListener;
	private CustomItems customItems;
	
	public UltiRun(JoinQuitListener joinQuitListener, CustomItems customItems) {
		this.joinQuitListener = joinQuitListener;
		this.customItems = customItems;
	}
	
	@Override
	public void run() {
		for (Player player : joinQuitListener.playerConnected) {
			addUlti(player);
		}
	}

	@SuppressWarnings("deprecation")
	private void addUlti(Player player) {
		if (RoleMethodsFunctions.getRole(player) != null && RoleMethodsFunctions.getRole(player).getUlti() < 30) {
			PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
			playerRole.setUlti(playerRole.getUlti() + 1);
		}
		
		if (RoleMethodsFunctions.getRole(player) != null && player.getItemInHand() != null && player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasDisplayName() && player.getItemInHand().getItemMeta().hasLore() && (player.getItemInHand().getItemMeta().getLore().equals(customItems.wizardStick().getItemMeta().getLore()) || player.getItemInHand().getItemMeta().getLore().equals(customItems.clericStick().getItemMeta().getLore()) || player.getItemInHand().getItemMeta().getLore().equals(customItems.ultiStick().getItemMeta().getLore())) && player.getItemInHand().getItemMeta().getDisplayName().contains("Ulti")) {
			PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
			String name;
			ItemStack itemStack = player.getItemInHand();
			ItemMeta itemMeta = itemStack.getItemMeta();
			
			if (playerRole.getUlti() == 30 || playerRole.getName().equalsIgnoreCase("Admin")) {
				name = "Ready";
				
			} else {
				name = playerRole.getUlti() + "s";
			}
			
			if (player.getItemInHand().getItemMeta().getDisplayName().contains("Cleric")) {
				itemMeta.setDisplayName("Cleric : Ulti " + name);
				
			} else if (player.getItemInHand().getItemMeta().getDisplayName().contains("Wizard")) {
				itemMeta.setDisplayName("Wizard : Ulti " + name);
				
			} else {
				itemMeta.setDisplayName("Ulti : " + name);
			}
			
			itemStack.setItemMeta(itemMeta);
			player.setItemInHand(itemStack);
		}
	}

}
