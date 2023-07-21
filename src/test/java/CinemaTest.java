import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class CinemaTest {
    Cinema cinema;
    Movie movie;
    Seance seance;

    @BeforeEach
    void setUp() {
        cinema = new Cinema("Oasis", new Time(10, 0), new Time(22, 0));
        movie = new Movie("Good morning!", new Time(1, 36));
        seance = new Seance(movie, new Time(12,0));
    }

    @Test
    void addMovie() {
        cinema.addMovie(movie);
        List<Movie> moviesLibrary = cinema.getMoviesLibrary();
        Assertions.assertTrue(moviesLibrary.stream().anyMatch(movie1 -> movie1.equals(movie)));
    }

    @Test
    void removeMovie() {
        cinema.addMovie(movie);
        cinema.removeMovie(movie);
        List<Movie> moviesLibrary = cinema.getMoviesLibrary();
        Assertions.assertFalse(moviesLibrary.stream().anyMatch(movie1 -> movie1.equals(movie)));
    }

    @Test
    void addSeance() {
        cinema.addSeance(seance, "monday");
        List<Movie> moviesLibrary = cinema.getMoviesLibrary();
        TreeMap<Days, Schedule> schedules = cinema.getSchedules();
        Schedule schedule = schedules.get(Days.MONDAY);
        Assertions.assertTrue(schedule.getSeances().stream().anyMatch(seance1 -> seance1.equals(seance)));
    }

    @Test
    void removeSeance() {
        cinema.addSeance(seance, "monday");
        cinema.removeSeance(seance, "monday");
        List<Movie> moviesLibrary = cinema.getMoviesLibrary();
        TreeMap<Days, Schedule> schedules = cinema.getSchedules();
        Schedule schedule = schedules.get(Days.MONDAY);
        Assertions.assertFalse(schedule.getSeances().stream().anyMatch(seance1 -> seance1.equals(seance)));
    }

    @Test
    void isMoviePresent() {
        cinema.addMovie(movie);
        Assertions.assertTrue(cinema.getMoviesLibrary().stream().anyMatch(movie1 -> movie1.equals(movie)));
    }

    @Test
    void stringToMovie() {
        cinema.addMovie(movie);
        Movie actualMovie = Cinema.stringToMovie("Good morning!");
        Assertions.assertEquals(movie, actualMovie);
    }
}