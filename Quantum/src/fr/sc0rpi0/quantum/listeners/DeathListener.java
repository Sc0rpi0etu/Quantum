package fr.sc0rpi0.quantum.listeners;

import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.sc0rpi0.quantum.classes.PlayerRole;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;
import fr.sc0rpi0.quantum.methodsfunctions.ScoreboardMethods;

public class DeathListener implements Listener {
	private HealthListener healthListener;
	private TargetListener targetListener;
	
	public DeathListener(HealthListener healthListener, TargetListener targetListener) {
		this.healthListener = healthListener;
		this.targetListener = targetListener;
	}
	
	@EventHandler
	private void OnEntityDeath(EntityDeathEvent event) {
		if (targetListener.targetMonsterList.size() > 0) {
			for (Entry<Integer, String> entry : targetListener.targetMonsterList.entrySet()) {
				if (entry.getValue().equals(event.getEntity().getUniqueId())) {
					targetListener.targetPlayerList.remove(entry.getKey());
					targetListener.targetPlayerList.remove(entry.getKey());
					break;
				}
			}
		}
		
		if (event.getDroppedExp() == 0) {
			switch (event.getEntityType()) {
				case BEE:
				case CAT:
				case CHICKEN:
				case COD:
				case SALMON:
				case PUFFERFISH:
				case TROPICAL_FISH:
				case COW:
				case FOX:
				case HORSE:
				case DONKEY:
				case MULE:
				case SKELETON_HORSE:
				case ZOMBIE_HORSE:
				case LLAMA:
				case TRADER_LLAMA:
				case MUSHROOM_COW:
				case OCELOT:
				case PANDA:
				case PARROT:
				case PIG:
				case POLAR_BEAR:
				case RABBIT:
				case SHEEP:
				case SQUID:
				case DOLPHIN:
				case TURTLE:
					event.setDroppedExp((int)(Math.random() * 2 + 1));
					break;
					
				case CAVE_SPIDER:
				case CREEPER:
				case DROWNED:
				case ENDERMAN:
				case GHAST:
				case HUSK:
				case ILLUSIONER:
				case PHANTOM:
				case PILLAGER:
				case SHULKER:
				case SILVERFISH:
				case SKELETON:
				case SPIDER:
				case STRAY:
				case VEX:
				case VINDICATOR:
				case WITCH:
				case WITHER_SKELETON:
				case ZOMBIE:
				case PIG_ZOMBIE:
					event.setDroppedExp(5);
					break;
					
				case ENDERMITE:
					event.setDroppedExp(3);
					break;
					
				case SLIME:
					event.setDroppedExp((int)(Math.random() * 2 + 1));
					break;
					
				case BLAZE:
				case EVOKER:
				case GUARDIAN:
					event.setDroppedExp(10);
					break;
					
				case ENDER_DRAGON:
					event.setDroppedExp(500);
					break;
				
				case WITHER:
					event.setDroppedExp(50);
					break;
					
				default:
					break;
			}
		}
	}
	
	@EventHandler
	private void OnRespawn(PlayerRespawnEvent event) {
		if (RoleMethodsFunctions.getRole(event.getPlayer()) != null) {
			Player player = event.getPlayer();
			PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
			
			playerRole.setHealth(healthListener.getHealthMax(player));
			player.setHealth(20);
			ScoreboardMethods.createScoreboard(player);
		}
	}
}
