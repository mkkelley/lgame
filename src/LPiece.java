import java.util.ArrayList;

/**
 * Created by Michael Kelley on 11/13/14.
 * See LICENSE file for license information.
 */
public class LPiece extends Piece {
    private int orientation; //range 0..7
    private Point corner;

    public LPiece(int orientation, Point corner) {
        this.orientation = orientation;
        this.corner = corner;
    }

    public Point getCorner() {
        return corner;
    }

    public int getOrientation() {
        return orientation;
    }

    public ArrayList<Point> getOccupiedPoints() {
        ArrayList<Point> out = new ArrayList<>();
        int row = corner.row;
        int col = corner.col;
        out.add(corner);
        if (orientation % 4 == 0) {
            out.add(new Point(row - 1, col));
            out.add(new Point(row - 2, col));
            out.add(new Point(row, col + (orientation/4)*2 - 1));
        } else if (orientation % 4 == 1) {
            out.add(new Point(row, col + 1));
            out.add(new Point(row, col + 2));
            out.add(new Point(row + (orientation/4)*2 - 1, col));
        } else if (orientation % 4 == 2) {
            out.add(new Point(row + 1, col));
            out.add(new Point(row + 2, col));
            out.add(new Point(row, col - (orientation/4)*2 + 1));
        } else {
            out.add(new Point(row, col - 1));
            out.add(new Point(row, col - 2));
            out.add(new Point(row - (orientation/4)*2 + 1, col));
        }
        return out;
    }
}
