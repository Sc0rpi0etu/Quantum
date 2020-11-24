package fr.sc0rpi0.quantum.run;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.sc0rpi0.quantum.listeners.JoinQuitListener;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;

public class ArcherUltiRun extends BukkitRunnable {
	private JoinQuitListener joinQuitListener;
	
	public ArcherUltiRun(JoinQuitListener joinQuitListener) {
		this.joinQuitListener = joinQuitListener;
	}

	@Override
	public void run() {
		for (Player player : joinQuitListener.playerConnected) {
			if (RoleMethodsFunctions.getArcher(player.getUniqueId().toString()) != null) {
				if (RoleMethodsFunctions.getArcher(player.getUniqueId().toString()).getUltiTime() > 0) {
					RoleMethodsFunctions.getArcher(player.getUniqueId().toString()).setUltiTime(RoleMethodsFunctions.getArcher(player.getUniqueId().toString()).getUltiTime() - 1);
				}
			}
		}
	}

}
