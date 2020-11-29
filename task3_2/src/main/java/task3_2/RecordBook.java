package task3_2;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class RecordBook {
    private String name;
    private String surname;
    private String patronymic;
    private int currSemester;
    private int currCourse;
    private ArrayList<Semester> semesters;

    /**
     * Конструктор класса RecordBook
     *
     * @param FIO - ФИО студента, разделенное пробелами, может быть без отчества
     */
    public RecordBook(String FIO) {
        if (FIO.isBlank()) {
            throw new IllegalArgumentException("Incorrect input");
        }

        var splited = FIO.trim().split(" ");

        this.currSemester = 1;
        this.currCourse = 1;
        this.name = splited[0];
        this.surname = splited[1];
        this.patronymic = splited.length == 3 ? splited[2] : null;
        this.addSemesters();
    }


    /**
     * Вернет средний балл по введенному предмету за всё время обучения.
     * Учитывает, что предмет может идти больше одного семестра
     *
     * @param subjectName - название предмета, по которому нужен средний балл
     * @return средний балл по введенному предмету за все время обучения
     */
    public Double getAverageMark(String subjectName) {
        var count = 0d;
        var res = 0d;
        for (var semester : this.semesters) {
            var avgSubjMark = semester.getAverageSubjectMark(subjectName);
            if (avgSubjMark != 0d) {
                count++;
                res += avgSubjMark;
            }
        }

        if (count == 0) {
            return 0d;
        }

        return res / count;
    }

    /**
     * Метод позволяет получить средний балл по конкретному предмету за конкретный семестр
     *
     * @param subjectName - название предмета
     * @param semesterId  - номер семестра. Начиная с 1
     * @return средний балл по введенному предмету за введенный семестр
     */
    public Double getAverageMark(String subjectName, int semesterId) {
        semesterId--;

        var semester = this.semesters.get(semesterId);
        if (semester == null) {
            throw new IllegalStateException("Such semester doesn't exist!");
        }

        var res = semester.getAverageSubjectMark(subjectName);

        return res;
    }


    /**
     * Вычисляет размер стипендии в текущем семестре на основе предыдущего
     *
     * @param semesterId - номер семестра с 1
     * @return величина стипендии в текущем семестре
     */
    public Scholarship calculateScholarship(int semesterId) {
        if (semesterId == 1) {
            return Scholarship.NOTHING;
        }

        semesterId--;
        var prvSemesterMarks = semesters.get(semesterId).getTotals().values();

        for (var mark : prvSemesterMarks) {
            if (mark >= 3.0 && mark < 4.0) {
                return Scholarship.LOW;
            }

            if (mark < 3.0) {
                return Scholarship.NOTHING;
            }

            if (mark > 4.0 && mark < 5.0) {
                return Scholarship.MIDDLE;
            }
        }

        return Scholarship.HIGH;
    }

    /**
     * Метод позволяет добавить оценок в семестр по конкретному предмету
     *
     * @param semesterId  - номер семестра с 1
     * @param subjectName - название предмета
     * @param marks       - хэш таблица с оценками <Название работы, Оценка>
     * @throws IllegalStateException - если такого семестра не существует
     */
    public void addMarks(int semesterId, String subjectName, HashMap<String, Double> marks) {
        semesterId--;

        var semester = this.semesters.get(semesterId);
        if (semester == null) {
            throw new IllegalStateException("Such semester doesn't exist!");
        }

        semester.addMarks(subjectName, marks);
    }

    /**
     * Метод позволяет получить Информацию о владельце зачетной книжки
     *
     * @return string - инфо о владельце книжки
     */
    public String getOwner() {
        var sb = new StringBuilder()
                .append("Surname: ").append(this.surname).append("\n")
                .append("Name: ").append(this.name).append("\n");

        if (!this.patronymic.isBlank()) {
            sb = sb.append("Patronymic: ").append(this.patronymic).append("\n");
        }

        sb = sb.append("Current course: ").append(this.currCourse).append("\n")
                .append("Current semester: ").append(this.currSemester).append("\n");

        return sb.toString();
    }

    /**
     * Метод позволяет добавить предмет в конкретный семестр
     * @param semesterId - номер семестра с 1
     * @param subject - объект класса Subject, не может быть null
     */
    public void addSubject(int semesterId, Subject subject){
        semesterId--;

        this.semesters.get(semesterId).addSubject(subject);
    }

    /**
     * Метод позволяет добавить предмет в конкретный семестр
     * @param semesterId - номер семестра
     * @param subjectName - название предмета
     * @param marks - оценки, может быть null
     */
    public void addSubject(int semesterId, String subjectName, @Nullable HashMap<String, Double> marks){
        semesterId--;

        this.semesters.get(semesterId).addSubject(subjectName, marks);
    }

    /**
     * Метод позволяет перевести пользователя на новый семестр
     * Проверяет, выставлены ли все итогывые за семестр
     */
    public void nextSemester() {
        if (this.currCourse == 4 && this.currSemester == 2) {
            throw new IllegalStateException("You in the last course in university!");
        }

        var semester = this.semesters.get((this.currCourse - 1) * 2 + this.currSemester - 1);
        var totalsEnough = !semester.getTotals().values().contains(0.0);
        if (!totalsEnough){
            throw new IllegalStateException("Not all final marks are recorded");
        }

        var curr = this.currSemester;
        if (curr == 2) {
            this.currCourse++;
            this.currSemester = 1;
            return;
        }

        this.currSemester++;
    }

    private void addSemesters() {
        this.semesters = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            var semester = new Semester(i + 1);
            this.semesters.add(semester);
        }
    }
}
