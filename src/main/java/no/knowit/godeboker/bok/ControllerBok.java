package no.knowit.godeboker.bok;

import no.knowit.godeboker.bestilling.Bestiling;
import no.knowit.godeboker.bestilling.Day;
import no.knowit.godeboker.bestilling.ResponseBestiling;
import no.knowit.godeboker.bestilling.Stats;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

@RestController
public class ControllerBok {

    private final BokRepository bokRepository;

    public ControllerBok(BokRepository bokRepository) {
        this.bokRepository = bokRepository;
    }

    @GetMapping("/get")
    public List<Bok> get() {
        return bokRepository.findAll();
    }

    @GetMapping("/{id}")
    public Bok get(@PathVariable int id) {
        return bokRepository.findById(id);
    }

    @GetMapping("/statistic")
    public Stats getStats() {
        Map<Day, AtomicInteger> result = new TreeMap<>();
        AtomicInteger monday = new AtomicInteger();
        AtomicInteger tuesday = new AtomicInteger();
        AtomicInteger wednesday = new AtomicInteger();
        AtomicInteger thursday = new AtomicInteger();
        AtomicInteger friday = new AtomicInteger();
        AtomicInteger saturday = new AtomicInteger();
        AtomicInteger sunday = new AtomicInteger();
        bokRepository.findDates().forEach(localDate -> {
            switch (localDate.getDayOfWeek()) {
                case MONDAY:
                    monday.addAndGet(1);
                case TUESDAY:
                    tuesday.addAndGet(1);
                case WEDNESDAY:
                    wednesday.addAndGet(1);
                case THURSDAY:
                    thursday.addAndGet(1);
                case FRIDAY:
                    friday.addAndGet(1);
                case SATURDAY:
                    saturday.addAndGet(1);
                case SUNDAY:
                    sunday.addAndGet(1);
            }
        });
        result.put(Day.MONDAY, monday);
        result.put(Day.TUESDAY, tuesday);
        result.put(Day.WEDNESDAY, wednesday);
        result.put(Day.THURSDAY, thursday);
        result.put(Day.FRIDAY, friday);
        result.put(Day.SATURDAY, saturday);
        result.put(Day.SUNDAY, sunday);
        // TODO: 28.01.2021 filter dager og get med storste value
        return new Stats(result, Day.SATURDAY);
    }

    @PutMapping("/bestil")
    @ResponseBody
    public ResponseBestiling bestilling(@RequestBody Bestiling bestiling) {
        Map<Bok, Boolean> response = new TreeMap<>();
        BiConsumer<Bok, Integer> bestilling = bokRepository::bestilling;
        bestiling.getBestiling().forEach(bestilling);
        bestiling.getBestiling().forEach((bok, integer) -> {
            if (bokRepository.findByIsbn(bok.getIsbn()).getAntall() < integer) {
                response.put(bokRepository.findByIsbn(bok.getIsbn()), false);
            } else {
                response.put(bokRepository.findByIsbn(bok.getIsbn()), true);
            }
        });
        return new ResponseBestiling(response);
    }

    @PostMapping("/post")
    @ResponseBody
    public ResponseMessage nyBok(@RequestBody Bok bok) {
        bokRepository.save(bok);
        return new ResponseMessage("Bok added");
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseMessage update(@RequestBody Bok bokNew, @PathVariable int id) {
        bokRepository.update(id, bokNew);
        return new ResponseMessage("Bok updated");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseMessage delete(@PathVariable int id) {
        bokRepository.delete(id);
        return new ResponseMessage("bok deleted");

    }
}
