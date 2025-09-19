package claudiopostiglione.entities;

import java.time.LocalDate;

public class CollezioniGiochi {

    //Attributi
    protected Long idGioco;
    protected String titolo;
    protected LocalDate anno_pubblicazione;
    protected double prezzo;
    
    //Costruttori
    public CollezioniGiochi(Long idGioco, String titolo, LocalDate anno_pubblicazione, double prezzo) {
        this.idGioco = idGioco;
        this.titolo = titolo;
        this.anno_pubblicazione = anno_pubblicazione;
        this.prezzo = prezzo;
    }

    //Metodi
    public Long getIdGioco() {
        return idGioco;
    }

    public void setIdGioco(Long idGioco) {
        this.idGioco = idGioco;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getAnno_pubblicazione() {
        return anno_pubblicazione;
    }

    public void setAnno_pubblicazione(LocalDate anno_pubblicazione) {
        this.anno_pubblicazione = anno_pubblicazione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "|CollezioniGiochi| " +
                "idGioco=" + idGioco +
                ", titolo='" + titolo + '\'' +
                ", anno_pubblicazione=" + anno_pubblicazione +
                ", prezzo=" + prezzo;

    }
}
