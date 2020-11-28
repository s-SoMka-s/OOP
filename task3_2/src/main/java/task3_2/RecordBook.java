package task3_2;

import java.util.ArrayList;

public class RecordBook {
    private String name;
    private String surname;
    private String patronymic;
    private ArrayList<Semester> semesters;

    public RecordBook(String FIO) {
        if (FIO.isBlank()) {
            throw new IllegalArgumentException("Incorrect input");
        }

        var splited = FIO.trim().split(" ");
        this.name = splited[0];
        this.surname = splited[1];
        this.patronymic = splited.length == 3 ? splited[2] : null;
    }

    /**
     * Вернет средний балл по введенному предмету,
     * учитывает, что предмет может идти больше одного семестра
     *
     * @param subjectName - название предмета, по которому нужен средний балл
     * @return средний балл по введенному предмету за все время обучения
     */
    public Double getAvgSubjectMark(String subjectName) {
        var count = 0d;
        var res = 0d;
        for (var semester : semesters) {
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
     * Вычисляет размер стипендии в текущем семестре на основе предыдущего
     *
     * @param semesterId - номер семестра с 1
     * @return величина стипендии в текущем семестре
     */
    public Scholarship calculateScholarship(int semesterId) {
        if (semesterId == 1) {
            return Scholarship.NOTHING;
        }

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
}
