package org.example.logic;

import java.awt.*;

public class Player extends Entity {
    public Player(int x, int y, String url) {
        super(x, y, url);
    }

    public int getX() {
        return coord.x;
    }

    public int getY() {
        return coord.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }

    public int returnPlayer(int x, int y) {
        coord.x = x;
        coord.y = y;

        return coord.x & coord.y;
    }
}
