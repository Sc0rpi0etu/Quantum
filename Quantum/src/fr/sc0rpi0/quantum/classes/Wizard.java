package fr.sc0rpi0.quantum.classes;

public class Wizard extends PlayerRole {
	public Wizard() {
		super();
		this.setAugmentMagic(3);
		this.setAugmentVit(0.5);
		this.setMana(201);
	}
	
	public Wizard(int experience, int level, double critical, double augmentCritical, double dexterity, double augmentDexterity, double vit, double augmentVit, double magic, double augmentMagic, double strength, double augmentStrength, double defense, double augmentDefense, int statsPoint, double health, int mana, int ulti) {
		super(experience, level, critical, augmentCritical, dexterity, augmentDexterity, vit, augmentVit, magic, augmentMagic, strength, augmentStrength, defense, augmentDefense, statsPoint, health, mana, ulti);
	}
	
	@Override
	public String getName() {
		return "Wizard";
	}
}
