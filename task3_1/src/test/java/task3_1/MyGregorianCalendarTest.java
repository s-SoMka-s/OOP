package task3_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;


class MyGregorianCalendarTest {
    @Test
    public void HowManyDaysTest(){
        var curDateCalendar = new MyGregorianCalendar("29/11/2020");

        var res = curDateCalendar.getDayOfTheWeek(curDateCalendar.addUnits(1024, DateUnit.Day));
        var expected = LocalDate.parse("2020-11-28").plusDays(1024).getDayOfWeek();

        assertEquals(res.ordinal(), expected.ordinal());
    }

    @Test
    public void InWhatDayIWasBornTest(){
        var myCalendar = new MyGregorianCalendar();

        var res = myCalendar.getDayOfTheWeek(new MyDate(5, 2, 2002));
        var expected = Day.TUESDAY;

        assertEquals(res, expected);

    }
}