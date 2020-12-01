package task3_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;


class MyGregorianCalendarTest {
    @Test
    public void HowManyDaysTest(){
        var curDateCalendar = new MyGregorianCalendar("29/11/2020");

        var added = curDateCalendar.addUnits(1024, DateUnit.Day);

        var res = curDateCalendar.getDayOfTheWeek(added);
        var parsed = LocalDate.parse("2020-11-29");
        var expAdded = parsed.plusDays(1024);
        var expected = expAdded.getDayOfWeek();

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