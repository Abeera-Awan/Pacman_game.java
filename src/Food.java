import java.util.Random;

public class Food {

    private int     fx;
    private int     fy;
    private boolean eaten;

    Random rand = new Random();

    public Food(Gameboard gb, Ghost[] ghosts, Food[] existingFoods, int count) {
        while (!isValidSpawn(ghosts, existingFoods, count)){
            fx = rand.nextInt(gb.getHeight());
            fy = rand.nextInt(gb.getWidth());
        }
        this.eaten = false;
    }

    private boolean isValidSpawn(Ghost[] ghosts, Food[] existingFoods, int count) {
        if (fx == 0 && fy == 0) return false;

        for (Ghost g : ghosts) {
            if (g != null && g.getGx() == fx && g.getGy() == fy) return false;
        }

        for (int i = 0; i < count; i++) {
            if (existingFoods[i] != null && existingFoods[i].getFx() == fx && existingFoods[i].getFy() == fy) {
                return false;
            }
        }

        return true;
    }

    public int     getFx()                   { return fx;    }
    public int     getFy()                   { return fy;    }
    public boolean isEaten()                 { return eaten; }
    public void    setEaten(boolean eaten)   { this.eaten = eaten; }
}

