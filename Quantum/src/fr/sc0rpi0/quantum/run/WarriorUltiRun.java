package fr.sc0rpi0.quantum.run;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.sc0rpi0.quantum.listeners.JoinQuitListener;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;

public class WarriorUltiRun extends BukkitRunnable {
	private JoinQuitListener joinQuitListener;
	
	public WarriorUltiRun(JoinQuitListener joinQuitListener) {
		this.joinQuitListener = joinQuitListener;
	}

	@Override
	public void run() {
		for (Player player : joinQuitListener.playerConnected) {
			if (RoleMethodsFunctions.getWarrior(player.getUniqueId().toString()) != null) {
				if (RoleMethodsFunctions.getWarrior(player.getUniqueId().toString()).getUltiTime() > 0) {
					RoleMethodsFunctions.getWarrior(player.getUniqueId().toString()).setUltiTime(RoleMethodsFunctions.getWarrior(player.getUniqueId().toString()).getUltiTime() - 1);
				}
			}
		}
	}
}
