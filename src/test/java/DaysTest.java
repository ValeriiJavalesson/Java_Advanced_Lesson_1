import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DaysTest {

    @Test
    void convertStringToDay() {
        String string = "MONDAY";
        Days day = Days.convertStringToDay(string);
        Assertions.assertEquals(Days.MONDAY, day);
    }

    @Test
    void isDayPresent() {
        Assertions.assertTrue(Days.isDayPresent("monday"));
    }

    @Test
    void isDayNotPresent() {
        Assertions.assertFalse(Days.isDayPresent("day"));
    }
}