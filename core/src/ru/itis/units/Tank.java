package ru.itis.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import ru.itis.TankGame;
import ru.itis.Weapon;

public abstract class Tank {
    TankGame game;
    Weapon weapon;
    TextureRegion texture;
    TextureRegion hpBarTexture;
    Vector2 position;
    float speed;
    float angle;
    int width;
    int height;
    float fireTimer;
    int hp;
    int hpMax;
    Circle hitBox;

    public Tank(TankGame game) {
        this.game = game;
    }

    public abstract void destroy();

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - width / 2, position.y - height / 2, width / 2, height / 2, width, height, 1, 1, angle);
        if (hp < hpMax) {
            batch.setColor(0, 0, 0, 1);
            batch.draw(hpBarTexture, position.x - width / 2 - 2, position.y + height / 2 - 4, 44, 12);
            batch.setColor(1, 0, 0, 1);
            batch.draw(hpBarTexture, position.x - width / 2, position.y + height / 2 - 2, ((float) hp / hpMax) * 40, 8);
            batch.setColor(1, 1, 1, 1);
        }
    }

    public void update(float dt) {
        fireTimer += dt;
        if (position.x < 0.f) {
            position.x = 0.f;
        }
        if (position.x > Gdx.graphics.getWidth()) {
            position.x = Gdx.graphics.getWidth();
        }
        if (position.y < 0.f) {
            position.y = 0.f;
        }
        if (position.y > Gdx.graphics.getHeight()) {
            position.y = Gdx.graphics.getHeight();
        }
        hitBox.setPosition(position);
    }


    public void fire(float dt) {
        if (fireTimer >= weapon.getFirePeriod()) {
            fireTimer = 0.f;
            float angleRad = (float) Math.toRadians(angle);
            game.getBulletEmitter().activate(position.x, position.y, 320.f * (float) Math.cos(angleRad), 320.f * (float) Math.sin(angleRad));
        }
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            destroy();
        }
    }

    public Circle getHitBox() {
        return hitBox;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
