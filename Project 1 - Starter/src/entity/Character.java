package entity;

import item.*;

public class Character extends Entity {
    private boolean isDefend = false;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private double currentXP = 0;
    private double xpThreshold = 100;
    private int gold = 10;
    private String currentClass;
    public Character(String name, int maxHp, int atk, int mana) {
        super(name, maxHp, atk, mana);
    }
    public void healSelf(int healAmount) {
        this.hp = Math.min(this.hp + healAmount, getMaxHp());
    }
    @Override
    public void attack(Entity target) {
        double calculatedDamage = this.atk;
        if (equippedWeapon != null) {
            calculatedDamage *= equippedWeapon.getDmgMultiplier();
        }
        target.takeDamage(calculatedDamage);
    }
    @Override
    public void takeDamage(double damage) {
        double calculatedDmg = damage;
        if (equippedArmor != null) {
            calculatedDmg *= equippedArmor.getDefMultiplier();
        }
        if (isDefend) {
            calculatedDmg *= 0.8;
        }

        this.hp -= calculatedDmg;
    }
    public void addXp(double xp) {
        this.currentXP += xp;
        while (this.currentXP >= xpThreshold) {
            this.currentXP -= xpThreshold;
            levelUp();
        }
    }

    public void levelUp() {
        restoreHp();
        this.level++;
        this.xpThreshold *= 1.2;
    }
    public String getCurrentClass() {
        return this.currentClass;
    }
    public void setClass(String charClass) {
        if (charClass != null) {
            this.currentClass = charClass;
        }
    }
    public int getGold() {
        return this.gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public void addGold(int gold) {
        this.gold += gold;
    }
    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public boolean isAlive() {
        return this.hp > 0;
    }
    public void setDefend(boolean state) {
        this.isDefend = state;
    }

    public Armor getEquippedArmor() {
        return this.equippedArmor;
    }
    public void setArmorEquipped(Armor armor) {
        if (armor != null) {
            this.equippedArmor = armor;
        }
    }
    public Weapon getEquippedWeapon() {
        return this.equippedWeapon;
    }
    public void setWeaponEquipped(Weapon weapon) {
        if (weapon != null) {
            this.equippedWeapon = weapon;
        }
    }
}