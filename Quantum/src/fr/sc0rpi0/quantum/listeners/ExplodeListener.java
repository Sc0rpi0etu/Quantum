package fr.sc0rpi0.quantum.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockExplodeEvent;

public class ExplodeListener {
	@EventHandler
	private void OnExplode(BlockExplodeEvent event) {
		event.setCancelled(true);
	}
}
