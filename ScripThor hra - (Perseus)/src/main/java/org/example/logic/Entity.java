package org.example.logic;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class Entity {
    protected Coords coord;
    protected int width;
    protected int height;
    protected Image image;

    private int frameCount = 0;  // Počítadlo pro sledování změny obrázku
    private ImageIcon[] imageSequence = new ImageIcon[3]; // Pole pro 3 obrázky

    public Entity(int x, int y, String url) {
        this.coord = new Coords(x,y);

        ImageIcon ii = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url)));
        this.image = ii.getImage();
        this.width = ii.getIconWidth();
        this.height = ii.getIconHeight();
        Arrays.fill(imageSequence, null);

    }

    public void move(int steps, Direction direction, String url1, String url2, String url3) {
        // Nastavení obrázků, pokud nejsou null
        if (url1 != null) {
            imageSequence[0] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url1)));
        }
        if (url2 != null) {
            imageSequence[1] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url2)));
        }
        if (url3 != null) {
            imageSequence[2] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/" + url3)));
        }


        switch (direction) {
            case LEFT -> this.coord.x -= steps;
            case RIGHT -> this.coord.x += steps;
            case UP -> this.coord.y -= steps;
            case DOWN -> this.coord.y += steps;
        }


        ImageIcon currentImage = null;


        if (imageSequence[frameCount] != null) {
            currentImage = imageSequence[frameCount];
        } else {
            for (ImageIcon img : imageSequence) {
                if (img != null) {
                    currentImage = img;
                    break;
                }
            }
        }


        if (currentImage != null) {
            this.image = currentImage.getImage();
            this.width = currentImage.getIconWidth();
            this.height = currentImage.getIconHeight();
        }


        frameCount++;
        if (frameCount == 3) {
            frameCount = 0;
        }
    }


    public Rectangle getRectangle_Item(){
        return new Rectangle(coord.x, coord.y, width, height);
    }
    public Rectangle getRectangle_Bush(){
        return new Rectangle(coord.x+200,coord.y+220, width/5, height/6);
    }
    public Rectangle getRectangle_Enemy(){
        return new Rectangle(coord.x+40,coord.y+65, (width/2)-20, (height/2)-5);
    }
    public Rectangle getRectangle_Player(){
        return new Rectangle(coord.x,coord.y,width, height);
    }

    public Coords getCoord() {
        return coord;
    }


    public Image getImage() {
        return image;
    }


    public boolean isCollided (Rectangle otherObject) {
        return getRectangle_Player().intersects(otherObject);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
