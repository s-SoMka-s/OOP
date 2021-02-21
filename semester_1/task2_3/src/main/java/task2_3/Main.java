package task2_3;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Main {
    public static void main(String args[]) {
        var boundary = new RectBoundary(200, 200, 200, 200);
        var qt = new QuadTree(boundary, 1);

        var rand = new Random(1000);
        var maxW = boundary.halfWidth * 2;
        var maxH = boundary.halfHeight * 2;
        for (int i = 0; i < 1550; i++) {
            var point = new Point(rand.nextDouble() * maxW, rand.nextDouble() * maxH);
            qt.insert(point);
        }

        var jF = new JFrame("QuadTree");
        jF.setSize(900, 900);
        jF.setVisible(true);
        jF.add(new Board(qt));
        jF.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}

class Board extends Panel {
    private QuadTree quadTree;

    public Board(QuadTree qt) {
        this.quadTree = qt;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawQT(g, this.quadTree);
    }

    private void drawQT(Graphics g, QuadTree qt) {
        if (qt == null) {
            return;
        }

        var g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLACK);

        var boundary = qt.getBoundary();

        var w = boundary.halfWidth * 2;
        var h = boundary.halfHeight * 2;
        var x = boundary.centerX - w / 2;
        var y = boundary.centerY - h / 2;

        var rect = new Rectangle2D.Double(x, y, w, h);
        g2d.draw(rect);

        var subTrees = qt.getSubtrees();
        if (subTrees.isEmpty()){
            return;
        }
        for (var subtree : subTrees) {
            drawQT(g, subtree);
        }

    }
}
