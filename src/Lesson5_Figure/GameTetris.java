package Lesson5_Figure;

//  https://www.youtube.com/watch?v=sCit9jzDSrE&t=34s
//  https://github.com/biblelamp/JavaExercises/tree/master/Games
//  начало в 1час 20 минута







import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class GameTetris {
    final String TITLE_OF_PROGRAM = "Tetris  Lesson5_Figure";
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
    private GameTetris() {
        frame = new JFrame(TITLE_OF_PROGRAM);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FIELD_WIDTH*BLOCK_SIZE+FIELD_DX, FIELD_HEIGHT*BLOCK_SIZE+FIELD_DY);
        frame.setLocation(START_LOCATION,START_LOCATION);
        frame.setResizable(false);
        canvas.setBackground(Color.BLACK);
//        frame.add(canvas);
        System.out.println( "bifor KeyListener");

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (!gameOver) {
                    if (keyEvent.getKeyCode() == DOWN) {
                        figure.drop();
                        System.out.println( "Нажата кнопка ВНИЗ");
                    }
                    if (keyEvent.getKeyCode() == UP) {
                        figure.rotate();
                        System.out.println( "Нажата кнопка ВВЕРХ");
                    }
                    if (keyEvent.getKeyCode() == LEFT || keyEvent.getKeyCode() == RIGHT) {
                        figure.move(keyEvent.getKeyCode());
                        System.out.println( "Нажата кнопка ВПРАВО ИЛИ ВЛЕВО");
                    }
                }
                canvas.repaint();
            }
        });

//        canvas.setBackground(Color.BLACK);
        frame.getContentPane().add(BorderLayout.CENTER,canvas);
        frame.setVisible(true);

        //нициализация дна стакана
        Arrays.fill(mine[FIELD_HEIGHT], 1);
    }

    private void go() {


//        Главный цикл игры
        while (!gameOver){
            try {
                Thread.sleep(SHOW_DELAY);
            }catch (Exception e ) {
                e.printStackTrace();
            }
            canvas.repaint();
            if (figure.isTouchGround()){
                figure.leaveOnTheGround();
                checkFilling();
                figure = new Figure();
                gameOver = figure.isCrossGround();
            }else{
                figure.stepDown();
            }

        }
    }


    void checkFilling(){};

    class Figure{
        private ArrayList<Block> figure = new ArrayList<>();
        private int [][] shape = new int [4][4];
        private int type,size,color;
        private int x = 3,y = 0;

        public Figure() {
            type = random.nextInt(SHAPES.length);
            size = SHAPES[type][4][0];
            color = SHAPES[type][4][1];
            if (size==4 ) y = -1;
            for(int i = 0; i<size; i++){
                System.arraycopy(SHAPES[type][i], 0, shape[i], 0, SHAPES[type][i].length);
                createFromShape();
            }
        }
        void createFromShape(){
            for(int x = 0; x < size; x++) {
                for(int y = 0; y < size; y++) {
                    if (shape[y][x] == 1){
                        figure.add(new Block(x + this.x, y + this.y));
                    }
                }
            }
        };
        void drop(){};
        void rotate(){};
        void move(int KeyCode){};
        void leaveOnTheGround(){};
        void stepDown(){
            for (Block block : figure) {
                block.setY(block.getY() + 1);
            }
            y++;
        };
        boolean isTouchGround(){return false;};
        boolean isCrossGround(){return false;};
        void paint(Graphics g ){
            for( Block block: figure) block.paint(g, color);
        };


    }
    class Block {
        private int x,y;
        public Block(int x,int y) {
            setX(x);
            setY(y);
        }

        public void setX(int x) { this.x = x; }
        public void setY(int y) { this.y = y; }

        public int getX() { return x; }
        public int getY() { return y; }

        void paint(Graphics g, int color){
            g.setColor(new Color(color));
            g.drawRoundRect(x*BLOCK_SIZE+1,y*BLOCK_SIZE+1,BLOCK_SIZE-2,BLOCK_SIZE-2, ARC_RADIUS,ARC_RADIUS);
        }

    }

    public class Canvas extends JPanel{


        @Override
        public void paint(Graphics g){
            super.paint(g);
            figure.paint(g);

        }
    }

    //Окончили на 1 -----
}