package task3_2;

import java.util.HashMap;

public class Subject {
    private String name;
    private double totalMark = 0;
    private HashMap<String, Double> marks;

    /**
     * Конструктор класса Subject
     * Создает предмет, не заполняя его оценками
     * @param name - название предмета
     */
    public Subject(String name) {
        this.name = name;
        this.marks = new HashMap<>();
    }

    /**
     * Конструктор класса Subject
     * Создает предмет, заполняет его подданными на ввод оценками
     * @param name - название предмета
     * @param marks - хэш таблица с оценками. Ключ - идентификатор работы, за которую выставлена оценка
     */
    public Subject(String name, HashMap<String, Double> marks) {
        this.name = name;
        this.marks = marks;
    }

    /**
     * @return название текущего предмета
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return хэш таблица оценок по текущему предмету
     */
    public HashMap<String, Double> getMarks() {
        return this.marks;
    }

    /**
     * @return итоговая оценка за текущий предмет
     */
    public double getTotalMark() {
        return this.totalMark;
    }

    /**
     * Позволяет выставить итоговую оценку за текущий предмет
     * @param totalMark - итоговая оценка, выставляемая пользователем
     */
    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

    /**
     * Метод позволяет добавить оценку в ведомость по текущему предмету
     * @param key - идентификатор работы, за которую выставляется оценка
     * @param mark - оценка, которую хотят поставить
     */
    public void addMark(String key, double mark) {
        if (!marks.containsKey(key)) {
            marks.put(key, mark);
        }

        marks.replace(key, mark);
    }

    /**
     * Метод позволяет удалить запись об оценке по текущему предмету
     * @param key - идентификатор работы, за которую хотят удалить оценку
     * @throws IllegalStateException - если работы с таким идентификатором нет
     */
    public void removeMark(String key) {
        if (!marks.containsKey(key)) {
            throw new IllegalStateException("This is no records about such work!");
        }

        marks.remove(key);
    }

    /**
     * Метод позволяет получить средний балл по текущему предмету
     * @return - средний балл
     *           0 - если оценок по предмету еще не было
     */
    public double getAverage() {
        var values = marks.values();
        if (values.isEmpty()) {
            return 0;
        }

        var res = values.stream().mapToDouble(Double::doubleValue).sum() / values.size();

        return res;
    }
}
