package fr.sc0rpi0.quantum.methodsfunctions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.sc0rpi0.quantum.classes.PlayerRole;
import net.md_5.bungee.api.ChatColor;

public class ScoreboardMethods {
	public static void createScoreboard(Player player) {
		PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		@SuppressWarnings("deprecation")
		Objective objective = board.registerNewObjective("Stats", "dummy");
		
		objective.setDisplayName(ChatColor.RED + "Stats");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		String manaName = "Stamina";
		if (playerRole.getName().equalsIgnoreCase("Cleric") || playerRole.getName().equalsIgnoreCase("Wizard")) {
			manaName = "Mana";
		}
		
		String roleString = "Role: " + playerRole.getName();
		String healthString = ChatColor.DARK_RED + "HP: " + Math.round(playerRole.getHealth()) + " / " + getHealthMax(player);
		String experienceString = ChatColor.GREEN + "EXP: " + playerRole.getExperience() + " / " + playerRole.getExperienceBar();
		String strengthString = ChatColor.RED + "STR: " + playerRole.getStrength();
		String magicString = ChatColor.DARK_PURPLE + "INT: " + playerRole.getMagic();
		String vitString = ChatColor.DARK_GREEN + "VIT: " + playerRole.getVit();
		String defenseString = ChatColor.GRAY + "DEF: " + playerRole.getDefense();
		String dexterityString = ChatColor.BLUE + "DEX: " + playerRole.getDexterity();
		String criticalString = ChatColor.YELLOW + "CRIT: " + playerRole.getCritical();
		String manaString = ChatColor.AQUA + manaName + ": " + playerRole.getMana();
		String statsPointsString = ChatColor.DARK_GRAY + "Stats Points: " + playerRole.getStatsPoint();
		
		Score role = objective.getScore(roleString);
		Score health = objective.getScore(healthString);
		Score experience = objective.getScore(experienceString);
		Score strength = objective.getScore(strengthString);
		Score magic = objective.getScore(magicString);
		Score vit = objective.getScore(vitString);
		Score defense = objective.getScore(defenseString);
		Score dexterity = objective.getScore(dexterityString);
		Score critical = objective.getScore(criticalString);
		Score mana = objective.getScore(manaString);
		Score statsPoints = objective.getScore(statsPointsString);
		
		role.setScore(8);
		health.setScore(7);
		experience.setScore(6);
		strength.setScore(5);
		magic.setScore(4);
		vit.setScore(3);
		defense.setScore(2);
		dexterity.setScore(1);
		critical.setScore(0);
		mana.setScore(-1);
		statsPoints.setScore(-2);
		
		player.setScoreboard(board);
	}
	
	public static Scoreboard setScoreboardBelowName(Player player) {
		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
		
		@SuppressWarnings("deprecation")
		Objective objective = scoreboard.registerNewObjective("Health", "dummy");
		
		objective.setDisplayName("Health");
		objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
		
		String health = "";
		if (RoleMethodsFunctions.getRole(player) != null) {
			health += (int) (RoleMethodsFunctions.getRole(player).getHealth());
			
		} else {
			health += (int) player.getHealth();
		}
		
		health += " ♥";
		
		Score healthScore = objective.getScore(health);
		
		healthScore.setScore(1);
		
		return scoreboard;
	}
	
	public static int getHealthMax(Player player) {
		PlayerRole playerRole = RoleMethodsFunctions.getRole(player);
		
		double variante = 2;
		
		if (playerRole.getName().equalsIgnoreCase("Tank")) {
			
		} else if (playerRole.getName().equalsIgnoreCase("Cleric")) {
			variante = variante * 2 / 3;
			
		} else {
			variante = variante / 6;
		}
		
		return (int) (variante * 20 * playerRole.getVit());
	}
	
	@SuppressWarnings("deprecation")
	public static void setUltiTime(Player player) {
		player.sendTitle("\n\n\n\n", "Time");
	}
}
