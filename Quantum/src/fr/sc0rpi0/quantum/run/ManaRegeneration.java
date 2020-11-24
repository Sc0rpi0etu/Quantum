package fr.sc0rpi0.quantum.run;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.sc0rpi0.quantum.classes.PlayerRole;
import fr.sc0rpi0.quantum.listeners.JoinQuitListener;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;
import fr.sc0rpi0.quantum.methodsfunctions.ScoreboardMethods;

public class ManaRegeneration extends BukkitRunnable {
	private JoinQuitListener joinQuitListener;
	
	public ManaRegeneration(JoinQuitListener joinQuitListener) {
		this.joinQuitListener = joinQuitListener;
	}
	
	@Override
	public void run() {
		for (Player player : joinQuitListener.playerConnected) {
			if (RoleMethodsFunctions.getRole(player) != null && RoleMethodsFunctions.getRole(player).getMana() < getManaMax(player)) {
				addMana(player);
				ScoreboardMethods.createScoreboard(player);
			}
		}
	}
	
	public void addMana(Player player) {
		PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
		
		if (playerRole.getMana() + playerRole.getMagic() * 0.5 + 1 <= getManaMax(player)) {
			playerRole.setMana((int) (playerRole.getMana() + playerRole.getMagic() * 0.5 + 1));
		} else {
			playerRole.setMana(getManaMax(player));
		}
	}
	
	public int getManaMax(Player player) {
		PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
		
		double variante = 1;
		
		if (playerRole.getName().equalsIgnoreCase("Wizard") || playerRole.getName().equalsIgnoreCase("admin")) {
			
		} else if (playerRole.getName().equalsIgnoreCase("Cleric")) {
			variante = variante * 2 / 3;
		} else {
			variante = variante / 3;
		}
		
		return (int) (variante * playerRole.getMagic() + 200);
	}
}
