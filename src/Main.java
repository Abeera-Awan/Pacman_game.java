import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // create once
        boolean game_loop = true;
        while (game_loop) {
            Game game = new Game(sc);
            game.run();
            System.out.println("Play another round? (yes/no)");
            String ans = sc.next().toLowerCase();
            if (!ans.equals("yes"))
                game_loop = false;
        }
       sc.close();
    }
}