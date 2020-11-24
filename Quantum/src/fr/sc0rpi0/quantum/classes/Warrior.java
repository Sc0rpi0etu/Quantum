package fr.sc0rpi0.quantum.classes;


public class Warrior extends PlayerRole {
	private int ultiTime;
	
	public Warrior() {
		super();
		this.setAugmentStrength(3);
		this.setAugmentVit(0.5);
		this.setUltiTime(0);
	}
	
	public Warrior(int experience, int level, double critical, double augmentCritical, double dexterity, double augmentDexterity, double vit, double augmentVit, double magic, double augmentMagic, double strength, double augmentStrength, double defense, double augmentDefense, int statsPoint, double health, int mana, int ulti, int ultiTume) {
		super(experience, level, critical, augmentCritical, dexterity, augmentDexterity, vit, augmentVit, magic, augmentMagic, strength, augmentStrength, defense, augmentDefense, statsPoint, health, mana, ulti);
		this.setUltiTime(ultiTime);
	}
	
	public Warrior(PlayerRole playerRole, int ultiTime) {
		super(playerRole.getExperience(), playerRole.getLevel(), playerRole.getCritical(), playerRole.getAugmentCritical(), playerRole.getDexterity(), playerRole.getAugmentDexterity(), playerRole.getVit(), playerRole.getAugmentVit(), playerRole.getMagic(), playerRole.getAugmentMagic(), playerRole.getStrength(), playerRole.getAugmentStrength(), playerRole.getDefense(), playerRole.getAugmentDefense(), playerRole.getStatsPoint(), playerRole.getHealth(), playerRole.getMana(), playerRole.getUlti());
		this.setUltiTime(ultiTime);
	}
	
	@Override
	public String getName() {
		return "Warrior";
	}

	public int getUltiTime() {
		return ultiTime;
	}

	public void setUltiTime(int ultiTime) {
		this.ultiTime = ultiTime;
	}
}
