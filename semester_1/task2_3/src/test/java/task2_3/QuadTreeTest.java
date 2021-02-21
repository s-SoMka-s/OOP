package task2_3;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class QuadTreeTest {
    @Test
    public void Test1()
    {
        var boundary = new RectBoundary(200, 200, 200, 200);
        var qt = new QuadTree(boundary, 4);

        var rand = new Random(1000);
        var maxW = boundary.halfWidth;
        var maxH = boundary.halfHeight;
        for (int i = 0; i < 50; i++) {
            var point = new Point(rand.nextDouble()*maxW, rand.nextDouble()*maxH);
            qt.insert(point);
        }


    }
}