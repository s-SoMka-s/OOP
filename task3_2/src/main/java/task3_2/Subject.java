package task3_2;

import java.util.HashMap;

public class Subject {
    private String name;
    private double totalMark = 0;
    private HashMap<String, Double> marks;

    public Subject(String name) {
        this.name = name;
        this.marks = new HashMap<>();
    }

    public Subject(String name, HashMap<String, Double> marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return this.name;
    }

    public HashMap<String, Double> getMarks() {
        return this.marks;
    }

    public double getTotalMark() {
        return this.totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

    public void addMark(String key, double mark) {
        if (!marks.containsKey(key)) {
            marks.put(key, mark);
        }

        marks.replace(key, mark);
    }

    public void removeMark(String key) {
        if (!marks.containsKey(key)) {
            return;
        }

        marks.remove(key);
    }

    public double getAverage() {
        var values = marks.values();
        if (values.isEmpty()) {
            return 0;
        }

        var res = values.stream().mapToDouble(Double::doubleValue).sum() / values.size();

        return res;
    }
}
