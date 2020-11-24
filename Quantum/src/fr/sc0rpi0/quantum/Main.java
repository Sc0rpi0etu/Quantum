package fr.sc0rpi0.quantum;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import fr.sc0rpi0.quantum.commands.AdminCommand;
import fr.sc0rpi0.quantum.commands.Commands;
import fr.sc0rpi0.quantum.listeners.BlockIgniteListener;
import fr.sc0rpi0.quantum.listeners.CraftListener;
import fr.sc0rpi0.quantum.listeners.DamageListener;
import fr.sc0rpi0.quantum.listeners.DeathListener;
import fr.sc0rpi0.quantum.listeners.ExperienceListener;
import fr.sc0rpi0.quantum.listeners.HealthListener;
import fr.sc0rpi0.quantum.listeners.InteractListener;
import fr.sc0rpi0.quantum.listeners.JoinQuitListener;
import fr.sc0rpi0.quantum.listeners.TargetListener;
import fr.sc0rpi0.quantum.methodsfunctions.AuraMethodsFunctions;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;
import fr.sc0rpi0.quantum.run.ArcherUltiRun;
import fr.sc0rpi0.quantum.run.AuraNoEffect;
import fr.sc0rpi0.quantum.run.AuraRegeneration;
import fr.sc0rpi0.quantum.run.AuraStrength;
import fr.sc0rpi0.quantum.run.ManaRegeneration;
import fr.sc0rpi0.quantum.run.ShowHealthRun;
import fr.sc0rpi0.quantum.run.TankUltiRun;
import fr.sc0rpi0.quantum.run.UltiRun;
import fr.sc0rpi0.quantum.run.WarriorUltiRun;

public class Main extends JavaPlugin {
	public LoadSave loadSave = new LoadSave();
	public CustomItems customItems = new CustomItems(this);
	
	public CraftListener craftListener = new CraftListener(customItems);
	public JoinQuitListener joinQuitListener = new JoinQuitListener(this);
	public HealthListener healthListener = new HealthListener();
	public TargetListener targetListener = new TargetListener();
	public DeathListener deathListener = new DeathListener(healthListener, targetListener);
	public ExperienceListener experienceListener = new ExperienceListener();
	public InteractListener interactListener = new InteractListener(this);
	public BlockIgniteListener blockIgniteListener = new BlockIgniteListener();
	public DamageListener damageListener = new DamageListener(this, healthListener, targetListener);
	
	@Override
	public void onEnable() {
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		loadSave.Load();
		loadSave.LoadPermission();
		getCommand("q").setExecutor(new Commands(healthListener, customItems));
		getCommand("qadmin").setExecutor(new AdminCommand(joinQuitListener, healthListener));
		
		customItems.AddRecipes();
		
		getServer().getPluginManager().registerEvents(damageListener, this);
		getServer().getPluginManager().registerEvents(deathListener, this);
		getServer().getPluginManager().registerEvents(experienceListener, this);
		getServer().getPluginManager().registerEvents(healthListener, this);
		getServer().getPluginManager().registerEvents(interactListener, this);
		getServer().getPluginManager().registerEvents(joinQuitListener, this);
		getServer().getPluginManager().registerEvents(targetListener, this);
		getServer().getPluginManager().registerEvents(blockIgniteListener, this);
		getServer().getPluginManager().registerEvents(craftListener, this);
		
		ManaRegeneration mana = new ManaRegeneration(joinQuitListener);
		mana.runTaskTimer(this, 20, 20);
		
		AuraStrength auraStrength = new AuraStrength();
		auraStrength.runTaskTimer(this, 20, 20);
		
		AuraRegeneration auraRegeneration = new AuraRegeneration();
		auraRegeneration.runTaskTimer(this, 20, 20);
		
		AuraNoEffect auraNoEffect = new AuraNoEffect();
		auraNoEffect.runTaskTimer(this, 20, 20);
		
		UltiRun ultiRun = new UltiRun(joinQuitListener, customItems);
		ultiRun.runTaskTimer(this, 20, 20);
		
		ArcherUltiRun archerUltiRun = new ArcherUltiRun(joinQuitListener);
		archerUltiRun.runTaskTimer(this, 20, 20);
		
		TankUltiRun tankUltiRun = new TankUltiRun(joinQuitListener);
		tankUltiRun.runTaskTimer(this, 20, 20);
		
		WarriorUltiRun warriorUltiRun = new WarriorUltiRun(joinQuitListener);
		warriorUltiRun.runTaskTimer(this, 20, 20);
		
		ShowHealthRun showhealthRun = new ShowHealthRun();
		showhealthRun.runTaskTimerAsynchronously(this, 20, 20);
	}
	
	@Override
	public void onDisable() {
		loadSave.Save();
		damageListener.damageByIceballHashtable.clear();
		targetListener.targetMonsterList.clear();
		targetListener.targetPlayerList.clear();
		AuraMethodsFunctions.auraExplosionLocation.clear();
		AuraMethodsFunctions.auraExplosionPlayer.clear();
		AuraMethodsFunctions.auraNoEffectLocation.clear();
		AuraMethodsFunctions.auraRegenerationLocation.clear();
		AuraMethodsFunctions.auraRegenerationPlayer.clear();
		AuraMethodsFunctions.auraResistanceLocation.clear();
		AuraMethodsFunctions.auraResistancePlayer.clear();
		AuraMethodsFunctions.auraStrengthLocation.clear();
		AuraMethodsFunctions.auraStrengthPlayer.clear();
		RoleMethodsFunctions.playerAndRoleByName.clear();
		RoleMethodsFunctions.playerAndRole.clear();
		loadSave.playerPermission.clear();
		loadSave.stringPermission.clear();
		joinQuitListener.playerConnected.clear();
	}
	
	public void permissionsSetter(Player player) {
		if (getPermission(player.getName()) && !player.hasPermission("quantum.admin")) {
			PermissionAttachment attachment = player.addAttachment(this);
			loadSave.playerPermission.put(player.getUniqueId(), attachment);
			
			for (String groups : this.getConfig().getConfigurationSection("Admin").getKeys(false)) {
				for (String permissions : this.getConfig().getStringList("Admin." + groups + ".permissions")) {
					attachment.setPermission(permissions, true);
				}
			}
		}
	}
	
	private boolean getPermission(String playerName) {
		for (String name : loadSave.getUuidPermission()) {
			if (name.equalsIgnoreCase(playerName)) {
				return true;
			}
		}
		return false;
	}
}
