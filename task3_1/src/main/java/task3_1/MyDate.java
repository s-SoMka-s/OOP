package task3_1;

public class MyDate {
    public int day;
    public int month;
    public int year;
    private int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


    /**
     * Конструктор класса MyDate
     * Сохраняет текущую дату как 1 января 1970 года
     */
    public MyDate(){
        this.day = 1;
        this.month = 1;
        this.year = 1970;
    }

    /**
     * Конструктор класса MyDate
     * @param day - день
     * @param month - номер месяця
     * @param year - год
     */
    public MyDate(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Конструктор класса MyDate.
     * Запомнит переданную дату
     * @param newDate - объект MyDate.
     */
    public MyDate(MyDate newDate){
        this.day = newDate.day;
        this.month = newDate.month;
        this.year = newDate.year;
    }

    /**
     * Вычисляет количество дней в месяце, учитавает високосность года
     * @param month - месяц
     * @param isYearLeap - високосный ли год
     * @return количество дней в месяце
     */
    protected int daysInMonth(Month month, boolean isYearLeap){
        var monthId = month.ordinal();
        var result = daysInMonth[monthId];

        if (isYearLeap && monthId == 1){
            result++;
        }

        return result;
    }

    /**
     * Добавляет сколько-то лет к текущей дате
     * @param date - к какой дате добавить
     * @param numberOfYears - сколько лет добавить
     * @return полученная дата
     */
    protected MyDate addYears(MyDate date, int numberOfYears) {
        return new MyDate(date.day, date.month, date.year + numberOfYears);
    }

    /**
     * Вычитает сколько-то лет из текущей даты
     * @param date - из какой даты вычесть
     * @param numberOfYears - сколько лет вычесть
     * @return полученная дата
     */
    protected MyDate subtractYears(MyDate date, int numberOfYears){
        return new MyDate(date.day, date.month, date.year - numberOfYears);
    }

    /**
     * Добавляет сколько-то месяцев к текущей дате
     * @param date - к какой дате добавить
     * @param numberOfMonth - сколько месяцев добавить
     * @return полученная дата
     */
    protected MyDate addMonths(MyDate date, int numberOfMonth){
        var month = date.month;
        var year = date.year;

        var yearsDiff = numberOfMonth / 12;
        numberOfMonth %= 12;

        month += numberOfMonth;
        year += yearsDiff;

        if (month > 12){
            month = month - 12;
            year++;
        }

        return new MyDate(date.day, month, year);
    }

    /**
     * Вычитает сколько-то месяцев из текущей даты
     * @param date - из какой даты вычесть
     * @param numberOfMonth - сколько месяцев вычесть
     * @return полученная дата
     */
    protected MyDate subtractMonths(MyDate date, int numberOfMonth){
        var month = date.month;
        var year = date.year;

        var yearsDiff = numberOfMonth / 12;
        numberOfMonth %= 12;

        month -= numberOfMonth;
        year -= yearsDiff;

        if (month < 1){
            month = month + 12;
            year--;
        }

        return new MyDate(date.day, month, year);
    }

    // TO-DO Implement
    protected MyDate addWeeks(MyDate date, int number) {
        return null;
    }

    // TO-DO Implement
    protected MyDate subtractWeeks(MyDate date, int number) {
        return null;
    }

    /**
     * Добавляет сколько-то дней к текущей дате
     * @param date - к какой дате прибавить
     * @param numberOfDays - сколько дней прибавить
     * @return полученная дата
     */
    protected MyDate addDays(MyDate date, int numberOfDays) {
        var resDay = date.day ;
        var monthId = date.month - 1;
        var year = date.year;
        resDay += numberOfDays;

        var daysInMonth = daysInMonth(Month.values()[monthId], isYearLeap(year));

        while (resDay > daysInMonth){
            resDay -= daysInMonth;
            monthId++;

            if (monthId > 11){
                monthId = 0;
                year++;
            }
            daysInMonth = daysInMonth(Month.values()[monthId], isYearLeap(year));
        }

        return new MyDate(resDay, monthId + 1, year);
    }

    /**
     * Вычитает сколько-то дней из текущей даты
     * @param date - из какой даты вычесть
     * @param numberOfDays - сколько дней вычесть
     * @return полученная дата
     */
    protected MyDate subtractDays(MyDate date, int numberOfDays){
        int resDay = date.day - numberOfDays;
        int monthId = date.month;
        int year = date.year;

        var daysInMonth = daysInMonth(Month.values()[monthId], isYearLeap(year));
        while(resDay < 1){
            resDay += daysInMonth;
            monthId--;

            if (monthId < 0){
                monthId = 12;
                year--;
            }

            daysInMonth = daysInMonth(Month.values()[monthId], isYearLeap(year));
        }

        return new MyDate(resDay, monthId, year);
    }

    /**
     * Проверяет, является ли год високосным
     * @param year - год
     * @return true - если год високосный,
     *         false - иначе
     */
    protected boolean isYearLeap(int year) {
        if ((year % 4 == 0) && (year % 100 != 0) || year % 400 == 0) {
            return true;
        }

        return false;
    }

    /**
     * Метод для форматированного вывода даты
     * @return дату в строковом представлении
     */
    @Override
    public String toString(){
        return String.format(this.day + "/" + this.month + "/" + this.year);
    }
}
