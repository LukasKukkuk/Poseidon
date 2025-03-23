
package org.example;

import org.example.logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static org.example.logic.Direction.*;

public class GameLogic {
    public static Player player;
    private static ArrayList<Enemy> enemies;
    private ArrayList<Bush> bushes;
    private ArrayList<Wall> walls;
    private final int ENEMY_STEPS = 10;
    private final int ITEM_STEPS = 10;
    public PNG bg;
    public PNG tutorial;
    public PNG logo;
    public JOptionPane pane = new JOptionPane();
    public int time;
    public Item item1;
    public int level;
    public Rectangle area;
    public boolean isTutorial = false;
    public boolean en = false;

    public GameLogic() {
        this.player = null;
        this.enemies = new ArrayList<>();
        this.bushes = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.level = 0; //klasik hra
        //this.level = 4; //debug level
        this.en = false;
    }

    public void initialize() {
        if (level == 0) {
            bg = new PNG(0, 0, "Background_no_object.png");
            logo = new PNG(0, 0, "Poseidon.png");
            item1 = new Item(500, 150, "Item.png");

            bushes = new ArrayList<>();
            Bush bush1 = new Bush(-100, 200, "Object.png");
            Bush bush2 = new Bush(600, 150, "Object.png");
            Bush bush3 = new Bush(100, 300, "Object.png");
            Bush bush4 = new Bush(500, 300, "Object.png");
            Bush bush5 = new Bush(300, 300, "Object.png");
            Bush bush6 = new Bush(-100, 0, "Object.png");
            Bush bush7 = new Bush(-100, 0, "Object.png");
            Bush bush8 = new Bush(600, -50, "Object.png");
            bushes.add(bush1);
            bushes.add(bush2);
            bushes.add(bush3);
            bushes.add(bush4);
            bushes.add(bush5);
            bushes.add(bush6);
            bushes.add(bush7);
            bushes.add(bush8);
        }

        if (level == -1) {
            tutorial = new PNG(0, 0, "tutorial.png");
        }

        if (!(level == 0) && !(level == -1)) {
            player = new Player(20, 20, "Placeholder_down.png"); //Změnit png, na vzled dolů
            bg = new PNG(0, 0, "Background_no_object.png");
            area = new Rectangle(0, 0, 1080, 720);

            walls = new ArrayList<>();
            Wall wall1 = new Wall(0, 10, 1080, 10, Color.BLACK);
            walls.add(wall1);


            if (level == 1) {
                setTime(180);
                itemRandomize();

                enemies = new ArrayList<>();
                Enemy enemy1 = new Enemy(500, 0, "Enemy_down.png");
                Enemy enemy2 = new Enemy(200, 0, "Enemy_down.png");

                enemies.add(enemy1);
                enemies.add(enemy2);

                bushes = new ArrayList<>();
                Bush bush1 = new Bush(-100, 150, "Object.png");
                Bush bush2 = new Bush(200, -100, "Object.png");
                Bush bush3 = new Bush(500, 0, "Object.png");
                bushes.add(bush1);
                bushes.add(bush2);
                bushes.add(bush3);
            }

            if (level == 2) {
                setTime(200);
                itemRandomize();

                enemies = new ArrayList<>();
                Enemy enemy1 = new Enemy(500, 100, "Enemy_down.png");
                Enemy enemy2 = new Enemy(100, 300, "Enemy_down.png");
                Enemy enemy3 = new Enemy(300, 0, "Enemy_down.png");
                enemies.add(enemy1);
                enemies.add(enemy2);
                enemies.add(enemy3);

                bushes = new ArrayList<>();
                Bush bush1 = new Bush(-100, 250, "Object.png");
                Bush bush2 = new Bush(200, -100, "Object.png");
                Bush bush3 = new Bush(350, 0, "Object.png");
                Bush bush4 = new Bush(0, 0, "Object.png");
                Bush bush5 = new Bush(100, 200, "Object.png");
                Bush bush6 = new Bush(500, 200, "Object.png");
                bushes.add(bush1);
                bushes.add(bush2);
                bushes.add(bush3);
                bushes.add(bush4);
                bushes.add(bush5);
                bushes.add(bush6);
            }

            if (level == 3) {
                setTime(250);
                itemRandomize();

                enemies = new ArrayList<>();
                Enemy enemy1 = new Enemy(800, 0, "Enemy_down.png");
                Enemy enemy2 = new Enemy(300, 400, "Enemy_down.png");
                Enemy enemy3 = new Enemy(400, 0, "Enemy_down.png");
                Enemy enemy4 = new Enemy(400, 200, "Enemy_down.png");
                enemies.add(enemy1);
                enemies.add(enemy2);
                enemies.add(enemy3);
                enemies.add(enemy4);

                bushes = new ArrayList<>();
                Bush bush1 = new Bush(200, -100, "Object.png");
                Bush bush2 = new Bush(0, 0, "Object.png");
                Bush bush3 = new Bush(-150, 200, "Object.png");
                Bush bush4 = new Bush(500, 300, "Object.png");
                Bush bush5 = new Bush(-10, 300, "Object.png");
                Bush bush6 = new Bush(250, 300, "Object.png");
                Bush bush7 = new Bush(500, 0, "Object.png");
                bushes.add(bush1);
                bushes.add(bush2);
                bushes.add(bush3);
                bushes.add(bush4);
                bushes.add(bush5);
                bushes.add(bush6);
                bushes.add(bush7);
            }

            if (level == 4) { //debug
                itemRandomize();

                enemies = new ArrayList<>();
                Enemy enemy1 = new Enemy(500, 0, "Enemy_down.png");
                Enemy enemy2 = new Enemy(200, 0, "Enemy_down.png");

                enemies.add(enemy1);
                enemies.add(enemy2);

                bushes = new ArrayList<>();
                Bush bush1 = new Bush(-100, 150, "Object.png");
                Bush bush2 = new Bush(200, -100, "Object.png");
                Bush bush3 = new Bush(500, 0, "Object.png");
                bushes.add(bush1);
                bushes.add(bush2);
                bushes.add(bush3);
            }
        }
    }

    public void update() {
        updateTime();

        if (level != 0) {
            for (Wall wall : walls) {
                playerReturn();
                enemyReturn();

                if (item1.isCollided(wall.getRectangle().getBounds())) {
                    String message = en ? "Finished level " + level + ", your time was: " + time + "." + "\nDo you want continue?" :
                            "Dokončení level " + level + ", tvůj čas byl: " + time + "." + "\nPřeješ si pokračovat?";
                    Object[] button = en ? new Object[]{"Yes, I want to continue", "I want to restart", "End game"} :
                            new Object[]{"Ano, chci pokračovat", "Chci restartovat", "Chci zavřít hru"};

                    int choice = JOptionPane.showOptionDialog(pane, message, "Reset hry", JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, button, button[2]);

                    if (choice == JOptionPane.YES_OPTION) {
                        if (level != 4) {
                            level++;
                        }
                        else {
                            level = 1;
                        }
                    } else if (choice == JOptionPane.NO_OPTION) {

                    } else if (choice == JOptionPane.CANCEL_OPTION) {
                        System.exit(0);
                    }

                    initialize();
                    return; // Zabráníme opakovanému volání initialize() ve stejné smyčce
                }
            }
        } else {
            showIntroDialog();
        }

        if (level == -1) {

            return;
        }


        if (level > 0) {
            if (time > 0) {
                time--;
            } else if (time == 0 && level != 4) {
                JOptionPane.showMessageDialog(null, en ? "Time won over you, but don't give up. :D" :
                        "Čas zvítězil nad tebou. Ale nevzdávej se. :D");
                initialize();
            }
            collision();
        }
    }

    private void updateTime() {
        if (time > 0) {
            time--;
        }
    }


    public void collision() {
        if (level == 1) {
            if (player.isCollided(item1.getRectangle_Item())) {
                item1.move(ITEM_STEPS, UP, "Item.png", null, null);
            }
        }
        if (level == 2) {
            if (player.isCollided(item1.getRectangle_Item())) {
                item1.move(ITEM_STEPS, UP, "Item.png", null, null);
            }
        }
        if (level == 3) {
            if (player.isCollided(item1.getRectangle_Item())) {
                item1.move(ITEM_STEPS, UP, "Item.png", null, null);
            }
        }
        if (level == 4) {
            if (player.isCollided(item1.getRectangle_Item())) {
                item1.move(ITEM_STEPS, UP, "Item.png", null, null);
            }
        }
        for (Enemy enemy : enemies) {
            randomMovement(enemy, area);
        }
    }

    public void randomMovement(Enemy enemy, Rectangle area) {
        for (Bush bush : bushes) {
            Random rand = new Random();
            int rand_number = rand.nextInt(1, 4 + 1);
            if (enemy.getCoord().x < area.width && enemy.getCoord().y < area.height) {
                if (rand_number == 1) {
                    enemy.move(ENEMY_STEPS, UP, "Enemy_up.png", null, null);
                }
                if (rand_number == 2) {
                    enemy.move(ENEMY_STEPS, LEFT, "Enemy_left.png", null, null);
                }
                if (rand_number == 3) {
                    enemy.move(ENEMY_STEPS, RIGHT, "Enemy_right.png", null, null);
                }
                if (rand_number == 4) {
                    enemy.move(ENEMY_STEPS, DOWN, "Enemy_down.png", null, null);
                }
                if (rand_number == 1 && enemy.isCollided(bush.getRectangle_Bush())) {
                    enemy.move(ENEMY_STEPS, UP, "Enemy_up.png", null, null);
                }
                if (rand_number == 2 && enemy.isCollided(bush.getRectangle_Bush())) {
                    enemy.move(ENEMY_STEPS, LEFT, "Enemy_left.png", null, null);
                }
                if (rand_number == 3 && enemy.isCollided(bush.getRectangle_Bush())) {
                    enemy.move(ENEMY_STEPS, RIGHT, "Enemy_right.png", null, null);
                }
                if (rand_number == 4 && enemy.isCollided(bush.getRectangle_Bush())) {
                    enemy.move(ENEMY_STEPS, DOWN, "Enemy_down.png", null, null);
                }
            }
        }
    }

    public void itemRandomize() {
        if (level == 1) {
            Random posbush = new Random();
            int item_pos = posbush.nextInt(0 + 1, 3 + 1);
            if (item_pos == 1) {
                item1 = new Item(100, 340, "Item.png");
            } else if (item_pos == 2) {
                item1 = new Item(400, 100, "Item.png");
            } else if (item_pos == 3) {
                item1 = new Item(700, 200, "Item.png");
            }
        }
        if (level == 2) {
            Random posbush = new Random();
            int item_pos = posbush.nextInt(0 + 1, 6 + 1);
            if (item_pos == 1) {
                item1 = new Item(100, 450, "Item.png");
            } else if (item_pos == 2) {
                item1 = new Item(400, 100, "Item.png");
            } else if (item_pos == 3) {
                item1 = new Item(550, 200, "Item.png");
            } else if (item_pos == 4) {
                item1 = new Item(200, 200, "Item.png");
            } else if (item_pos == 5) {
                item1 = new Item(300, 400, "Item.png");
            } else if (item_pos == 6) {
                item1 = new Item(700, 400, "Item.png");
            }
        }
        if (level == 3) {
            Random posbush = new Random();
            int item_pos = posbush.nextInt(0 + 1, 7 + 1);
            if (item_pos == 1) {
                item1 = new Item(400, 100, "Item.png");
            } else if (item_pos == 2) {
                item1 = new Item(200, 200, "Item.png");
            } else if (item_pos == 3) {
                item1 = new Item(50, 400, "Item.png");
            } else if (item_pos == 4) {
                item1 = new Item(700, 500, "Item.png");
            } else if (item_pos == 5) {
                item1 = new Item(200, 500, "Item.png");
            } else if (item_pos == 6) {
                item1 = new Item(450, 500, "Item.png");
            } else if (item_pos == 7) {
                item1 = new Item(700, 200, "Item.png");
            }
        }
        if (level == 4) {
            Random posbush = new Random();
            int item_pos = posbush.nextInt(0 + 1, 3 + 1);
            if (item_pos == 1) {
                item1 = new Item(100, 340, "Item.png");
            } else if (item_pos == 2) {
                item1 = new Item(400, 100, "Item.png");
            } else if (item_pos == 3) {
                item1 = new Item(700, 200, "Item.png");
            }
        }
    }

    public void playerReturn() {
        int randomX = new Random().nextInt(1080); // náhodné X mezi 0 a 1080
        int randomY = new Random().nextInt(720); // náhodné Y mezi 0 a 720
        if (player.getX() > 1080 || player.getX() < 0) {
            player.returnPlayer(randomX, randomY);
        }
        if (player.getY() > 720 || player.getY() < 0) {
            player.returnPlayer(randomX, randomY);
        }
    }

    public void enemyReturn() {
        for (Enemy enemy : enemies) {
            int randomX = new Random().nextInt(1080); // náhodné X mezi 0 a 1080
            int randomY = new Random().nextInt(720); // náhodné Y mezi 0 a 720
            if (enemy.getX() > 1080 || enemy.getX() < 0) {
                enemy.returnEnemy(randomX, randomY);
            }
            if (enemy.getY() > 720 || enemy.getY() < 0) {
                enemy.returnEnemy(randomX, randomY);
            }
        }
    }

    public static ArrayList<Enemy> getEnemy() {
        return enemies;
    }

    public static Player getPlayer() {
        return player;
    }

    public ArrayList<Bush> getBushes() {
        return bushes;
    }

    public PNG getBg() {
        return bg;
    }

    public PNG getLogo() {
        return logo;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Item getItem() {
        return item1;
    }

    public PNG getTutorial() {
        return tutorial;
    }
    public boolean isEn() {
        return en;
    }

    public boolean isTutorial() {
        return isTutorial;
    }

    private void showIntroDialog() {
        Object[] button = en ? new Object[]{"Yes, let's go", "I want tutorial", "I want to close game", "Change language"} :
                new Object[]{"Ano, jdeme na to", "Chci tutoriál", "Chci zavřít hru", "Změnit jazyk"};

        String message = en ? "Welcome to the game Poseidon! :D Do you want to jump to the first level?" :
                "Vítej ve hře Poseidon! :D Chceš jít rovnou na první level?";

        int choice = JOptionPane.showOptionDialog(pane, message, "Vítej", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, button, button[3]);

        if (choice == 0) { // První možnost - start hry
            level = 1;
            initialize();
        } else if (choice == 1) { // Druhá možnost - tutoriál
            level = -1;
            initialize();
        } else if (choice == 2) { // Třetí možnost - zavření hry
            JOptionPane.showMessageDialog(null, en ? "So bye. :)" : "Tak nic. Ahoj :)");
            System.exit(0);
        } else if (choice == 3) { // Čtvrtá možnost - změna jazyka
            en = !en; // Přepnutí mezi češtinou a angličtinou
        }
    }
}