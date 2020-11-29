package task3_2;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Semester {
    private int Id;
    private ArrayList<Subject> subjects;

    /**
     * Конструктор класса Semester.
     * @param id - номер семестра. Начинается с 1
     */
    public Semester(int id) {
        this.Id = id;
        this.subjects = new ArrayList<>();
    }

    /**
     * Метод позволяет добавить новый предмет в семестр
     * @param name - название предмета
     * @param marks - хэш таблица с оценками. Может быть null.
     * @throws IllegalStateException - если такой предмет уже существует.
     */
    public void addSubject(String name, @Nullable HashMap<String, Double> marks) {
        if (subjects.stream().filter(s -> s.getName().equals(name)).count() != 0) {
            throw new IllegalStateException("Such subject is already exists!");
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

    public double getAverageSubjectMark(String name) {
        var exists = subjects.stream().filter(s -> s.getName().equals(name)).findFirst();
        if (exists.isEmpty()) {
            throw new IllegalStateException("Such subject doesn't exist!");
        }

        var res = exists.get().getAverage();

        return res;
    }

    public HashMap<String, Double> getTotals(){
        var res = new HashMap<String, Double>();

        subjects.forEach(s -> res.put(s.getName(), s.getTotalMark()));

        return res;
    }

    public void setTotalMark(String subjectName, double newTotalMark){
        for (var subject : subjects){
            if (subject.getName().equals(subjectName)){
                subject.setTotalMark(newTotalMark);
                return;
            }
        }
    }
}
