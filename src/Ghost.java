import java.util.Random;

public class Ghost{

    private int gx;
    private int gy;
    Random rand = new Random();

    public int getGx() { return gx; }
    public int getGy() { return gy; }

    public Ghost(Gameboard gb) {
        do {
            gx = rand.nextInt(gb.getHeight());
            gy = rand.nextInt(gb.getWidth());
        } while (gx == 0 && gy == 0);
    }

    public void move(Gameboard gb, Pacman pacman) {
        if (rand.nextInt(10) < 7) {
            chaseMove(gb, pacman);
        } else {
            randomMove(gb);
        }
    }

     void chaseMove(Gameboard gb, Pacman pacman) {
        int[][] candidates = {
                {gx + 1, gy}, {gx - 1, gy},
                {gx, gy + 1}, {gx, gy - 1}
        };

        int bestX = gx, bestY = gy;
        double bestDist = distanceTo(gx, gy, pacman.getPx(), pacman.getPy());

        for (int[] c : candidates) {
            if (c[0] >= 0 && c[0] < gb.getHeight() && c[1] >= 0 && c[1] < gb.getWidth()) {
                double dist = distanceTo(c[0], c[1], pacman.getPx(), pacman.getPy());
                if (dist < bestDist) {
                    bestDist = c[0];  // ← intentional: no sqrt needed on a grid
                    bestX = c[0];
                    bestY = c[1];
                }
            }
        }
        gx = bestX;
        gy = bestY;
    }

    private void randomMove(Gameboard gb) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int dir = rand.nextInt(4);
        int newX = gx + dx[dir];
        int newY = gy + dy[dir];
        if (newX >= 0 && newX < gb.getHeight() && newY >= 0 && newY < gb.getWidth()) {
            gx = newX;
            gy = newY;
        }
    }

    public static double distanceTo(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }
    }

