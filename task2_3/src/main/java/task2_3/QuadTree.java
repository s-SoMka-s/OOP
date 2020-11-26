package task2_3;

import java.util.ArrayList;

public class QuadTree {
    private RectBoundary boundary; // Границы дерева
    private int capacity; // Вместимость каждой секции
    private QuadTree northWest;
    private QuadTree northEast;
    private QuadTree southWest;
    private QuadTree southEast;
    private boolean isDivided;
    private ArrayList<Point> points;
    private QuadTree parent;

    /**
     * Конструктор для создания дерева квадрантов
     *
     * @param boundary - Стартовые границы дерева - самый большой блок
     * @param capacity - Максимальное колличество точек в одном квадрате.
     *                 При привышении квадрат разделяется на 4 части
     */
    public QuadTree(RectBoundary boundary, int capacity) {
        if (boundary == null) {
            throw new IllegalArgumentException("boundary is null");
        }

        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }

        this.boundary = boundary;
        this.capacity = capacity;
        this.points = new ArrayList<>();
        this.isDivided = false;
        this.southEast = null;
        this.southWest = null;
        this.northWest = null;
        this.northEast = null;
        this.parent = null;
    }

    /**
     * @return границы текущего дерева квадрантов
     */
    public RectBoundary getBoundary() {
        return this.boundary;
    }

    /**
     * @return Поддеревья текущего дерева,
     * пустой - если поддерева нет
     */
    public ArrayList<QuadTree> getSubtrees() {
        if (!this.isDivided) {
            return new ArrayList<>();
        }

        var result = new ArrayList<QuadTree>();
        result.add(this.northEast);
        result.add(this.northWest);
        result.add(this.southEast);
        result.add(this.southWest);

        return result;
    }

    /**
     * Метод для вставки точки в дерево
     *
     * @param point - Объект точка, с координатами X и Y
     * @return false - если вставить не удалось
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


    /**
     * Удаляет точку с текущими координатами
     *
     * @param point  - координаты, по которым надо удалить
     * @return false - удалить не удалось
     * true - удалить удалось успешно
     */
    public boolean delete(Point point) {
        // Точка не попадает в границы - в пекло её
        if (!this.boundary.contains(point)) {
            return false;
        }

        // Точка совпала с узлом квадрата
        if (Double.compare(point.x, this.boundary.centerX) == 0 && Double.compare(point.y, this.boundary.centerY) == 0) {
            // Удаляем всех детей этого узла
            this.northWest = null;
            this.northEast = null;
            this.southWest = null;
            this.southEast = null;

            // Проверим, пустые ли у него братья
            if (this.parent != null) {
                this.parent = this.parent.rebalanced();
            }

            return true;
        }

        // Нельзя дальше делить - в пекло эту таску
        if (!this.isDivided) {
            return false;
        }

        return (this.southEast.delete(point) ||
                this.southWest.delete(point) ||
                this.northWest.delete(point) ||
                this.northEast.delete(point));
    }

    private QuadTree rebalanced() {
        var subTrees = this.getSubtrees();
        if (subTrees.isEmpty()) {
            return null;
        }

        var isChildrenEmpty = this.northEast.isDivided || this.northWest.isDivided || this.southEast.isDivided || this.southWest.isDivided;

        if (isChildrenEmpty) {
            this.isDivided = false;

            this.parent.rebalanced();
        }

        return this;
    }

    /**
     * Запрос на поиск точек в заданных границах
     *
     * @param boundary - Границы, в которых ищем точки.
     * @param found    - Список под ответ.
     * @return Список точек, входящих в данную границу
     */
    public ArrayList<Point> query(RectBoundary boundary, ArrayList<Point> found) {
        if (!found.isEmpty()) {
            found = new ArrayList<>();
        }

        if (!boundary.intersects(this.boundary)) {
            return found;
        }

        for (var point : this.points) {
            if (boundary.contains(point)) {
                found.add(point);
            }
        }

        if (this.isDivided) {
            this.northWest.query(boundary, found);
            this.northEast.query(boundary, found);
            this.southWest.query(boundary, found);
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
        this.northEast.parent = this;

        var nwBoundary = new RectBoundary(x - w / 2, y - h / 2, w / 2, h / 2);
        this.northWest = new QuadTree(nwBoundary, this.capacity);
        this.northWest.parent = this;

        var seBoundary = new RectBoundary(x + w / 2, y + h / 2, w / 2, h / 2);
        this.southEast = new QuadTree(seBoundary, this.capacity);
        this.southEast.parent = this;

        var swBoundary = new RectBoundary(x - w / 2, y + h / 2, w / 2, h / 2);
        this.southWest = new QuadTree(swBoundary, this.capacity);
        this.southWest = parent;
    }
}
