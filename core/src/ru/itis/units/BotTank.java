package ru.itis.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.itis.TankGame;
import ru.itis.Weapon;
import ru.itis.utils.Direction;

public class BotTank extends Tank {
    Direction preferredDirection;
    float aiTimer;
    float aiTimerTo;
    boolean active;

    public BotTank(TankGame game, TextureAtlas atlas) {
        super(game);
        this.weapon = new Weapon();
        this.texture = atlas.findRegion("botTank40");
        this.hpBarTexture = atlas.findRegion("bar");
        this.position = new Vector2(100.f, 100.f);
        this.speed = 100;
        this.width = texture.getRegionWidth();
        this.height = texture.getRegionHeight();
        this.hpMax = 10;
        this.hp = hpMax;
        this.preferredDirection = Direction.UP;
        this.hitBox = new Circle(position.x, position.y, (width + height)/2);
    }

    @Override
    public void destroy() {
        active = false;
    }

    @Override
    public void update(float dt) {
        aiTimer += dt;
        if (aiTimer >= aiTimerTo) {
            aiTimer = 0.f;
            aiTimerTo = MathUtils.random(2.5f, 4.f);
            preferredDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
            angle = preferredDirection.getAngle();
        }
        position.add(speed * preferredDirection.getVx() * dt, speed * preferredDirection.getVy() * dt);
        super.update(dt);
    }

    public void activate(float x, float y) {
        hpMax = 5;
        hp = hpMax;
        preferredDirection = Direction.values()[MathUtils.random(0, Direction.values().length - 1)];
        angle = preferredDirection.getAngle();
        position.set(x, y);
        aiTimer = 0.f;
        active = true;

    }

    public boolean isActive() {
        return active;
    }
}
