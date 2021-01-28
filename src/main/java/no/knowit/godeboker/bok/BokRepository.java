package no.knowit.godeboker.bok;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BokRepository {
    private List<Bok> repository = new ArrayList<>();

    private List<LocalDate> dates = new ArrayList<>();

    public List<Bok> findAll() {
        return repository;
    }

    public List<LocalDate> findDates() {
        return dates;
    }

    public Bok findById(int id) {
        return repository.get(id);
    }

    public Bok findByIsbn(String isnb) {
        return repository.stream().filter(bok -> bok.getIsbn().equals(isnb)).findFirst().orElse(null);
    }

    public void bestilling(Bok bok, int antall) {
        bok.setAntall(findByIsbn(bok.getIsbn()).getAntall() - antall);
        this.update(bok.getId(), bok);
        dates.add(LocalDate.now());
    }

    public void save(Bok bok) {
        bok.setId(generateNextId());
        repository.add(bok);
    }

    public void update(int id, Bok bok) {
        Bok bok1 = repository.get(id);
        bok1.setTittel(bok.getTittel());
        bok1.setIsbn(bok.getIsbn());
        bok1.setAntall(bok.getAntall());
        repository.add(bok1);
    }


    public void delete(int id) {
        repository.remove(id);
    }

    private int generateNextId() {
        return repository.stream()
                .mapToInt(Bok::getId)
                .max()
                .orElse(0) + 1;
    }
}
