package net.minthe.lgame;

import java.util.ArrayList;

/**
 * Created by Michael Kelley on 11/13/14.
 * See LICENSE file for license information.
 */
public class NeutralPiece extends Piece {
    private Point point;
    public NeutralPiece(Point pt) {
        point = pt;
    }

    @Override
    public ArrayList<Point> getOccupiedPoints() {
        ArrayList<Point> out = new ArrayList<>();
        out.add(point);
        return out;
    }
}
