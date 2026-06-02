import java.util.Scanner;

public class Game {

    private static final int no_ghosts = 2;
    private static final int no_food  = 4;

    private Gameboard board;
    private Pacman    pacman;
    private Ghost[]   ghosts;
    private Food[]    foods;
    Scanner sc;

    public Game(Scanner sc) {
        this.sc=sc;
        board  = new Gameboard();
        pacman = new Pacman();
        ghosts = new Ghost[no_ghosts];
        for (int i = 0; i < no_ghosts; i++) {
            ghosts[i] = new Ghost(board);
        }
        foods = new Food[no_food ];
        for (int i = 0; i < no_food ; i++) {
            foods[i] = new Food(board, ghosts, foods, i);
        }
    }

    public void run() {
        System.out.println("----------- PACMAN --------------");
        System.out.println("Controls: > < ^ v ");
        System.out.println();

        boolean gameRunning = true;

        while (gameRunning) {
            board.display(pacman, ghosts, foods);
            System.out.println();
            System.out.println("Score: " + pacman.getScore() + "\nEnter move: ");
            String move=sc.next();
            pacman.move(move, board);

            checkFoodCollision();
            if (checkGhostCollision()) {
                board.display(pacman, ghosts, foods);
                System.out.println("❌❌YOU LOSE❌❌");
                gameRunning = false;
            }

            if (allFoodEaten()) {
                board.display(pacman, ghosts, foods);
                System.out.println("🎉🎉YOU WIN🎉🎉");
                System.out.println("Final Score: " + pacman.getScore());
                gameRunning = false;
            }
        }
    }

    private void checkFoodCollision() {
        for (Food f : foods) {
            if (!f.isEaten() && pacman.getPx() == f.getFx() && pacman.getPy() == f.getFy()) {
                f.setEaten(true);
                pacman.addScore(10);
            }
        }
    }

    private boolean checkGhostCollision() {
        for (Ghost g : ghosts) {
            g.move(board, pacman);
            if (pacman.getPx() == g.getGx() && pacman.getPy() == g.getGy()) {
                return true;
            }
        }
        return false;
    }

    private boolean allFoodEaten() {
        for (Food f : foods) {
            if (!f.isEaten()) return false;
        }
        return true;
    }
}
