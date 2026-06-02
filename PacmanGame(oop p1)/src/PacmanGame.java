import java.util.*;

public class PacmanGame{
    public static void main(String [] args){
        System.out.println("Game Started");
        System.out.println("Use ><^ or v to move.");

        Gameboard board = new Gameboard();
        Pacman pacman = new Pacman();
        Ghost[] ghosts = {new Ghost(board), new Ghost(board)};
        Food[] foods={new Food(board), new Food(board), new Food(board), new Food(board)};

        boolean game_running = true;
        Scanner sc = new Scanner(System.in);

        while (game_running){
            board.display(pacman, ghosts, foods);
            String arrow= sc.next();
            pacman.move(arrow, board);

            boolean food_finished= true;
            for(Food f: foods) {
                if (!f.isEaten() && pacman.getPx() == f.getFx() && pacman.getPy() == f.getFy())
                    f.setEaten(true);
                if (!f.isEaten())
                    food_finished = false;
            }
            for (Ghost g: ghosts){
                g.move(board);
                if (pacman.getPx()==g.getGx() && pacman.getPy()== g.getGy()){
                    board.display(pacman, ghosts, foods);
                    System.out.println("YOU LOSE.GAME OVER");
                    game_running = false;
                }
            }

            if (food_finished) {
                board.display(pacman, ghosts, foods);
                System.out.println("YOU WIN! Game over.");
                game_running = false;
            }
        }
    }
}
class Gameboard{
    private final int height = 5;
    private final int width = 7;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void display(Pacman pacman, Ghost[] ghosts, Food[] foods){
        for(int i = 0; i< height; i++){
            for (int j=0; j < width; j++ ){

                if (pacman.getPx()==i && pacman.getPy() == j){
                    System.out.print("P ");
                }
                else {
                    boolean printed = false;

                    for (Ghost g : ghosts) {
                        if (g.getGx()==i && g.getGy()==j) {
                            System.out.print("G ");
                            printed = true;
                            break;
                        }
                    }
                    if (!printed) {
                        for (Food f : foods) {
                            if (f.getFx()==i && f.getFy()==j && !f.isEaten()) {
                                System.out.print("* ");
                                printed = true;
                                break;
                            }
                        }
                    }
                    if (!printed) {
                        System.out.print(". ");
                    }
                }
            }
            System.out.println();
        }
    }
}
class Pacman{
    private int Px;
    private int Py;

    public Pacman(){
        this.Px= 0;
        this.Py=0;
    }

    public int getPx() {
        return Px;
    }

    public int getPy() {
        return Py;
    }

    public void move(String arrow, Gameboard gb){
        int X = Px;
        int Y = Py;

        switch (arrow){
            case ">":
                Y++;
                break;
            case "<":
                Y--;
                break;
            case "^":
                X--;
                break;
            case "v":
                X++;
                break;
        }
        if (X >= 0 && X < gb.getHeight() && Y >= 0 && Y < gb.getWidth()) {
            Px = X;
            Py = Y;
        }
    }
}
class Ghost{
    private int Gx;
    private int Gy;
    Random rand = new Random();

    public int getGx() {
        return Gx;
    }

    public int getGy() {
        return Gy;
    }

    public Ghost(Gameboard gb){
        Random rand = new Random();
        do {
            Gx = rand.nextInt(gb.getHeight());
            Gy = rand.nextInt(gb.getWidth());
        }while(Gx==0 && Gy ==0);
    }

    public void move(Gameboard gb){
        int X=Gx;
        int Y=Gy;
        int number= rand.nextInt(4);
        switch (number){
            case 0:
                X++;
                break;
            case 1:
                X--;
                break;
            case 2:
                Y++;
                break;
            case 3:
                Y--;
                break;
        }
        if (X >= 0 && X < gb.getHeight() && Y >= 0 && Y < gb.getWidth()) {
            Gx = X;
            Gy = Y;
        }
    }
}
class Food{
    private int Fx;
    private int Fy;
    private boolean eaten;

    public Food(Gameboard gb){
        Random rand= new Random();
        this.Fx= rand.nextInt(gb.getHeight());
        this.Fy=rand.nextInt(gb.getWidth());
        this.eaten=false;
    }

    public int getFx() {
        return Fx;
    }

    public int getFy() {
        return Fy;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

}
