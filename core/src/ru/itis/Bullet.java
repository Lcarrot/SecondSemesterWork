package ru.itis;

import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Vector2 position;
    private Vector2 velocity;
    private boolean active;

    public Bullet() {
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void deactivate() {
        active = false;
    }

    public void activate(float x, float y, float vx, float vy) {
        this.active = true;
        this.position.set(x, y);
        this.velocity.set(vx, vy);

    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        if (position.x < 0.f || position.x > 1280.f || position.y < 0.f || position.y > 720.f) {
            deactivate();
        }
    }
}
