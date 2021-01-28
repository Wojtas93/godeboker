package no.knowit.godeboker.bestilling;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Stats {
    private Map<Day, AtomicInteger> stats;
    private Day day;

    public Stats(Map<Day, AtomicInteger> stats, Day day) {
        this.stats = stats;
        this.day = day;
    }
}
