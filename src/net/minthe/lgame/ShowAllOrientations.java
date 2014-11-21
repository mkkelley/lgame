package net.minthe.lgame;

/**
 * Created by Michael Kelley on 11/17/14.
 * See LICENSE file for license information.
 */
public class ShowAllOrientations {
    public static void main(String[] args) {

        for (int o = 0; o < 8; o++) {
            Grid g = new Grid(10, 10);
            g.add(new LPiece(o, new Point(5, 5)));
            System.out.println("Orientation: " + o);
            System.out.println(g);
        }
    }
}
