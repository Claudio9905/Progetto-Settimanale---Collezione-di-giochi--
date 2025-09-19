package claudiopostiglione.entities;

import claudiopostiglione.interfaces.RichiesteCollezioneGiochiTavolo;

import java.time.LocalDate;
import java.util.List;

public class GiochiDaTavolo extends CollezioniGiochi implements RichiesteCollezioneGiochiTavolo {

    //Attributi
    protected int numeroGiocatori;
    protected int durataPartita;

    //Costruttori
    GiochiDaTavolo(Long idGioco, String titolo, LocalDate anno_pubblicazione, double prezzo, int numeroGiocatori, int durataPartita) {
        super(idGioco, titolo, anno_pubblicazione, prezzo);
        this.numeroGiocatori = numeroGiocatori;
        this.durataPartita = durataPartita;
    }

    //Metodi
    public int getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public void setNumeroGiocatori(int numeroGiocatori) {
        this.numeroGiocatori = numeroGiocatori;
    }

    public int getDurataPartita() {
        return durataPartita;
    }

    public void setDurataPartita(int durataPartita) {
        this.durataPartita = durataPartita;
    }

    @Override
    public String toString() {
        return "| - GiochiDaTavolo :" +
                "numeroGiocatori=" + numeroGiocatori +
                ", durataPartita=" + durataPartita +
                " |";
    }

    @Override
    public void elementoAggiunto(List<GiochiDaTavolo> giochiDaTavoloList) {

    }

    @Override
    public void ricercaId(List<GiochiDaTavolo> giochiDaTavoloList, GiochiDaTavolo nuovoGiocoDaTavolo) {

    }

    @Override
    public void ricercaPerPrezzo(List<GiochiDaTavolo> giochiDaTavoloList, double prezzoInserito) {

    }

    @Override
    public void rimozioneGioco(List<GiochiDaTavolo> giochiDaTavoloList, Long idGioco) {

    }

    @Override
    public void updateGioco(List<GiochiDaTavolo> giochiDaTavoloList, Long idGioco) {

    }

    @Override
    public void statisticheCollezione(List<GiochiDaTavolo> giochiDaTavoloList) {

    }
}
