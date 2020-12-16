package ru.itis.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import ru.itis.TankGame;
import ru.itis.Weapon;
import ru.itis.utils.TankOwner;

public class PlayerTank extends Tank {
    int lives;

    public PlayerTank(TankGame game, TextureAtlas atlas) {
        super(game);
        this.ownerType = TankOwner.PLAYER;
        this.weapon = new Weapon();
        this.texture = atlas.findRegion("playerTank40");
        this.textureHp = atlas.findRegion("bar");
        this.position = new Vector2(100.0f, 100.0f);
        this.speed = 100.0f;
        this.width = texture.getRegionWidth();
        this.height = texture.getRegionHeight();
        this.hpMax = 10;
        this.hp = this.hpMax;
        this.hitBox = new Circle(position.x, position.y, (width + height) / 2);
        this.lives = 5;
    }

    @Override
    public void destroy() {
        lives--;
        hp = hpMax;
    }

    public void update(float dt) {
        checkMovement(dt);
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fire();
        }
        super.update(dt);
    }

    public void checkMovement(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= speed * dt;
            angle = 180.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += speed * dt;
            angle = 0.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += speed * dt;
            angle = 90.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= speed * dt;
            angle = 270.0f;
        }
    }
}
