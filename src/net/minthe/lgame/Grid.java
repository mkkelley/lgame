package net.minthe.lgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Michael Kelley on 11/17/14.
 * See LICENSE file for license information.
 */
public class Grid {
    private ArrayList<Piece> pieces;
    private int rows;
    private int cols;

    public Grid(int rows, int cols) {
        pieces = new ArrayList<>();
        this.rows = rows;
        this.cols = cols;
    }

    public void add(Piece p) {
        pieces.add(p);
    }

    public boolean isValid() {
        return !hasOverlaps() && allPointsInBounds();
    }

    private boolean hasOverlaps() {
        List<Point> points = getOccupiedPoints();
        points.sort((a,b) -> a.hashCode() - b.hashCode());
        for (int i = 0; i < points.size() - 1; i++) {
            if (points.get(i).equals(points.get(i + 1))) {
                return true;
            }
        }
        return false;
    }

    private boolean allPointsInBounds() {
        List<Point> points = getOccupiedPoints();
        for (Point p : points) {
            if (p.row < 0 || p.row >= rows || p.col < 0 || p.col >= cols) {
                return false;
            }
        }
        return true;
    }

    public List<Point> getOccupiedPoints() {
        return pieces.stream().map(Piece::getOccupiedPoints).flatMap(Collection::stream).collect(Collectors.toList());
    }


    private List<Point> rotateRight(List<Point> points, int n) {
        if (n == 0) {
            return points;
        }
        List<Point> rotatedPoints = points.stream().map(point -> new Point(rows - 1 - point.col, point.row)).collect(Collectors.toList());
        return rotateRight(rotatedPoints, n - 1);
    }

    /**
     *
     * @param g A grid which may or may not be similar.
     * @return Whether or not the grid is the same considering all rotation and reflection.
     */
    public boolean isSimilar(Grid g) {
        if (g.getOccupiedPoints().containsAll(this.getOccupiedPoints())) {
            return true;
        }
        return false;
        // rotate 1 time, 2 times, 3 times.
//        for (int i = 1; i < 4; i++) {
//        }
    }

    public String toString() {
        char[] symbols = {'x', 'o', '.', '-'};
        int index = 0;
        char[][] buffer = new char[rows][cols * 2];
        for (char[] row : buffer) {
            Arrays.fill(row, ' ');
        }
        for (Piece p : pieces) {
            List<Point> points = p.getOccupiedPoints();
            for (Point point : points) {
                buffer[point.row][point.col * 2] = symbols[index];
            }
            index++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols * 2; j++) {
                sb.append(buffer[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

//    public String toString() {
//        List<Point> points = getOccupiedPoints();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                if (points.contains(new Point(i, j))) {
//                    sb.append('x');
//                } else {
//                    sb.append(' ');
//                }
//            }
//            sb.append('\n');
//        }
//        return sb.toString();
//    }
}
