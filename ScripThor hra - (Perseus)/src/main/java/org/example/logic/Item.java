package org.example.logic;

import java.awt.*;

public class Item extends Entity {
    public Item(int x, int y, String url) {
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

    public boolean isCollided (Rectangle otherObject) {
        return getRectangle_Item().intersects(otherObject);
    }
}
