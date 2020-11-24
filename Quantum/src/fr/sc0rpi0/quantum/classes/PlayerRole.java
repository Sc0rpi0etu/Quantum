package fr.sc0rpi0.quantum.classes;

public class PlayerRole {
	private double strength;
	private double magic;
	private double vit;
	private double dexterity;
	private double critical;
	private double defense;
	private double augmentStrength;
	private double augmentMagic;
	private double augmentVit;
	private double augmentDexterity;
	private double augmentCritical;
	private double augmentDefense;
	private int experience;
	private int level;
	private int statsPoint;
	private double health;
	private int mana;
	private int ulti;
	
	public PlayerRole() {
		this.setExperience(0);
		this.setLevel(1);
		this.setCritical(1);
		this.setAugmentCritical(1);
		this.setDexterity(1);
		this.setAugmentDexterity(1);
		this.setVit(1);
		this.setAugmentVit(1);
		this.setMagic(1);
		this.setAugmentMagic(1);
		this.setStrength(1);
		this.setAugmentStrength(1);
		this.setDefense(1);
		this.setAugmentDefense(1);
		this.setStatsPoint(0);
		this.setHealth(24);
		this.setMana(200);
		this.setUlti(0);
	}
	
	public PlayerRole(int experience, int level, double critical, double augmentCritical, double dexterity, double augmentDexterity, double vit, double augmentVit, double magic, double augmentMagic, double strength, double augmentStrength, double defense, double augmentDefense, int statsPoint, double health, int mana, int ulti) {
		this.setExperience(experience);
		this.setLevel(level);
		this.setCritical(critical);
		this.setAugmentCritical(augmentCritical);
		this.setDexterity(dexterity);
		this.setAugmentDexterity(augmentDexterity);
		this.setVit(vit);
		this.setAugmentVit(augmentVit);
		this.setMagic(magic);
		this.setAugmentMagic(augmentMagic);
		this.setStrength(strength);
		this.setAugmentStrength(augmentStrength);
		this.setDefense(defense);
		this.setAugmentDefense(augmentDefense);
		this.setStatsPoint(statsPoint);
		this.setHealth(health);
		this.setMana(mana);
		this.setUlti(ulti);
	}
	
	public int getExperienceBar() {
		return (int) Math.round(4 * (Math.pow(this.getLevel(), 3)) / 5);
	}
	
	public double getStrength() {
		return strength;
	}
	
	public void setStrength(double strength) {
		this.strength = strength;
	}
	
	public double getHealth() {
		return health;
	}
	
	public void setHealth(double health) {
		this.health = health;
	}
	
	public double getVit() {
		return vit;
	}
	
	public void setVit(double vit) {
		this.vit = vit;
	}
	
	public double getMagic() {
		return magic;
	}
	
	public void setMagic(double magic) {
		this.magic = magic;
	}
	
	public double getDexterity() {
		return dexterity;
	}
	
	public void setDexterity(double dexterity) {
		this.dexterity = dexterity;
	}
	
	public double getCritical() {
		return critical;
	}
	
	public void setCritical(double critical) {
		this.critical = critical;
	}
	
	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public double getAugmentStrength() {
		return augmentStrength;
	}

	public void setAugmentStrength(double augmentStrength) {
		this.augmentStrength = augmentStrength;
	}

	public double getAugmentMagic() {
		return augmentMagic;
	}

	public void setAugmentMagic(double augmentMagic) {
		this.augmentMagic = augmentMagic;
	}

	public double getAugmentVit() {
		return augmentVit;
	}

	public void setAugmentVit(double augmentVit) {
		this.augmentVit = augmentVit;
	}

	public double getAugmentDexterity() {
		return augmentDexterity;
	}

	public void setAugmentDexterity(double augmentDexterity) {
		this.augmentDexterity = augmentDexterity;
	}

	public double getAugmentCritical() {
		return augmentCritical;
	}

	public void setAugmentCritical(double augmentCritical) {
		this.augmentCritical = augmentCritical;
	}

	public double getLife() {
		return this.getVit() * 10 + 40;
	}

	public double getDefense() {
		return defense;
	}

	public void setDefense(double defense) {
		this.defense = defense;
	}

	public double getAugmentDefense() {
		return augmentDefense;
	}

	public void setAugmentDefense(double augmentDefense) {
		this.augmentDefense = augmentDefense;
	}
	
	public String ToString() {
		return "STR : " + this.getStrength() + "\nINT : " + this.getMagic() + "\nVIT : " + this.getVit() + "\nDEF : " + this.getDefense() + "\nDEX : " + this.getDexterity() + "\nCRIT : " + this.getCritical() + "\nExperience : " + this.getExperience() + " / " + this.getExperienceBar() + "\nStats points : " + this.getStatsPoint();
	}
	
	public String getName() {
		return "PlayerRole";
	}

	public int getStatsPoint() {
		return statsPoint;
	}

	public void setStatsPoint(int statsPoint) {
		this.statsPoint = statsPoint;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getUlti() {
		return ulti;
	}

	public void setUlti(int ulti) {
		this.ulti = ulti;
	}
}
