package fr.sc0rpi0.quantum.classes;

public class Admin extends PlayerRole {
	public Admin() {
		this.setCritical(2000);
		this.setDefense(2000);
		this.setDexterity(2000);
		this.setLevel(2000);
		this.setHealth(13333);
		this.setMagic(2000);
		this.setMana(2000);
		this.setStrength(2000);
		this.setVit(2000);
	}
	
	public Admin(int experience, int level, double critical, double augmentCritical, double dexterity, double augmentDexterity, double vit, double augmentVit, double magic, double augmentMagic, double strength, double augmentStrength, double defense, double augmentDefense, int statsPoint, double health, int mana, int ulti) {
		super(experience, level, critical, augmentCritical, dexterity, augmentDexterity, vit, augmentVit, magic, augmentMagic, strength, augmentStrength, defense, augmentDefense, statsPoint, health, mana, ulti);
	}
	
	@Override
	public String getName() {
		return "Admin";
	}
}
