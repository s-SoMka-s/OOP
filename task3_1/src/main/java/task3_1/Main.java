package task3_1;

public class Main {
    public static void main(String[] args){
        MyGregorianCalendar c = new MyGregorianCalendar("22/11/2020");
        var res = c.addUnits(3, DateUnit.Year);

        System.out.println(c.isYearLeap(2021));
        System.out.println(c.getDayOfTheWeek(new MyDate(28, 11, 2020)));
        System.out.println(res);
    }
}
