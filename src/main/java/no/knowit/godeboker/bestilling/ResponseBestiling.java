package no.knowit.godeboker.bestilling;

import no.knowit.godeboker.bok.Bok;

import java.util.Map;

public class ResponseBestiling {

    private Map<Bok, Boolean> bestiling;

    public ResponseBestiling( Map<Bok, Boolean> bestiling) {
        this.bestiling = bestiling;
    }
}
