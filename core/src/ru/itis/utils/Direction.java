package ru.itis.utils;

public enum Direction {
    UP(0, 1, 90f), DOWN(0, -1, 270f), LEFT(-1, 0, 180f), RIGHT(1, 0, 0f);

    private int vx;
    private int vy;
    private float angle;

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public float getAngle() {
        return angle;
    }

    Direction(int vx, int vy, float angle) {
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;
    }
}
