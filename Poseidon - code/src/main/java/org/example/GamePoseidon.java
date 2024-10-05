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
                                    controlledMove(player, Direction.LEFT, "Player_left.png");
                                    break;

                                case KeyEvent.VK_RIGHT:
                                    controlledMove(player, Direction.RIGHT, "Player_right.png");
                                    break;

                                case KeyEvent.VK_UP:
                                    controlledMove(player, Direction.UP, "Player_up.png");
                                    break;

                                case KeyEvent.VK_DOWN:
                                    controlledMove(player, Direction.DOWN, "Player_down.png");
                                    break;

                                case KeyEvent.VK_W:
                                    controlledMove(player, Direction.UP, "Player_up.png");
                                    break;

                                case KeyEvent.VK_A:
                                    controlledMove(player, Direction.LEFT, "Player_left.png");
                                    break;

                                case KeyEvent.VK_S:
                                    controlledMove(player, Direction.DOWN, "Player_down.png");
                                    break;

                                case KeyEvent.VK_D:
                                    controlledMove(player, Direction.RIGHT, "Player_right.png");
                                    break;

                                case KeyEvent.VK_Q:
                                    controlledMove(player, Direction.LEFT, "Placeholder.png");
                                    controlledMove(player, Direction.UP, "Placeholder.png");
                                    System.out.println("Levo, nahoru");
                                    break;

                                case KeyEvent.VK_E:
                                    controlledMove(player, Direction.RIGHT, "Placeholder.png");
                                    controlledMove(player, Direction.UP, "Placeholder.png");
                                    System.out.println("Pravo, nahoru");
                                    break;
                                case KeyEvent.VK_Y:
                                    controlledMove(player, Direction.LEFT, "Placeholder.png");
                                    controlledMove(player, Direction.DOWN, "Placeholder.png");
                                    System.out.println("Levo, dolů");
                                    break;
                                case KeyEvent.VK_X:
                                    controlledMove(player, Direction.RIGHT, "Placeholder.png");
                                    controlledMove(player, Direction.DOWN, "Placeholder.png");
                                    System.out.println("Pravo, dolů");
                                    break;
                            }
                        }
                        if (logic.level == -1) {
                            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                                logic.isTutorial = true;
                            }
                        }
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {

                }
            });

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphic.render(logic);
                logic.update();
            }
        });

        timer.start();
    }

    public void controlledMove(Entity entity, Direction direction1, String url) {
        logic.getPlayer().move(PLAYER_STEPS, direction1, url);
    }

    public GameLogic getLogic() {
        return logic;
    }
}