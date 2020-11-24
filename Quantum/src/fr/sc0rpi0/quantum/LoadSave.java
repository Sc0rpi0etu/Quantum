package fr.sc0rpi0.quantum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.permissions.PermissionAttachment;

import fr.sc0rpi0.quantum.classes.Admin;
import fr.sc0rpi0.quantum.classes.Archer;
import fr.sc0rpi0.quantum.classes.Cleric;
import fr.sc0rpi0.quantum.classes.Assassin;
import fr.sc0rpi0.quantum.classes.Tank;
import fr.sc0rpi0.quantum.classes.Warrior;
import fr.sc0rpi0.quantum.classes.Wizard;
import fr.sc0rpi0.quantum.methodsfunctions.RoleMethodsFunctions;

public class LoadSave {
	public List<String> stringPermission = new ArrayList<String>();
	public Hashtable<UUID, PermissionAttachment> playerPermission = new Hashtable<UUID, PermissionAttachment>();
	
	private BufferedReader br;
	
	public void Load() {
		try {
			File file = new File("Roles.txt");
			br = new BufferedReader(new FileReader(file));
			String line;
			int i = 0;
			String player = null;
			String role = null;
			double strength = 0;
			double augmentStrength = 0;
			double magic = 0;
			double augmentMagic = 0;
			double vit = 0;
			double augmentVit = 0;
			double defense = 0;
			double augmentDefense = 0;
			double dexterity = 0;
			double augmentDexterity = 0;
			double critical = 0;
			double augmentCritical = 0;
			int level = 0;
			int experience = 0;
			double health = 0;
			int statsPoint = 0;
			int mana = 0;
			int ulti = 0;
			int ultiTime = 0;
			
			while ((line = br.readLine()) != null) {
				i++;
				switch (i%20) {
					case 1:
						player = line;
						break;
				
					case 2:
						role = line;
						break;
						
					case 3:
						strength = Double.parseDouble(line);
						break;
					
					case 4:
						augmentStrength = Double.parseDouble(line);
						break;
					
					case 5:
						magic = Double.parseDouble(line);
						break;
					
					case 6:
						augmentMagic = Double.parseDouble(line);
						break;
						
					case 7:
						vit = Double.parseDouble(line);
						break;
					
					case 8:
						augmentVit = Double.parseDouble(line);
						break;
						
					case 9:
						defense = Double.parseDouble(line);
						break;
					
					case 10:
						augmentDefense = Double.parseDouble(line);
						break;
						
					case 11:
						dexterity = Double.parseDouble(line);
						break;
					
					case 12:
						augmentDexterity = Double.parseDouble(line);
						break;
						
					case 13:
						critical = Double.parseDouble(line);
						break;
					
					case 14:
						augmentCritical = Double.parseDouble(line);
						break;
						
					case 15:
						level = Integer.parseInt(line);
						break;
						
					case 16:
						experience = Integer.parseInt(line);
						break;
					
					case 17:
						statsPoint = Integer.parseInt(line);
						break;
						
					case 18:
						health = Double.parseDouble(line);
						break;
					
					case 19:
						ulti = Integer.parseInt(line);
						break;
					
					case 0:
						mana = Integer.parseInt(line);
						switch (role) {
							case "Warrior":
								line = br.readLine();
								ultiTime = Integer.parseInt(line);
								RoleMethodsFunctions.setRole(player, new Warrior(experience, level, critical, augmentCritical, dexterity, augmentDexterity, vit, augmentVit, magic, augmentMagic, strength, augmentStrength, defense, augmentDefense, statsPoint, health, mana, ulti, ultiTime));
								break;
								
							case "Archer":
								line = br.readLine();
								ultiTime = Integer.parseInt(line);
								RoleMethodsFunctions.setRole(player, new Archer(experience, level, critical, augmentCritical, dexterity, augmentDexterity, vit, augmentVit, magic, augmentMagic, strength, augmentStrength, defense, augmentDefense, statsPoint, health, mana, ulti, ultiTime));
								break;
								
							case "Cleric":
								RoleMethodsFunctions.setRole(player, new Cleric(experience, level, critical, augmentCritical, dexterity, augmentDexterity, vit, augmentVit, magic, augmentMagic, strength, augmentStrength, defense, augmentDefense, statsPoint, health, mana, ulti));
								break;
								
							case "Murderer":
								RoleMethodsFunctions.setRole(player, new Assassin(experience, level, critical, augmentCritical, dexterity, augmentDexterity, vit, augmentVit, magic, augmentMagic, strength, augmentStrength, defense, augmentDefense, statsPoint, health, mana, ulti));
								break;
								
							case "Tank":
								line = br.readLine();
								ultiTime = Integer.parseInt(line);
								RoleMethodsFunctions.setRole(player, new Tank(experience, level, critical, augmentCritical, dexterity, augmentDexterity, vit, augmentVit, magic, augmentMagic, strength, augmentStrength, defense, augmentDefense, statsPoint, health, mana, ulti, ultiTime));
								break;
								
							case "Wizard":
								RoleMethodsFunctions.setRole(player, new Wizard(experience, level, critical, augmentCritical, dexterity, augmentDexterity, vit, augmentVit, magic, augmentMagic, strength, augmentStrength, defense, augmentDefense, statsPoint, health, mana, ulti));
								break;
							
							case "Admin":
								RoleMethodsFunctions.setRole(player, new Admin());
						}
						break;
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void Save() {
		String save = "";
		Set<String> keys = RoleMethodsFunctions.playerAndRole.keySet();
		Iterator<String> itr = keys.iterator();
		
		String key;
		
		while(itr.hasNext()) {
			key = itr.next();
			
			save += key + "\n";
			save += RoleMethodsFunctions.getRole(key).getName() + "\n";
			save += RoleMethodsFunctions.getRole(key).getStrength() + "\n";
			save += RoleMethodsFunctions.getRole(key).getAugmentStrength() + "\n";
			save += RoleMethodsFunctions.getRole(key).getMagic() + "\n";
			save += RoleMethodsFunctions.getRole(key).getAugmentMagic() + "\n";
			save += RoleMethodsFunctions.getRole(key).getVit() + "\n";
			save += RoleMethodsFunctions.getRole(key).getAugmentVit() + "\n";
			save += RoleMethodsFunctions.getRole(key).getDefense() + "\n";
			save += RoleMethodsFunctions.getRole(key).getAugmentDefense() + "\n";
			save += RoleMethodsFunctions.getRole(key).getDexterity() + "\n";
			save += RoleMethodsFunctions.getRole(key).getAugmentDexterity() + "\n";
			save += RoleMethodsFunctions.getRole(key).getCritical() + "\n";
			save += RoleMethodsFunctions.getRole(key).getAugmentCritical() + "\n";
			save += RoleMethodsFunctions.getRole(key).getLevel() + "\n";
			save += RoleMethodsFunctions.getRole(key).getExperience() + "\n";
			save += RoleMethodsFunctions.getRole(key).getStatsPoint() + "\n";
			save += RoleMethodsFunctions.getRole(key).getHealth() + "\n";
			save += RoleMethodsFunctions.getRole(key).getMana() + "\n";
			save += RoleMethodsFunctions.getRole(key).getUlti() + "\n";
			if (RoleMethodsFunctions.getRole(key).getName().equalsIgnoreCase("Archer")) {
				save += RoleMethodsFunctions.getArcher(key).getUltiTime() + "\n";
				
			} else if (RoleMethodsFunctions.getRole(key).getName().equalsIgnoreCase("Warrior")) {
				save += RoleMethodsFunctions.getWarrior(key).getUltiTime() + "\n";
				
			} else if (RoleMethodsFunctions.getRole(key).getName().equalsIgnoreCase("Tank")) {
				save += RoleMethodsFunctions.getTank(key).getUltiTime() + "\n";
			}
		}
		
		try {
			FileWriter fw = new FileWriter("Roles.txt");
			fw.write(save);
			fw.close();
		} catch ( IOException ex) {
			ex. printStackTrace ();
		}
	}
	
	public void LoadPermission() {
		System.out.print("ok");
		try {
			File file = new File("Permission.txt");
			br = new BufferedReader(new FileReader(file));
			String line;
			
			while ((line = br.readLine()) != null) {
				setUuidPermission(line);
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public List<String> getUuidPermission() {
		return stringPermission;
	}

	public void setUuidPermission(String uuid) {
		this.stringPermission.add(uuid);
	}
}
