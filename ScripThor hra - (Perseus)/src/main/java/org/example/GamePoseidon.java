package org.example;

import org.example.logic.Direction;
import org.example.logic.Enemy;
import org.example.logic.Entity;

import javax.swing.*;
import java.awt.event.*;

import static org.example.GameLogic.*;

public class GamePoseidon {
    GameLogic logic;
    private final int PLAYER_STEPS = 20;


    public GamePoseidon() {
        logic = new GameLogic();
        GameGraphics graphic = new GameGraphics(logic);
        logic.initialize();
        graphic.render(logic);
        logic.update();


            graphic.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (logic.level != 0) {
                        boolean enemy_Collision = false;

                        for (Enemy enemy : logic.getEnemies()) {
                            if (enemy.getRectangle_Enemy().intersects(player.getRectangle_Player())) {
                                enemy_Collision = true;
                                break;
                            }
                        }

                        if (!enemy_Collision && e != null) {
                            switch (e.getKeyCode()) {
                                case KeyEvent.VK_LEFT:
                                    controlledMove(player, Direction.LEFT, "Scrip - left_1.png", "Scrip - left_2.png", "Scrip - left_3.png");
                                    break;

                                case KeyEvent.VK_RIGHT:
                                    controlledMove(player, Direction.RIGHT, "Scrip - right_1.png", "Scrip - right_2.png", "Scrip - right_3.png");
                                    break;

                                case KeyEvent.VK_UP:
                                    controlledMove(player, Direction.UP, "Placeholder_up.png", "Placeholder_up.png", "Placeholder_up.png"); //změnit na chůži nahoru
                                    break;

                                case KeyEvent.VK_DOWN:
                                    controlledMove(player, Direction.DOWN, "Placeholder_down.png", "Placeholder_down.png", "Placeholder_down.png"); //změnit na chůži dolů
                                    break;

                                case KeyEvent.VK_W:
                                    controlledMove(player, Direction.UP, "Placeholder_up.png", "Placeholder_up.png", "Placeholder_up.png"); //změnit na chůži nahoru
                                    break;

                                case KeyEvent.VK_A:
                                    controlledMove(player, Direction.LEFT, "Scrip - left_1.png", "Scrip - left_2.png", "Scrip - left_3.png");
                                    break;

                                case KeyEvent.VK_S:
                                    controlledMove(player, Direction.DOWN, "Placeholder_down.png", "Placeholder_down.png", "Placeholder_down.png"); //změnit na chůži dolů
                                    break;

                                case KeyEvent.VK_D:
                                    controlledMove(player, Direction.RIGHT, "Scrip - right_1.png", "Scrip - right_2.png", "Scrip - right_3.png");
                                    break;

                                case KeyEvent.VK_Q:
                                    controlledMove(player, Direction.LEFT, "Placeholder.png", "Placeholder.png", "Placeholder.png");
                                    controlledMove(player, Direction.UP, "Placeholder.png", "Placeholder.png", "Placeholder.png");
                                    System.out.println("Levo, nahoru");
                                    break;

                                case KeyEvent.VK_E:
                                    controlledMove(player, Direction.RIGHT, "Placeholder.png", "Placeholder.png", "Placeholder.png");
                                    controlledMove(player, Direction.UP, "Placeholder.png", "Placeholder.png", "Placeholder.png");
                                    System.out.println("Pravo, nahoru");
                                    break;
                                case KeyEvent.VK_Y:
                                    controlledMove(player, Direction.LEFT, "Placeholder.png", "Placeholder.png", "Placeholder.png");
                                    controlledMove(player, Direction.DOWN, "Placeholder.png", "Placeholder.png", "Placeholder.png");
                                    System.out.println("Levo, dolů");
                                    break;
                                case KeyEvent.VK_X:
                                    controlledMove(player, Direction.RIGHT, "Placeholder.png", "Placeholder.png", "Placeholder.png");
                                    controlledMove(player, Direction.DOWN, "Placeholder.png", "Placeholder.png", "Placeholder.png");
                                    System.out.println("Pravo, dolů");
                                    break;
                            }
                        }
                        if (logic.level == -1) {
                            System.out.println("Level je -1, čekám na stisknutí mezerníku...");
                            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                                System.out.println("Mezerník stisknut! Opouštím tutoriál.");
                                logic.isTutorial = false;
                                logic.level = 0;
                                logic.initialize();
                            }
                        }
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

        Timer timer = new Timer(80, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphic.render(logic);
                logic.update();
            }
        });

        timer.start();
    }

    public void controlledMove(Entity entity, Direction direction1, String url1, String url2, String url3) {
        logic.getPlayer().move(PLAYER_STEPS, direction1, url1, url2, url3);
    }

    public GameLogic getLogic() {
        return logic;
    }
}