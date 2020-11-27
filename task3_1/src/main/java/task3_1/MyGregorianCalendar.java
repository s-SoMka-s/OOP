package task3_1;

public class MyGregorianCalendar {
    private MyDate currentDate;

    public MyGregorianCalendar(String dateString){
        if (dateString.isBlank()){
            throw new IllegalArgumentException("Wrong date format");
        }

        if (!this.tryParse(dateString)){
            throw new IllegalArgumentException("Wrong date format");
        }


    }

    private boolean tryParse(String dateString) {
        if (!dateString.matches("d{1,2}\\/d{1,2}\\/d{4}")){
            return false;
        }

        var splitted = dateString.split("\\/");
        if(splitted.length != 3){
            return false;
        }

        this.currentDate.day = Integer.parseInt(splitted[0]);
        this.currentDate.month = Integer.parseInt(splitted[1]);
        this.currentDate.year = Integer.parseInt(splitted[2]);

        return true;
    }

    /**
     * Adds number of date unit to current date
     * @param number - count of date units
     * @param unit - date unit
     * @return MyDate object - result date
     */
    public MyDate addUnits(int number, Units unit){
        MyDate result;
        switch (unit){
            case Day:
                result = this.addDays(number);
                break;
            case Week:
                result = this.addWeeks(number);
                break;
            case Month:
                result = this.addMonths(number);
                break;
            case Year:
                result = this.addYears(number);
                break;
            default:
                throw new IllegalArgumentException("Illegal date unit");
        }

        return result;
    }

    private MyDate addYears(int number) {
        return null;
    }

    private MyDate addWeeks(int number) {
        return null;
    }


    private MyDate addDays(int number){
        return null;
    }

    private MyDate addMonths(int number){
        return null;
    }



    enum Units {
        Day,
        Week,
        Month,
        Year
    }
}
