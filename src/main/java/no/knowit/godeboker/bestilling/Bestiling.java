package no.knowit.godeboker.bestilling;

import no.knowit.godeboker.bok.Bok;

import java.util.Map;

public class Bestiling {
   private Map<Bok, Integer> bestiling;

    public Map<Bok, Integer> getBestiling() {
        return bestiling;
    }

    public void setBestiling(Map<Bok, Integer> bestiling) {
        this.bestiling = bestiling;
    }
}
