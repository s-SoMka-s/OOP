package task2_3;

import java.util.ArrayList;

public class QuadTree {
    public RectBoundary boundary; // Границы дерева
    public int capacity; // Вместимость каждой секции
    public QuadTree northWest;
    public QuadTree northEast;
    public QuadTree southWest;
    public QuadTree southEast;
    public boolean isDivided;
    public ArrayList<Point> points;

    /**
     * Конструктор для создания дерева квадрантов
     * @param boundary - Стартовые границы дерева - самый большой блок
     * @param capacity - Максимальное колличество точек в одном квадрате.
     *                   При привышении квадрат разделяется на 4 части
     */
    public QuadTree(RectBoundary boundary, int capacity) {
        if (boundary == null) {
            throw new IllegalStateException("boundary is null");
        }

        if (capacity < 1) {
            throw new IllegalStateException("capacity must be greater than 0");
        }

        this.boundary = boundary;
        this.capacity = capacity;
        this.points = new ArrayList<>();
        this.isDivided = false;
        this.southEast = null;
        this.southWest = null;
        this.northEast = null;
        this.northWest = null;
    }

    /**
     * Метод для вставки точки в дерево
     * @param point - Объект точка, с координатами X и Y
     * @return
     * false - если вставить не удалось
     * true - если вставка прошла успешно
     */
    public boolean insert(Point point) {
        if (!this.boundary.contains(point)) {
            return false;
        }

        if (this.points.size() < this.capacity) {
            this.points.add(point);
            return true;
        }

        if (!this.isDivided) {
            this.subdivide();
            this.isDivided = true;
        }

        return (this.northWest.insert(point) ||
                this.northEast.insert(point) ||
                this.southEast.insert(point) ||
                this.southWest.insert(point));
    }

    public boolean delete(Point point) {
        if (!this.boundary.contains(point)) {
            return false;
        }
        return true;
    }

    /**
     * Запрос на поиск точек в заданных границах
     * @param boundary - Границы, в которых ищем точки.
     * @param found - Список под ответ. Отчистится, если не пустой.
     * @return Список точек, входящих в данную границу
     */
    public ArrayList<Point> query(RectBoundary boundary, ArrayList<Point> found) {
        if (!found.isEmpty()){
            found = new ArrayList<>();
        }

        if (!boundary.intersects(this.boundary)) {
            return found;
        }

        for (var point : this.points) {
            if (boundary.contains(point)){
                found.add(point);
            }
        }

        if (this.isDivided){
            this.northWest.query(boundary, found);
            this.northEast.query(boundary, found);
            this.southWest.query(boundary,found);
            this.southEast.query(boundary, found);
        }

        return found;
    }

    private void subdivide() {
        var x = this.boundary.centerX;
        var y = this.boundary.centerY;
        var w = this.boundary.halfWidth;
        var h = this.boundary.halfHeight;

        var neBoundary = new RectBoundary(x + w / 2, y - h / 2, w / 2, h / 2);
        this.northEast = new QuadTree(neBoundary, this.capacity);

        var nwBoundary = new RectBoundary(x - w / 2, y - h / 2, w / 2, h / 2);
        this.northWest = new QuadTree(nwBoundary, this.capacity);

        var seBoundary = new RectBoundary(x + w / 2, y + h / 2, w / 2, h / 2);
        this.southEast = new QuadTree(seBoundary, this.capacity);

        var swBoundary = new RectBoundary(x - w / 2, y + h / 2, w / 2, h / 2);
        this.southWest = new QuadTree(swBoundary, this.capacity);
    }
}
