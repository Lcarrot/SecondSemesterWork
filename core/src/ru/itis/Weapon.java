package ru.itis;

public class Weapon {
    private float firePeriod;
    private int damage;

    public float getFirePeriod() {
        return firePeriod;
    }

    public int getDamage() {
        return damage;
    }

    public Weapon() {
        this.firePeriod = 0.5f;
        this.damage = 1;
    }
}
