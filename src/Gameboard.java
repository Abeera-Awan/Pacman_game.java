import java.awt.*;

public class Gameboard {

    private final int height = 5;
    private final int width  = 7;

    public int getHeight() { return height; }
    public int getWidth()  { return width;  }

    public void display(Pacman pacman, Ghost[] ghosts, Food[] foods) {

        for (int i = 0; i < height; i++) {
            System.out.print("|");
            for (int j = 0; j < width; j++) {
                if (pacman.getPx() == i && pacman.getPy() == j) {
                    System.out.print(Colors.BOLD_YELLOW+"P "+Colors.RESET);
                } else {
                    boolean printed = false;

                    for (Ghost g : ghosts) {
                        if (g.getGx() == i && g.getGy() == j) {
                            System.out.print(Colors.BOLD_BLUE+"G "+Colors.RESET);
                            printed = true;
                            break;
                        }
                    }
                    if (!printed) {
                        for (Food f : foods) {
                            if (f.getFx() == i && f.getFy() == j && !f.isEaten()) {
                                System.out.print(Colors.BOLD_GREEN+"* "+Colors.RESET);
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
            System.out.print("|");
            System.out.println();
        }
    }
    class Colors {
        public static final String RESET = "\u001B[0m";
        public static final String BOLD_GREEN = "\u001B[1;92m";
        public static final String BOLD_YELLOW = "\u001B[1;93m";
        public static final String BOLD_BLUE = "\u001B[1;96m";
    }
}


