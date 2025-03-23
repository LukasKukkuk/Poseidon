package org.example.logic;

public class Enemy extends Entity {

    public Enemy(int x, int y, String url) {
        super(x, y, url);
    }

    public int returnEnemy(int x, int y) {
        coord.x = x;
        coord.y = y;

        return coord.x & coord.y;
    }

    public int getX() {
        return coord.x;
    }

    public int getY() {
        return coord.y;
    }
}
