import java.awt.*;

public class MouseFinder {
    public static void main(final String[] args) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        System.out.println(point.x + ", " + point.y);
    }
}