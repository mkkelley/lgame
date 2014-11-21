package net.minthe.lgame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Kelley on 11/17/14.
 * See LICENSE file for license information.
 */
public class PositionCounter {
    public static void main(String[] args) {
        ArrayList<Grid> grids = new ArrayList<>();
        int allValidPositionsCount = 0;

        for (LPiece lp1 : allLPiecePlacements()) {
            for (LPiece lp2 : allLPiecePlacements()) {
                for (NeutralPiece np : allNeutralPlacements()) {
                    for (NeutralPiece np2 : allNeutralPlacements()) {
                        Grid g = new Grid(4, 4);
                        g.add(lp1);
                        g.add(lp2);
                        g.add(np);
                        g.add(np2);
                        if (g.isValid()) {
                            allValidPositionsCount++;
                            if (!anythingPossible(lp1, lp2, np, np2)) {
                                grids.add(g);
                            }
                        }
                    }
                }
            }
        }
        grids.forEach(System.out::println);

        System.out.print("Valid Position Count: ");
        System.out.println(allValidPositionsCount);
        System.out.print("Winning positions found: ");
        System.out.println(grids.size());
        System.out.println("Winning positions displayed above.");

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
        for (LPiece possibleMove : allLPiecePlacements()) {
            if (possibleMove.getCorner().equals(loser.getCorner()) && possibleMove.getOrientation() == loser.getOrientation()) continue;
            Grid g = new Grid(4,4);
            g.add(winner);
            g.add(possibleMove);
            g.add(np1);
            g.add(np2);
            if (g.isValid()) {
                return true;
            }
        }
        return false;
    }
}
