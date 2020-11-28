package task3_1;

public class MyGregorianCalendar extends MyDate {
    private MyDate currentDate;

    public MyGregorianCalendar(){
        this.currentDate = new MyDate();
    }

    public MyGregorianCalendar(String dateString) {
        if (dateString.isBlank()) {
            throw new IllegalArgumentException("Wrong date format");
        }

        this.currentDate = new MyDate();

        if (!this.tryParse(dateString)) {
            throw new IllegalArgumentException("Wrong date format");
        }


    }

    private boolean tryParse(String dateString) {
        var splitted = dateString.split("\\/");
        if (splitted.length != 3) {
            return false;
        }

        this.currentDate.day = Integer.parseInt(splitted[0]);
        this.currentDate.month = Integer.parseInt(splitted[1]);
        this.currentDate.year = Integer.parseInt(splitted[2]);

        return true;
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
