package ru.itis.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import ru.itis.TankGame;
import ru.itis.Weapon;
import ru.itis.units.Tank;

public class PlayerTank extends Tank {
    int lives;

    public PlayerTank(TankGame game, TextureAtlas atlas) {
        super(game);
        this.weapon = new Weapon();
        this.texture = atlas.findRegion("playerTank40");
        this.hpBarTexture = atlas.findRegion("bar");
        this.position = new Vector2(100.f, 100.f);
        this.speed = 100;
        this.width = texture.getRegionWidth();
        this.height = texture.getRegionHeight();
        this.hpMax = 10;
        this.hp = hpMax - 2;
        this.hitBox = new Circle(position.x, position.y, (width + height)/2);
        this.lives = 3;
    }

    @Override
    public void destroy() {
        lives--;
        hp = hpMax;
    }

    @Override
    public void update(float dt) {
        checkMovement(dt);
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fire(dt);
        }
        super.update(dt);
    }

    public void checkMovement(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= speed * dt;
            angle = 180.f;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += speed * dt;
            angle = 0.f;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += speed * dt;
            angle = 90.f;
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= speed * dt;
            angle = 270.f;
            return;
        }
    }
}
