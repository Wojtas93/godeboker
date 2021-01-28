package no.knowit.godeboker.bok;

import java.math.BigDecimal;

public class Bok {
    private int id;
    private String tittel;
    private String isbn;
    private BigDecimal pris;
    private int antall;

    public Bok() {
    }

    public Bok(String tittel, String isbn, BigDecimal pris) {
        this.tittel = tittel;
        this.isbn = isbn;
        this.pris = pris;
    }

    public Bok(String tittel, String isbn, BigDecimal pris, int antall) {
        this.tittel = tittel;
        this.isbn = isbn;
        this.pris = pris;
        this.antall = antall;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPris() {
        return pris;
    }

    public void setPris(BigDecimal pris) {
        this.pris = pris;
    }

    public int getAntall() {
        return antall;
    }

    public void setAntall(int antall) {
        this.antall = antall;
    }
}
