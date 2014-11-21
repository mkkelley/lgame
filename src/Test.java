import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Kelley on 11/17/14.
 * See LICENSE file for license information.
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<Grid> grids = new ArrayList<>();

        for (LPiece lp1 : allLPiecePlacements()) {
            for (LPiece lp2 : allLPiecePlacements()) {
                for (NeutralPiece np : allNeutralPlacements()) {
                    for (NeutralPiece np2 : allNeutralPlacements()) {
                        Grid g = new Grid(4, 4);
                        g.add(lp1);
                        g.add(lp2);
                        g.add(np);
                        g.add(np2);
                        if (g.isValid() && !anythingPossible(lp1, lp2, np, np2)) {
                            grids.add(g);
                        }
                    }
                }
            }
        }

        System.out.println(grids.size());

        grids.forEach(System.out::println);
    }

    public static List<LPiece> allLPiecePlacements() {
        List<LPiece> placements = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            int orientation = i / 16;
            int pos = i % 16;
            int row = pos / 4;
            int col = pos % 4;
            placements.add(new LPiece(orientation, new Point(row, col)));
        }
        return placements;
    }

    public static List<NeutralPiece> allNeutralPlacements() {
        List<NeutralPiece> placements = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            int row = i / 4;
            int col = i % 4;
            placements.add(new NeutralPiece(new Point(row, col)));
        }
        return placements;

    }

    public static boolean anythingPossible(LPiece winner, LPiece loser, NeutralPiece np1, NeutralPiece np2) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                for (int o = 0; o < 8; o++) {
                    if (r == loser.getCorner().row && c == loser.getCorner().col && o == loser.getOrientation()) continue;
                    LPiece possibleMove = new LPiece(o, new Point(r,c));
                    Grid g = new Grid(4,4);
                    g.add(winner);
                    g.add(possibleMove);
                    g.add(np1);
                    g.add(np2);
                    if (g.isValid()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int cpf(int a, int b) {
        return (a + b)*(a + b + 1)/2 + b;
    }
}
