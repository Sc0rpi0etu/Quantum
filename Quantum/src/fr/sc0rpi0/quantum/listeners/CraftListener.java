package fr.sc0rpi0.quantum.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import fr.sc0rpi0.quantum.CustomItems;

public class CraftListener implements Listener {
	private CustomItems customItems;
	
	public CraftListener(CustomItems customItems) {
		this.customItems = customItems;
	}
	
	@EventHandler
	private void OnCraft(CraftItemEvent event) {
		if (event.getInventory().contains(customItems.clericStick()) || event.getInventory().contains(customItems.wizardStick()) || event.getInventory().contains(customItems.ultiStick())) {
			event.setCancelled(true);
			event.getWhoClicked().sendMessage("You can't use special item to craft");
		}
	}
}
