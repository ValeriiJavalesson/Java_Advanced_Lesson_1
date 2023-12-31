import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Schedule {
    private Set<Seance> seances = new TreeSet<>();

    public void addSeance(Seance seance) {
        seances.add(seance);
    }

    public void removeSeance(Seance seance) {
        for (Seance s : seances) {
            if (s.equals(seance)) seances.remove(s);
        }
    }
    public Set<Seance> getSeances() {
        return seances;
    }

    @Override
    public String toString() {
        return String.format("Кількість сеансів: %d", seances.size());
    }
}
