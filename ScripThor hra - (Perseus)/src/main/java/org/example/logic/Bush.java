package org.example.logic;

public class Bush extends Entity {
    private Coords coordStart;
    private Coords coordEnd;

    public Bush(int x1, int y1, String url) {
        super(x1, y1, url);
        this.coordStart = new Coords(x1, y1);
        this.coordEnd = new Coords(x1, y1);
    }
}
