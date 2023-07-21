import java.util.*;

public class Cinema {
    String name;
    private final TreeMap<Days, Schedule> schedules = new TreeMap<>();
    private static List<Movie> moviesLibrary;
    private final Time openTime;
    private final Time closeTime;

    Cinema(String name, Time openTime, Time closeTime) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        moviesLibrary = new ArrayList<>();
        Days[] days = Days.values();
        for (Days d : days) {
            schedules.put(d, new Schedule());
        }
        System.out.println("Кінотеатр «"+ name +"» працює з " + openTime + " до " + closeTime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    void addMovie(Movie movie) {
        moviesLibrary.add(movie);
    }

    void removeMovie(Movie movie) {
        moviesLibrary.removeIf(m -> m.equals(movie));
        schedules.values()
                .forEach(schedule -> schedule.getSeances().removeIf(seance -> seance.getMovie().equals(movie)));
    }

    public List<Movie> getMoviesLibrary() {
        return moviesLibrary;
    }

    public TreeMap<Days, Schedule> getSchedules() {
        return schedules;
    }

    public void addSeance(Seance seance, String day) {
        try{
            if (Time.toInt(seance.getStartTime()) < Time.toInt(openTime) ||
                    Time.toInt(seance.getEndTime()) > Time.toInt(closeTime))
                throw new WrongTimeException("Час сеансу не співпадає з часом роботи кінотеатру!");
            Days d = Days.convertStringToDay(day);
            Schedule schedule = schedules.get(d);
            if (schedule.getSeances().isEmpty()) {
                schedule.addSeance(seance);
                System.out.println("Успішно!");
            } else {
                if (schedule.getSeances().stream().anyMatch(seance1 -> {
                    int fromTime = Time.toInt(seance1.getStartTime());
                    int toTime = Time.toInt(seance1.getEndTime());
                    return (fromTime <= Time.toInt(seance.getStartTime()) && toTime >= Time.toInt(seance.getStartTime()))
                            || (fromTime <= Time.toInt(seance.getEndTime()) && toTime >= Time.toInt(seance.getEndTime()));
                })) System.out.println("Час сеансу пересікається з уже існуючим, сеанс не буде додано!");
                else {
                    schedule.addSeance(seance);
                    System.out.println("Успішно!");
                }
            }
        }catch (WrongTimeException e){
            System.err.println(e.getMessage());
        }
    }

    void removeSeance(Seance seance, String day) {
        if (Days.isDayPresent(day)) {
            Days d = Days.convertStringToDay(day);
            Schedule schedule = schedules.get(d);
            schedule.removeSeance(seance);
        }
    }

    public void showSeancesFromDay(String day) {
        if (Days.isDayPresent(day)) {
            Days d = Days.convertStringToDay(day);
            Schedule schedule = schedules.get(d);
            Set<Seance> seances = schedule.getSeances();
            seances.forEach(System.out::println);
        }
    }

    public static boolean isMoviePresent(String title) {
        return moviesLibrary.stream().anyMatch(m -> m.getTitle().equalsIgnoreCase(title));
    }

    public static Movie stringToMovie(String title) {
        for (Movie m : moviesLibrary) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Кінотеатр :" + name;
    }
}
