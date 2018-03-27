import java.awt.*;

/**
 * This class is used to tell the {@link Ant} object which way to move. The
 * {@link Point} tells the {@link Ant} how many x and y squares to move,
 * which simulates directions.
 */
public class Direction {
    public static Point NORTH = new Point(0,  1);
    public static Point EAST  = new Point(1,  0);
    public static Point SOUTH = new Point(0, -1);
    public static Point WEST  = new Point(-1, 0);
}
