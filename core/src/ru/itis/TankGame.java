package ru.itis;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import ru.itis.emitters.BotEmitter;
import ru.itis.emitters.BulletEmitter;
import ru.itis.units.BotTank;
import ru.itis.units.PlayerTank;

public class TankGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private PlayerTank player;
    private BulletEmitter bulletEmitter;
    private BotEmitter botEmitter;
    private float gameTimer;

    @Override
    public void create() {
        TextureAtlas atlas = new TextureAtlas("game.pack");
        batch = new SpriteBatch();
        player = new PlayerTank(this, atlas);
        bulletEmitter = new BulletEmitter(atlas);
        botEmitter = new BotEmitter(this, atlas);
    }

    @Override
    public void render() {

        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(0, 0.6f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        player.render(batch);
        botEmitter.render(batch);
        bulletEmitter.render(batch);
        batch.end();
    }

    public void update(float dt) {
        gameTimer += dt;
        if (gameTimer > 5.f) {
            gameTimer = 0.f;
            botEmitter.activate(MathUtils.random(0.f, Gdx.graphics.getWidth()), MathUtils.random(0.f, Gdx.graphics.getHeight()));
        }
        player.update(dt);
        botEmitter.update(dt);
        bulletEmitter.update(dt);
        checkCollision();
    }

    public void checkCollision() {
        for (int i = 0; i < bulletEmitter.getBullets().length; i++) {
            Bullet bullet = bulletEmitter.getBullets()[i];
            if (bullet.isActive()) {
                for (int j = 0; i < botEmitter.getBots().length; i++) {
                    BotTank bot = botEmitter.getBots()[j];
                    if (bot.isActive()) {
                        if (bot.getHitBox().contains(bullet.getPosition())) {
                            bullet.deactivate();
                            bot.takeDamage(bot.getWeapon().getDamage());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public BulletEmitter getBulletEmitter() {
        return bulletEmitter;
    }
}
