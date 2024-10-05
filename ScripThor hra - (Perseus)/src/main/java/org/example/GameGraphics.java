package org.example;

import org.example.logic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class GameGraphics extends JFrame {
    Draw draw;
    GameLogic findLogic;
    public GameGraphics(GameLogic findLogic) throws HeadlessException {
        this.draw = new Draw();
        this.findLogic = findLogic;

        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Find Game");
        setResizable(false);

        add(draw);
    }

    public void render(GameLogic findLogic) {
        this.findLogic = findLogic;
        repaint();
    }

    public class Draw extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            if (!(findLogic.level == -1)) {
                g.setColor(Color.BLACK);
                Font font = new Font("Arial", Font.BOLD, 20);
                g.setFont(font);

                g.drawImage(findLogic.getBg().getImage(), findLogic.getBg().getCoord().x, findLogic.getBg().getCoord().y, 1080, 720, this);
                if (findLogic.level == 0) {
                    g.drawImage(findLogic.getLogo().getImage(), findLogic.getLogo().getCoord().x, findLogic.getLogo().getCoord().y, 1080, 720, this);
                    g.drawString("© Lukáš Karlovský 2024", 25, 650);
                    for (Bush bush: findLogic.getBushes()) {
                        g.drawImage(bush.getImage(), bush.getCoord().x, bush.getCoord().y, 500, 500, this);
                    }
                    g.drawImage(findLogic.getItem().getImage(), findLogic.getItem().getX(), findLogic.getItem().getY(), new ImageObserver() {
                        @Override
                        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                            return false;
                        }
                    });
                }
            }
            if (findLogic.level == -1) {
                g.drawImage(findLogic.getTutorial().getImage(), findLogic.getTutorial().getCoord().x, findLogic.getTutorial().getCoord().y, findLogic.getTutorial().getWidth(), findLogic.getTutorial().getHeight(), this);
            }


            if (!(findLogic.level == 0) && !(findLogic.level == -1)) {
                if (findLogic.level == 1) {
                    g.drawString("Tvůj čas: " + findLogic.getTime(), 900, 70);
                } else if (findLogic.level == 2) {
                    g.drawString("Tvůj čas: " + findLogic.getTime(), 900, 70);
                } else if (findLogic.level == 3) {
                    g.drawString("Tvůj čas: " + findLogic.getTime(), 900, 70);
                } else if (findLogic.level == 4) {
                    g.drawString("Debug", 900, 70);
                }
                g.drawString("Tvůj level: " + findLogic.level, 900, 30);


                g.drawImage(GameLogic.getPlayer().getImage(), GameLogic.getPlayer().getX(), GameLogic.getPlayer().getY(), new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });

                g.drawImage(findLogic.getItem().getImage(), findLogic.getItem().getX(), findLogic.getItem().getY(), new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });

                for (Enemy enemy: GameLogic.getEnemy())
                    g.drawImage(enemy.getImage(), enemy.getCoord().x, enemy.getCoord().y, new ImageObserver() {
                        @Override
                        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                            return false;
                        }
                    });

                for (Wall wall: findLogic.getWalls()) {
                    g.setColor(wall.getColor());
                    g.drawLine(wall.getCoordStart().x, wall.getCoordStart().y, wall.getCoordEnd().x, wall.getCoordEnd().y);
                }

                for (Bush bush: findLogic.getBushes()) {
                    g.drawImage(bush.getImage(), bush.getCoord().x, bush.getCoord().y, 500, 500, this);
                }
            }
        }
        @Override
        protected Graphics getComponentGraphics(Graphics g) {
            return super.getComponentGraphics(g);
        }
    }
}