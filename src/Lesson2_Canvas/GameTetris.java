package Lesson2_Canvas;

//  https://www.youtube.com/watch?v=sCit9jzDSrE&t=34s
//  https://github.com/biblelamp/JavaExercises/tree/master/Games


import javax.swing.*;
import java.awt.*;
import java.util.Random;

class GameTetris {
    final String TITLE_OF_PROGRAM = "Tetris";
    final int BLOCK_SIZE = 25;      // size of one block / размер блока в пикселях
    final int ARC_RADIUS = 6;       // рахмер скругления
    final int FIELD_WIDTH = 10;     // size game field in block / ширина игрового поля в блоках
    final int FIELD_HEIGHT = 18;    // высота игрового поля в блоках
    final int START_LOCATION = 180; // расположение
    final int FIELD_DX = 7;         // determined experimentally / определено эксперементально
    final int FIELD_DY = 26;        // determined experimentally / определено эксперементально
    final int LEFT = 37;            // key codes / код кнопок
    final int UP = 38;              // key codes / код кнопок
    final int RIGHT = 39;           // key codes / код кнопок
    final int DOWN = 40;            // key codes / код кнопок
    final int SHOW_DELAY = 700;     // delay for animation . задержка для анимации
    final int[][][] SHAPES = {
            {{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {4, 0x00f0f0}}, // I
            {{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {4, 0xf0f000}}, // O
            {{1, 0, 0, 0}, {1, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0x0000f0}}, // J
            {{0, 0, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0xf0a000}}, // L
            {{0, 1, 1, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0x00f000}}, // S
            {{1, 1, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0xa000f0}}, // T
            {{1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {3, 0xf00000}}  // Z
    };
    final int[] SCORES = {100, 300, 700, 1500}; // points table / таблица очков
    int gameScore = 0;                          // gameScore / счет игры
    int[][] mine = new int[FIELD_HEIGHT + 1][FIELD_WIDTH]; // mine/glass  шахта/стекло
    JFrame frame;
    Canvas canvas = new Canvas();
    Random random = new Random();
    Figure figure = new Figure();
    boolean gameOver = false;
    final int[][] GAME_OVER_MSG = {
            {0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0},
            {0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0},
            {1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0}};




    public static void main(String[] args) {
        new GameTetris().go();
        System.out.println("new GameTetris().go();");

    }
    private GameTetris() {}

    private void go() {
        frame = new JFrame(TITLE_OF_PROGRAM);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FIELD_WIDTH*BLOCK_SIZE+FIELD_DX, FIELD_HEIGHT*BLOCK_SIZE+FIELD_DY);
        frame.setLocation(START_LOCATION,START_LOCATION);
        frame.setResizable(false);
        canvas.setBackground(Color.BLACK);
        frame.setVisible(true);
    }

    class Figure{}
    class Block {}
    public class Canvas extends JPanel{
        @Override
        public void paint(Graphics g){
            super.paint(g);

        }
    }

    //Окончили на 43
}