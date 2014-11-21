package net.minthe.lgame;

/**
 * Created by Michael Kelley on 11/13/14.
 * See LICENSE file for license information.
 */
public class Point {
    public final int row;
    public final int col;

    public Point(int r, int c) {
        row = r;
        col = c;
    }

    // Cantor's pairing function
    public int hashCode() {
        return (row + col)*(row + col + 1)/2 + col;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Point)) return false;
        Point p = (Point)o;
        return p.row == this.row && p.col == this.col;
    }
}
