package fr.sc0rpi0.quantum.classes;

public class Cleric extends PlayerRole {
	public Cleric() {
		super();
		this.setAugmentMagic(2);
		this.setAugmentVit(2);
		this.setAugmentStrength(0.5);
		this.setHealth(28);
	}
	
	public Cleric(int experience, int level, double critical, double augmentCritical, double dexterity, double augmentDexterity, double vit, double augmentVit, double magic, double augmentMagic, double strength, double augmentStrength, double defense, double augmentDefense, int statsPoint, double health, int mana, int ulti) {
		super(experience, level, critical, augmentCritical, dexterity, augmentDexterity, vit, augmentVit, magic, augmentMagic, strength, augmentStrength, defense, augmentDefense, statsPoint, health, mana, ulti);
	}
	
	@Override
	public String getName() {
		return "Cleric";
	}
}
