package task3_1;

public class MyGregorianCalendar extends MyDate {
    private MyDate currentDate;

    /**
     * Конструктор класса,
     * выставляет текущей датой 1 января 1970 года
     */
    public MyGregorianCalendar(){
        this.currentDate = new MyDate();
    }

    /**
     * Конструктор класса
     * Пытается распарсить строку представляющую дату
     * @param dateString - дата в строковом представлении в формате ddMMyyyy
     */
    public MyGregorianCalendar(String dateString) {
        if (dateString.isBlank()) {
            throw new IllegalArgumentException("Wrong date format");
        }

        var parsed = this.tryParse(dateString);
        if (parsed == null) {
            throw new IllegalArgumentException("Wrong date format");
        }

        this.currentDate = parsed;
    }

    private MyDate tryParse(String dateString) {
        var splitted = dateString.split("\\/");
        if (splitted.length != 3) {
            return null;
        }

        var day = Integer.parseInt(splitted[0]);
        var month = Integer.parseInt(splitted[1]);
        var year = Integer.parseInt(splitted[2]);

        return new MyDate(day,month, year);
    }

    /**
     * Adds number of date unit to current date
     *
     * @param number - count of date units
     * @param unit   - date unit
     * @return MyDate object - result date
     */
    public MyDate addUnits(int number, DateUnit unit) {
        MyDate result;
        switch (unit) {
            case Day:
                result = addDays(this.currentDate, number);
                break;
            case Week:
                result = addWeeks(this.currentDate, number);
                break;
            case Month:
                result = addMonths(this.currentDate, number);
                break;
            case Year:
                result = addYears(this.currentDate, number);
                break;
            default:
                throw new IllegalArgumentException("Illegal date unit");
        }

        return result;
    }

    /**
     * Subtract number of date unit to current date
     *
     * @param number - count of date units
     * @param unit   - date unit
     * @return MyDate object - result date
     */
    public MyDate subtractUnits(int number, DateUnit unit) {
        MyDate result;
        switch (unit) {
            case Day:
                result = this.subtractDays(this.currentDate, number);
                break;
            case Week:
                result = this.subtractWeeks(this.currentDate, number);
                break;
            case Month:
                result = this.subtractMonths(this.currentDate, number);
                break;
            case Year:
                result = this.subtractYears(this.currentDate, number);
                break;
            default:
                throw new IllegalArgumentException("Illegal date unit");
        }

        return result;
    }

    /**
     * Вычисляет, какой день недели призодится на конкретную дату
     * С помощью алгоритма Tomohiko Sakamoto
     * @param date
     * @return день недели
     */
    public Day getDayOfTheWeek(MyDate date){
        int day = date.day;
        int month = date.month;
        int year = date.year;

        int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };

        year -= (month < 3) ? 1 : 0;
        var dayId = (year + year/4 - year/100 + year/400 + t[month-1] + day) % 7 - 1;

        return Day.values()[dayId];
    }
}
