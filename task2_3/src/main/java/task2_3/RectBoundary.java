package task2_3;

public class RectBoundary {
    public double centerX;
    public double centerY;
    public double halfWidth;
    public double halfHeight;

    public RectBoundary(double centerX, double centerY, double halfWidth, double halfHeight) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
    }

    public boolean contains(Point point) {
        return (point.x >= this.centerX - this.halfWidth &&
                point.x <= this.centerX + this.halfWidth &&
                point.y >= this.centerY - this.halfHeight &&
                point.y <= this.centerY + this.halfHeight);
    }

    public boolean intersects(RectBoundary boundary) {
        return !(boundary.centerX - boundary.halfWidth > this.centerX + this.halfWidth ||
                 boundary.centerX + boundary.halfWidth < this.centerX - this.halfWidth ||
                 boundary.centerY - boundary.halfHeight > this.centerY + this.halfHeight ||
                 boundary.centerY + boundary.halfHeight < this.centerY - this.halfHeight);
    }
}

