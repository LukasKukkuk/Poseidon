
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
    public int enemy_X;
    public int enemy_Y;
    public Rectangle area;
    public boolean isTutorial = false;
    public boolean minusTime = true;

    public GameLogic() {
        this.player = null;
        this.enemies = new ArrayList<>();
        this.bushes = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.level = 4;
    }

    public void initialize() {
        if (level == 0) {
            bg = new PNG(0,0,"Background_no_object.png");
            logo = new PNG(0,0,"Poseidon.png");
            item1 = new Item(500, 150, "Item.png");

            bushes = new ArrayList<>();
            Bush bush1 = new Bush(-100, 200,"Object.png");
            Bush bush2 = new Bush(600, 150, "Object.png");
            Bush bush3 = new Bush(100, 300, "Object.png");
            Bush bush4 = new Bush(500, 300, "Object.png");
            Bush bush5 = new Bush(300, 300, "Object.png");
            Bush bush6 = new Bush(-100, 0,"Object.png");
            Bush bush7 = new Bush(-100,  0,"Object.png");
            Bush bush8 = new Bush(600, -50,"Object.png");
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
            tutorial = new PNG(0,0,"tutorial.png");
        }

        if (!(level == 0) && !(level == -1)) {
            player = new Player(20, 20, "Player_down.png");
            bg = new PNG(0, 0, "Background_no_object.png");
            area = new Rectangle(0,0 , 1080, 720);

            walls = new ArrayList<>();
            Wall wall1 = new Wall(0,10,1080,10, Color.BLACK);
            walls.add(wall1);


            if (level == 1) {
                setTime(200);
                itemRandomize();

                enemies = new ArrayList<>();
                Enemy enemy1 = new Enemy(500,0, "Enemy_down.png");
                Enemy enemy2 = new Enemy(200,0, "Enemy_down.png");

                enemies.add(enemy1);
                enemies.add(enemy2);

                bushes = new ArrayList<>();
                Bush bush1 = new Bush(-100, 150,"Object.png");
                Bush bush2 = new Bush(200, -100, "Object.png");
                Bush bush3 = new Bush(500, 0, "Object.png");
                bushes.add(bush1);
                bushes.add(bush2);
                bushes.add(bush3);
            }

            if (level == 2) {
                setTime(500);
                itemRandomize();

                enemies = new ArrayList<>();
                Enemy enemy1 = new Enemy(500,100, "Enemy_down.png");
                Enemy enemy2 = new Enemy(100,300, "Enemy_down.png");
                Enemy enemy3 = new Enemy(300, 0, "Enemy_down.png");
                enemies.add(enemy1);
                enemies.add(enemy2);
                enemies.add(enemy3);

                bushes = new ArrayList<>();
                Bush bush1 = new Bush(-100, 250,"Object.png");
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
                setTime(400);
                itemRandomize();

                enemies = new ArrayList<>();
                Enemy enemy1 = new Enemy(800,0, "Enemy_down.png");
                Enemy enemy2 = new Enemy(300,400, "Enemy_down.png");
                Enemy enemy3 = new Enemy(400,0, "Enemy_down.png");
                Enemy enemy4 = new Enemy(400,200, "Enemy_down.png");
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
                setMinusTime(false);
                itemRandomize();

                enemies = new ArrayList<>();
                Enemy enemy1 = new Enemy(500,0, "Enemy_down.png");
                Enemy enemy2 = new Enemy(200,0, "Enemy_down.png");

                enemies.add(enemy1);
                enemies.add(enemy2);

                bushes = new ArrayList<>();
                Bush bush1 = new Bush(-100, 150,"Object.png");
                Bush bush2 = new Bush(200, -100, "Object.png");
                Bush bush3 = new Bush(500, 0, "Object.png");
                bushes.add(bush1);
                bushes.add(bush2);
                bushes.add(bush3);
            }
        }
    }

    public void update() {
        if (!(level == 0)) {
            for (Wall wall: walls) {
                if (level == 1) {
                    if (item1.isCollided(wall.getRectangle().getBounds())) {
                        var level1 = JOptionPane.showConfirmDialog(pane,"Dokončení level " + level + ", tvůj čas byl: " + time + "." + "\nPřeješ si pokračovat?", "Level 1", JOptionPane.YES_NO_OPTION);
                        if (level1 == pane.YES_OPTION) {
                            level = 2;
                            initialize();
                        }
                        else {
                            System.exit(0);
                        }
                    }
                } else if (level == 2) {
                    if (item1.isCollided(wall.getRectangle().getBounds())) {
                        var level2 = JOptionPane.showConfirmDialog(pane,"Dokončení level " + level + ", tvůj čas byl: " + time + "." + "\nPřeješ si pokračovat?", "Level 3", JOptionPane.YES_NO_OPTION);
                        if (level2 == pane.YES_OPTION) {
                            level = 3;
                            initialize();
                        }
                        else {
                            System.exit(0);
                        }
                    }
                } else if (level == 3) {
                    if (item1.isCollided(wall.getRectangle().getBounds())) {
                        var level3 = JOptionPane.showConfirmDialog(pane,"Dokončení level " + level + ", tvůj čas byl: " + time + "." + "\nPřeješ si pokračovat?", "Reset hry", JOptionPane.YES_NO_OPTION);
                        if (level3 == pane.YES_OPTION) {
                            level = 1;
                            initialize();
                        }
                        else {
                            System.exit(0);
                        }
                    }
                }
                else if (level == 4) {
                    if (item1.isCollided(wall.getRectangle().getBounds())) {
                        var level4 = JOptionPane.showConfirmDialog(pane, "Dokončení level " + level + ", tvůj čas byl: " + time + "." + "\nPřeješ si pokračovat?", "Reset hry", JOptionPane.YES_NO_OPTION);
                        if (level4 == pane.YES_OPTION) {
                            level = 4;
                            initialize();
                        } else {
                            System.exit(0);
                        }
                    }
                }
            }
        }
        else {
            Object[] button = {"Ano jdeme na to", "Chci tutoriál", "Chci zavřít hru"};
            var level0 = JOptionPane.showOptionDialog(pane,"Vítej v této hře s názvem Poseidon! :D Chceš jít rovnou na první level?", "Vítej", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, button, button[2]);
            if (level0 == pane.YES_OPTION) {
                level = 1;
                initialize();
            }
            if (level0 == pane.CANCEL_OPTION){
                pane.showMessageDialog(null, "Tak nic. Ahoj :)");
                System.exit(0);
            }
            if (level0 == pane.NO_OPTION) {
                level = -1;
                initialize();
            }
        }

        if (level == -1 && isTutorial == true) {
            level = 0;
            initialize();
        }

        if (!(level == 0) && !(level == -1)){
            if (!(time == 0)) {
                if (minusTime == true) {
                    time--;
                }
            } else if (time == 0 && minusTime == true){
                JOptionPane.showMessageDialog(null, "Čas zvítežil nad tebou. Ale nevzdávej se. :D");
                initialize();
            }
            collision();
        }
        enemyDelete();
    }

    public void collision() {
        if (level == 1) {
            if (player.isCollided(item1.getRectangle_Item())) {
                item1.move(ITEM_STEPS, UP, "Item.png");
            }
        }
        if (level == 2) {
            if (player.isCollided(item1.getRectangle_Item())) {
                item1.move(ITEM_STEPS, UP, "Item.png");
            }
        }
        if (level == 3) {
            if (player.isCollided(item1.getRectangle_Item())) {
                item1.move(ITEM_STEPS, UP, "Item.png");
            }
        }
        for (Enemy enemy : enemies) {
            for (Bush bush: bushes) {
                if (enemy.getRectangle_Enemy().intersects(bush.getRectangle_Bush())) {
                    if (enemy.getCoord().x == enemy_X++) {
                        enemyControl(enemy, RIGHT);
                    }
                    if (enemy.getCoord().x == enemy_X--) {
                        enemyControl(enemy, LEFT);
                    }
                    if (enemy.getCoord().y == enemy_Y++) {
                        enemyControl(enemy, UP);
                    }
                    if (enemy.getCoord().y == enemy_Y--) {
                        enemyControl(enemy, DOWN);
                    }
                }
            }
            randomMovement(enemy, area);
        }
    }

    public void enemyControl(Enemy enemy, Direction direction) {
            for (Wall wall: walls) {
                for (Bush bush: bushes){
                    Random rand = new Random();
                    int rand_number = rand.nextInt(1, 4 + 1);
                    switch (direction) {
                        case RIGHT -> {
                            if (enemy.getRectangle_Enemy().intersects(wall.getRectangle())) {
                                if (rand_number == 1) {
                                    enemy.move(10, UP, "Enemy_up.png");
                                }
                                if (rand_number == 2) {
                                    enemy.move(10, LEFT, "Enemy_left.png");
                                }
                                if (rand_number == 3) {
                                    enemy.move(10, DOWN, "Enemy_down.png");
                                }
                            }
                            if (enemy.getRectangle_Enemy().intersects(bush.getRectangle_Bush())) {
                                if (rand_number == 1) {
                                    enemy.move(10, UP, "Enemy_up.png");
                                }
                                if (rand_number == 2) {
                                    enemy.move(10, LEFT, "Enemy_left.png");
                                }
                                if (rand_number == 3) {
                                    enemy.move(10, DOWN, "Enemy_down.png");
                                }
                            }
                        }
                        case LEFT -> {
                            if (enemy.getRectangle_Enemy().intersects(wall.getRectangle())) {
                                if (rand_number == 1) {
                                    enemy.move(10, UP, "Enemy_up.png");
                                }
                                if (rand_number == 4) {
                                    enemy.move(10, RIGHT, "Enemy_right.png");
                                }
                                if (rand_number == 3) {
                                    enemy.move(10, DOWN, "Enemy_down.png");
                                }
                            }
                            if (enemy.getRectangle_Enemy().intersects(bush.getRectangle_Bush())) {
                                if (rand_number == 1) {
                                    enemy.move(10, UP, "Enemy_up.png");
                                }
                                if (rand_number == 4) {
                                    enemy.move(10, RIGHT, "Enemy_right.png");
                                }
                                if (rand_number == 3) {
                                    enemy.move(10, DOWN, "Enemy_down.png");
                                }
                            }
                        }
                        case UP -> {
                            if (enemy.getRectangle_Enemy().intersects(wall.getRectangle())) {
                                if (rand_number == 4) {
                                    enemy.move(10, RIGHT, "Enemy_right.png");
                                }
                                if (rand_number == 2) {
                                    enemy.move(10, LEFT, "Enemy_left.pn g");
                                }
                                if (rand_number == 3) {
                                    enemy.move(10, DOWN, "Enemy_down.png");
                                }
                            }
                            if (enemy.getRectangle_Enemy().intersects(bush.getRectangle_Bush())) {
                                if (rand_number == 4) {
                                    enemy.move(10, RIGHT, "Enemy_right.png");
                                }
                                if (rand_number == 2) {
                                    enemy.move(10, LEFT, "Enemy_left.png");
                                }
                                if (rand_number == 3) {
                                    enemy.move(10, DOWN, "Enemy_down.png");
                                }
                            }
                        }
                        case DOWN -> {
                            if (enemy.getRectangle_Enemy().intersects(wall.getRectangle())) {
                                if (rand_number == 4) {
                                    enemy.move(10, RIGHT, "Enemy_right.png");
                                }
                                if (rand_number == 2) {
                                    enemy.move(10, LEFT, "Enemy_left.png");
                                }
                                if (rand_number == 1) {
                                    enemy.move(10, UP, "Enemy_up.png");
                                }
                            }
                            if (enemy.getRectangle_Enemy().intersects(bush.getRectangle_Bush())) {
                                if (rand_number == 4) {
                                    enemy.move(10, RIGHT, "Enemy_right.png");
                                }
                                if (rand_number == 2) {
                                    enemy.move(10, LEFT, "Enemy_left.png");
                                }
                                if (rand_number == 1) {
                                    enemy.move(10, UP, "Enemy_up.png");
                                }
                            }
                        }
                    }
                }
            }
    }

    public void randomMovement (Enemy enemy, Rectangle area) {
        for (Bush bush: bushes){
            Random rand = new Random();
            int rand_number = rand.nextInt(1, 4 + 1);
            if (enemy.getCoord().x < area.width && enemy.getCoord().y < area.height) {
                if (rand_number == 1) {
                    enemy.move(ENEMY_STEPS, UP, "Enemy_up.png");
                }
                if (rand_number == 2) {
                    enemy.move(ENEMY_STEPS, LEFT, "Enemy_left.png");
                }
                if (rand_number == 3) {
                    enemy.move(ENEMY_STEPS, RIGHT, "Enemy_right.png");
                }
                if (rand_number == 4) {
                    enemy.move(ENEMY_STEPS, DOWN, "Enemy_down.png");
                }
                if (rand_number == 1 && enemy.isCollided(bush.getRectangle_Bush())) {
                    enemy.move(ENEMY_STEPS, UP, "Enemy_up.png");
                }
                if (rand_number == 2 && enemy.isCollided(bush.getRectangle_Bush())) {
                    enemy.move(ENEMY_STEPS, LEFT, "Enemy_left.png");
                }
                if (rand_number == 3 && enemy.isCollided(bush.getRectangle_Bush())) {
                    enemy.move(ENEMY_STEPS, RIGHT, "Enemy_right.png");
                }
                if (rand_number == 4 && enemy.isCollided(bush.getRectangle_Bush())) {
                    enemy.move(ENEMY_STEPS, DOWN, "Enemy_down.png");
                }
            }
        }
    }

    public void itemRandomize() {
        if (level == 1) {
            Random posbush = new Random();
            int item_pos = posbush.nextInt(0+1, 3+1);
            System.out.println(item_pos);
            if (item_pos == 1) {
                item1 = new Item(100, 340,"Item.png");
            }
            else if (item_pos == 2){
                item1 = new Item(400, 100,"Item.png");
            }
            else if (item_pos == 3){
                item1 = new Item(700,200,"Item.png");
            }
        }
        if (level == 2) {
            Random posbush = new Random();
            int item_pos = posbush.nextInt(0+1, 6+1);
            System.out.println(item_pos);
            if (item_pos == 1) {
                item1 = new Item(100,450,"Item.png");
            }
            else if (item_pos == 2){
                item1 = new Item(400, 100,"Item.png");
            }
            else if (item_pos == 3){
                item1 = new Item(550,200,"Item.png");
            }
            else if (item_pos == 4){
                item1 = new Item(200,200,"Item.png");
            }
            else if (item_pos == 5){
                item1 = new Item(300,400,"Item.png");
            }
            else if (item_pos == 6){
                item1 = new Item(700,400,"Item.png");
            }
        }
        if (level == 3) {
            Random posbush = new Random();
            int item_pos = posbush.nextInt(0+1, 7+1);
            System.out.println(item_pos);
            if (item_pos == 1) {
                item1 = new Item(400, 100,"Item.png");
            }
            else if (item_pos == 2){
                item1 = new Item(200, 200,"Item.png");
            }
            else if (item_pos == 3){
                item1 = new Item(50,400,"Item.png");
            }
            else if (item_pos == 4){
                item1 = new Item(700,500,"Item.png");
            }
            else if (item_pos == 5){
                item1 = new Item(200,500,"Item.png");
            }
            else if (item_pos == 6){
                item1 = new Item(450,500,"Item.png");
            }
            else if (item_pos == 7){
                item1 = new Item(700,200,"Item.png");
            }
        }
        if (level == 4) {
            Random posbush = new Random();
            int item_pos = posbush.nextInt(0+1, 3+1);
            System.out.println(item_pos);
            if (item_pos == 1) {
                item1 = new Item(100, 340,"Item.png");
            }
            else if (item_pos == 2){
                item1 = new Item(400, 100,"Item.png");
            }
            else if (item_pos == 3){
                item1 = new Item(700,200,"Item.png");
            }
        }
    }

    public void enemyDelete() {
        if (level == 1) {
            if (enemies.get(1).getCoord().x > area.x ) {
                enemies.remove(1);
                enemies.add(new Enemy(0, 0, "Enemy_down.png"));
                System.out.println("Nepřítel 1 - odstraněn - x");
            } else if (enemies.get(1).getCoord(). y > area.y ) {
                enemies.remove(1);
                System.out.println("Nepřítel 1 - odstraněn - y");
            }
            if (enemies.get(2).getCoord().x > area.x ) {
                enemies.remove(2);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 2 - odstraněn - x");
            } else if (enemies.get(2).getCoord(). y > area.y ) {
                enemies.remove(2);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 2 - odstraněn - y");
            }
        }
        if (level == 2) {
            if (enemies.get(1).getCoord().x > area.x ) {
                enemies.remove(1);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 1 - odstraněn - x");
            } else if (enemies.get(1).getCoord(). y > area.y ) {
                enemies.remove(1);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 1 - odstraněn - y");
            }
            if (enemies.get(2).getCoord().x > area.x ) {
                enemies.remove(2);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 2 - odstraněn - x");
            } else if (enemies.get(2).getCoord(). y > area.y ) {
                enemies.remove(2);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 2 - odstraněn - y");
            }
            if (enemies.get(3).getCoord().x > area.x ) {
                enemies.remove(3);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 3 - odstraněn - x");
            } else if (enemies.get(3).getCoord(). y > area.y ) {
                enemies.remove(3);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 3 - odstraněn - y");
            }
        }
        if (level == 3) {
            if (enemies.get(1).getCoord().x > area.x ) {
                enemies.remove(1);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 1 - odstraněn - x");
            } else if (enemies.get(1).getCoord(). y > area.y ) {
                enemies.remove(1);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 1 - odstraněn - y");
            }
            if (enemies.get(2).getCoord().x > area.x ) {
                enemies.remove(2);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 2 - odstraněn - x");
            } else if (enemies.get(2).getCoord(). y > area.y ) {
                enemies.remove(2);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 2 - odstraněn - y");
            }
            if (enemies.get(3).getCoord().x > area.x ) {
                enemies.remove(3);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 3 - odstraněn - x");
            } else if (enemies.get(3).getCoord(). y > area.y ) {
                enemies.remove(3);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 3 - odstraněn - y");
            }
            if (enemies.get(4).getCoord().x > area.x ) {
                enemies.remove(4);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 4 - odstraněn - x");
            } else if (enemies.get(4).getCoord(). y > area.y ) {
                enemies.remove(4);
                enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                System.out.println("Nepřítel 4 - odstraněn - y");
            }
            if (level == 4) {
                if (enemies.get(1).getCoord().x > area.x ) {
                    enemies.remove(1);
                    enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                    System.out.println("Nepřítel 1 - odstraněn - x");
                } else if (enemies.get(1).getCoord(). y > area.y ) {
                    enemies.remove(1);
                    enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                    System.out.println("Nepřítel 1 - odstraněn - y");
                }
                if (enemies.get(2).getCoord().x > area.x ) {
                    enemies.remove(2);
                    enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                    System.out.println("Nepřítel 2 - odstraněn - x");
                } else if (enemies.get(2).getCoord(). y > area.y ) {
                    enemies.remove(2);
                    enemies.add(new Enemy(0, 0, "Enemy_down.png" ));
                    System.out.println("Nepřítel 2 - odstraněn - y");
                }
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

    public void setMinusTime(boolean minusTime) {
        this.minusTime = minusTime;
    }
}