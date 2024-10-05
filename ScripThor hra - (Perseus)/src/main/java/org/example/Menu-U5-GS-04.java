package org.example;


public class Menu {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GamePoseidon();
            }
        });
    }
}
