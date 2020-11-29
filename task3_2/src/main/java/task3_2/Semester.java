package task3_2;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Semester {
    private int Id;
    private ArrayList<Subject> subjects;

    /**
     * Конструктор класса Semester.
     *
     * @param id - номер семестра. Начинается с 1
     */
    public Semester(int id) {
        this.Id = id;
        this.subjects = new ArrayList<>();
    }

    /**
     * Метод позволяет добавить новый предмет в семестр
     *
     * @param name  - название предмета
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

    /**
     * Метод позволяет получить средний балл по введенному предмету за текущий семестр
     *
     * @param name
     * @return
     */
    public double getAverageSubjectMark(String name) {
        var exists = subjects.stream().filter(s -> s.getName().equals(name)).findFirst();
        if (exists.isEmpty()) {
            throw new IllegalStateException("Such subject doesn't exist!");
        }

        var res = exists.get().getAverage();

        return res;
    }

    /**
     * Метод позволяет получить все итоговые оценки по предметам за текущий семестр
     *
     * @return - хэш таблица <название предмета, оценка>
     */
    public HashMap<String, Double> getTotals() {
        var res = new HashMap<String, Double>();

        subjects.forEach(s -> res.put(s.getName(), s.getTotalMark()));

        return res;
    }

    /**
     * Метод позволяет выставить итоговую оценку за предмет в текущем семестре
     *
     * @param subjectName  - название предмета
     * @param newTotalMark - итогова оценка за предмет
     * @throws IllegalStateException - если такого предмета нет в текущем семестре
     */
    public void setTotalMark(String subjectName, double newTotalMark) {
        for (var subject : subjects) {
            if (subject.getName().equals(subjectName)) {
                subject.setTotalMark(newTotalMark);
                return;
            }
        }

        throw new IllegalStateException("Such subject doesn't exist!");
    }

    /**
     * Метод позволяет добавить оценки по текущему предмету
     * @param subjectName - название предмета
     * @param marks - хэш таблица с оценками, не может быть пустой
     * @throws IllegalStateException - если такого предмета нет
     * @throws IllegalArgumentException - если оценки пусты
     */
    public void addMarks(String subjectName, HashMap<String, Double> marks) {
        if (marks.isEmpty()){
            throw new IllegalArgumentException("Marks can't be empty!");
        }

        var subject = this.subjects.stream().filter(s -> s.getName().equals(subjectName)).findFirst().get();
        if (subject == null){
            throw new IllegalStateException("Such subject doesn't exists!");
        }

        for (var mark : marks.entrySet()) {
            subject.addMark(mark.getKey(), mark.getValue());
        }
    }
}
