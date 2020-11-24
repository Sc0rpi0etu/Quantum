package fr.sc0rpi0.quantum.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;

public class BlockIgniteListener implements Listener {
	
	@EventHandler
	private void OnIgnite(BlockIgniteEvent event) {
		if (event.getCause().equals(IgniteCause.LIGHTNING)) {
			event.setCancelled(true);
		}
	}
}
