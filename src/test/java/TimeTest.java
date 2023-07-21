import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class TimeTest {
    Time time1;
    Time time2;

    @BeforeEach
    void setUp(){
        time1 = new Time(10, 0);
        time2 = new Time(2, 30);
    }

    @Test
    void timeSum() {
        Time actualTime = Time.timeSum(time1, time2);
        Assertions.assertEquals(new Time(12, 30), actualTime);
    }

    @Test
    void toInt() {
        int intTime = Time.toInt(time2);
        Assertions.assertEquals(150, intTime);
    }

    @Test
    void toTime() {
        Assertions.assertEquals(time1, Time.toTime(600));
    }
}