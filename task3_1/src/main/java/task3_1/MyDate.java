package task3_1;

public class MyDate {
    public int day;
    public int month;
    public int year;
    private int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


    public MyDate(){
        this.day = 1;
        this.month = 1;
        this.year = 1970;
    }

    public MyDate(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public MyDate(MyDate newDate){
        this.day = newDate.day;
        this.month = newDate.month;
        this.year = newDate.year;
    }

    protected int daysInMonth(Month month, boolean isYearLeap){
        var monthId = month.ordinal();
        var result = daysInMonth[monthId];

        if (isYearLeap && monthId == 1){
            result++;
        }

        return result;
    }

    protected MyDate addYears(MyDate date, int numberOfYears) {
        return new MyDate(date.day, date.month, date.year + numberOfYears);
    }

    protected MyDate subtractYears(MyDate date, int numberOfYears){
        return new MyDate(date.day, date.month, date.year - numberOfYears);
    }

    // TO-DO Implement
    protected MyDate addMonths(MyDate date, int numberOfMonth){
        return null;
    }

    // TO-DO Implement
    protected MyDate subtractMonths(MyDate date, int numberOfMonth){
        return null;
    }

    // TO-DO Implement
    protected MyDate addWeeks(MyDate date, int number) {
        return null;
    }

    // TO-DO Implement
    protected MyDate subtractWeeks(MyDate date, int number) {
        return null;
    }

    protected MyDate addDays(MyDate date, int numberOfDays) {
        var resDay = date.day ;
        var monthId = date.month;
        var year = date.year;
        resDay += numberOfDays;

        var daysInMonth = daysInMonth(Month.values()[monthId], isYearLeap(year));

        while (resDay > daysInMonth){
            resDay -= daysInMonth;
            monthId++;

            if (monthId > 12){
                monthId = 1;
                year++;
            }
            daysInMonth = daysInMonth(Month.values()[monthId-1], isYearLeap(year));
        }

        return new MyDate(resDay, monthId, year);
    }

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

    protected boolean isYearLeap(int year) {
        if ((year % 4 == 0) && (year % 100 != 0) || year % 400 == 0) {
            return true;
        }

        return false;
    }

    @Override
    public String toString(){
        return String.format(this.day + "/" + this.month + "/" + this.year);
    }
}
