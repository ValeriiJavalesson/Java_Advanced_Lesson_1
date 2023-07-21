import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {
    Schedule schedule = new Schedule();
    Seance seance;

    @BeforeEach
    void setUp() {
        seance = new Seance(new Movie("Good morning!", new Time(1, 36)), new Time(10, 0));
    }

    @Test
    void addSeance() {
        schedule.addSeance(seance);
        Set<Seance> seances = schedule.getSeances();
        Assertions.assertTrue(seances.stream().anyMatch(seance1 -> seance1.equals(seance)));
    }

    @Test
    void removeSeance() {
        schedule.addSeance(seance);
        schedule.removeSeance(seance);
        Set<Seance> seances = schedule.getSeances();
        Assertions.assertFalse(seances.stream().anyMatch(seance1 -> seance1.equals(seance)));
    }
}