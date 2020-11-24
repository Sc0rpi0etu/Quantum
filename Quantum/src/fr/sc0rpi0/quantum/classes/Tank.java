package fr.sc0rpi0.quantum.classes;

public class Tank extends PlayerRole {
	private int ultiTime;
	
	public Tank() {
		super();
		this.setAugmentVit(2);
		this.setAugmentDefense(2);
		this.setAugmentStrength(0.5);
		this.setHealth(28);
		this.setUltiTime(0);
	}
	
	public Tank(int experience, int level, double critical, double augmentCritical, double dexterity, double augmentDexterity, double vit, double augmentVit, double magic, double augmentMagic, double strength, double augmentStrength, double defense, double augmentDefense, int statsPoint, double health, int mana, int ulti, int ultiTime) {
		super(experience, level, critical, augmentCritical, dexterity, augmentDexterity, vit, augmentVit, magic, augmentMagic, strength, augmentStrength, defense, augmentDefense, statsPoint, health, mana, ulti);
		this.setUltiTime(ultiTime);
	}
	
	public Tank(PlayerRole playerRole, int ultiTime) {
		super(playerRole.getExperience(), playerRole.getLevel(), playerRole.getCritical(), playerRole.getAugmentCritical(), playerRole.getDexterity(), playerRole.getAugmentDexterity(), playerRole.getVit(), playerRole.getAugmentVit(), playerRole.getMagic(), playerRole.getAugmentMagic(), playerRole.getStrength(), playerRole.getAugmentStrength(), playerRole.getDefense(), playerRole.getAugmentDefense(), playerRole.getStatsPoint(), playerRole.getHealth(), playerRole.getMana(), playerRole.getUlti());
		this.setUltiTime(ultiTime);
	}
	
	@Override
	public String getName() {
		return "Tank";
	}

	public int getUltiTime() {
		return ultiTime;
	}

	public void setUltiTime(int ultiTime) {
		this.ultiTime = ultiTime;
	}
}
