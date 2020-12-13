package ru.itis;

import com.badlogic.gdx.graphics.Texture;

public class Weapon {
    private float firePeriod;
    private int damage;

    public Weapon() {
        this.firePeriod = 0.5f;
        this.damage = 1;
    }

    public float getFirePeriod() {
        return firePeriod;
    }

    public int getDamage() {
        return damage;
    }
}
