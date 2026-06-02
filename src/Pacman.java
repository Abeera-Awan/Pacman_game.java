public class Pacman {

    private int px;
    private int py;
    private int score;

    public Pacman() {
        this.px    = 0;
        this.py    = 0;
        this.score = 0;
    }

    public int getPx()    { return px;    }
    public int getPy()    { return py;    }
    public int getScore() { return score; }

    public void addScore(int points) {
        score += points;
    }

    public void move(String arrow, Gameboard gb) {
        int newX = px;
        int newY = py;

        switch (arrow) {
            case ">": newY++; break;
            case "<": newY--; break;
            case "^": newX--; break;
            case "v": newX++; break;
            default:
                System.out.println("invalid move");
        }

        if (newX >= 0 && newX < gb.getHeight() && newY >= 0 && newY < gb.getWidth()) {
            px = newX;
            py = newY;
        }
    }
}

