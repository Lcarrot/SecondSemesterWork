package ru.itis.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import ru.itis.TankGame;
import ru.itis.Weapon;
import ru.itis.utils.TankOwner;

public abstract class Tank {
    TankGame game;
    TankOwner ownerType;
    Weapon weapon;
    TextureRegion texture;
    TextureRegion textureHp;
    Vector2 position;
    Circle hitBox;
    int hp;
    int hpMax;
    float speed;
    float angle;
    float fireTimer;
    int width;
    int height;

    public Vector2 getPosition() {
        return position;
    }

    public TankOwner getOwnerType() {
        return ownerType;
    }

    public Circle getHitBox() {
        return hitBox;
    }

    public Tank(TankGame game) {
        this.game = game;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - width / 2, position.y - height / 2, width / 2, height / 2, width, height, 1, 1, angle);
        if (hp < hpMax) {
            batch.setColor(0, 0, 0, 1);
            batch.draw(textureHp, position.x - width / 2 - 2, position.y + height / 2 - 10, 44, 12);
            batch.setColor(1, 0, 0, 1);
            batch.draw(textureHp, position.x - width / 2, position.y + height / 2 - 8, ((float) hp / hpMax) * 40, 8);
            batch.setColor(1, 1, 1, 1);
        }
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            destroy();
        }
    }

    public abstract void destroy();

    public void update(float dt) {
        fireTimer += dt;
        if (position.x < 0.0f) {
            position.x = 0.0f;
        }
        if (position.x > Gdx.graphics.getWidth()) {
            position.x = Gdx.graphics.getWidth();
        }
        if (position.y < 0.0f) {
            position.y = 0.0f;
        }
        if (position.y > Gdx.graphics.getHeight()) {
            position.y = Gdx.graphics.getHeight();
        }
        hitBox.setPosition(position);
    }

    public void fire() {
        if (fireTimer >= weapon.getFirePeriod()) {
            fireTimer = 0.0f;
            float angleRad = (float) Math.toRadians(angle);
            game.getBulletEmitter().activate(this, position.x, position.y, 320.0f * (float) Math.cos(angleRad), 320.0f * (float) Math.sin(angleRad), weapon.getDamage());
        }
    }
}
