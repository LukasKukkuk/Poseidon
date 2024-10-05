package org.example.logic;

import java.awt.*;

public class Wall {
    private Coords coordStart;
    private Coords coordEnd;
    private Color color;

    public Wall(int x1, int y1, int x2, int y2, Color color) {
        this.color = color;
        this.coordStart = new Coords(x1, y1);
        this.coordEnd = new Coords(x2, y2);
    }

    public Coords getCoordStart() {
        return coordStart;
    }

    public Coords getCoordEnd() {
        return coordEnd;
    }

    public Rectangle getRectangle() {
        return new Rectangle(coordStart.x, coordStart.y, coordEnd.x - coordStart.x+1, coordEnd.y - coordStart.y+1);
    }

    public Color getColor() {
        return color;
    }
}
