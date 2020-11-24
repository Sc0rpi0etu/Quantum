package fr.sc0rpi0.quantum.listeners;

import java.util.Hashtable;
import java.util.Map.Entry;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;

public class TargetListener implements Listener {
	public Hashtable<Integer, Player> targetPlayerList = new Hashtable<Integer, Player>();
	public Hashtable<Integer, String> targetMonsterList = new Hashtable<Integer, String>();
	private int idTarget = 0;
	
	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	private void OnTarget(EntityTargetEvent event) {
		Entity entity = event.getEntity();
		Entity targetedEntity = event.getTarget();
		if (targetedEntity instanceof Player && RoleMethodsFunctions.getRole((Player)targetedEntity) != null) {
			if (targetMonsterList.size() > 0) {
				for (Entry<Integer, String> entry : targetMonsterList.entrySet()) {
					if (entry.getValue().equals(entity.getUniqueId())) {
						event.setTarget(targetPlayerList.get(entry.getKey()));
						break;
					}
				}
				
			} else {
				int index = getIdTarget();
				targetMonsterList.put(index, entity.getUniqueId().toString());
				targetPlayerList.put(index, (Player) targetedEntity);
			}
		}
	}
	
	public int getIdTarget() {
		idTarget++;
		return idTarget;
	}
}
