package task3_2;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Semester {
    private int Id;
    private ArrayList<Subject> subjects;

    public Semester(int id) {
        this.Id = id;
        this.subjects = new ArrayList<>();
    }

    public void addSubject(String name, @Nullable HashMap<String, Double> marks) {
        if (subjects.stream().filter(s -> s.getName().equals(name)).count() != 0) {
            return;
        }

        if (marks == null) {
            subjects.add(new Subject(name));
            return;
        }

        subjects.add(new Subject(name, marks));
    }

    public double getAverageMark() {
        if (subjects.isEmpty()) {
            return 0;
        }

        var avgMarksSum = subjects.stream().mapToDouble(s -> s.getAverage()).sum();
        var subjCount = subjects.size();

        return avgMarksSum / subjCount;
    }
}
