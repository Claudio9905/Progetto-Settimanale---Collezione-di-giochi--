package claudiopostiglione.entities;


import claudiopostiglione.exceptions.ErrorIdNotFound;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GiochiDaTavolo extends CollezioniGiochi {

    //Attributi
    protected int numeroGiocatori;
    protected int durataPartita;

    //Costruttori
    public GiochiDaTavolo(Long idGioco, String titolo, LocalDate anno_pubblicazione, double prezzo, int numeroGiocatori, int durataPartita) {
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
        return "| - GiochiDaTavolo: " +
                " Titolo: " + titolo +
                ", ID: " + idGioco +
                ", Anno di pubblicazioene: " + anno_pubblicazione +
                ", Prezzo: € " + prezzo +
                ", Numero dei Giocatori = " + numeroGiocatori +
                ", Durata della partita=" + durataPartita +
                "min |";
    }

    public static boolean elementoAggiunto(List<GiochiDaTavolo> giochiDaTavoloList, GiochiDaTavolo nuovoGiocoDaTavolo) {
        giochiDaTavoloList.add(nuovoGiocoDaTavolo);
        return true;
    }

    public static void ricercaId(List<GiochiDaTavolo> giochiDaTavoloList, Long idGiocoInserito) throws ErrorIdNotFound {
        GiochiDaTavolo searchById = (GiochiDaTavolo) giochiDaTavoloList.stream().filter(giochiDaTavolo -> {
            if (giochiDaTavolo.getIdGioco().equals(idGiocoInserito)){
                return true;
            } else {
                throw new  ErrorIdNotFound("Errore, id non corrispondente");
            }
        });

        System.out.println("| - ID: " + idGiocoInserito + " / Gioco da tavolo: " + searchById);
    }

    public static void ricercaPerPrezzo(List<GiochiDaTavolo> giochiDaTavoloList, double prezzoInserito) {
        List<GiochiDaTavolo> searchByPrice = giochiDaTavoloList.stream().filter(giochiDaTavolo -> giochiDaTavolo.getPrezzo() < prezzoInserito).toList();
            System.out.println("| ---Lista dei giochi da tavolo con prezzo inferiore a quello inserito--- |");
        searchByPrice.forEach(giochiDaTavolo -> {
            System.out.println("| - " + giochiDaTavolo);
        });
    }

    public static void ricercaPerNumGiocatori(List<GiochiDaTavolo> giochiDaTavoloList) {
        Map<Integer, List<GiochiDaTavolo>> giocatoriPerGioco = giochiDaTavoloList.stream().collect(Collectors.groupingBy(GiochiDaTavolo::getNumeroGiocatori));
        giocatoriPerGioco.forEach((numero, giochiDaTavolo) -> System.out.println("| - Numero di giocatori: " + numero + " | Gioco da tavolo: " + giochiDaTavolo));
    }

    public static boolean rimozioneGioco(List<GiochiDaTavolo> giochiDaTavoloList, Long idGiocoInserito) {
        giochiDaTavoloList.removeIf(giochiDaTavolo -> giochiDaTavolo.getIdGioco().equals(idGiocoInserito));
        return true;
    }

    public static boolean updateGioco(List<GiochiDaTavolo> giochiDaTavoloList, Long idGiocoDaTavolo) {

        Scanner scanner = new Scanner(System.in);

        GiochiDaTavolo updateGiocoDaTavolo = (GiochiDaTavolo) giochiDaTavoloList.stream().filter(giochiDaTavolo -> giochiDaTavolo.getIdGioco().equals(idGiocoDaTavolo));
        System.out.println("| Cosa desideri modificare ( o premere 0 per uscire dalla modifica ):");
        System.out.println("| - Titolo (1)");
        System.out.println("| - ID (2)");
        System.out.println("| - Anno di pubblicazione (3)");
        System.out.println("| - Prezzo (4)");
        System.out.println("| - Numero di giocatori (5)");
        System.out.println("| - Durata della partita (6)");

        int scelta = Integer.parseInt(scanner.nextLine());

        switch (scelta) {

            case 0:
                break;
            case 1:
                System.out.println("| - Inserisci il nuovo titolo:");
                String titolo = scanner.nextLine();
                updateGiocoDaTavolo.setTitolo(titolo);
                break;
            case 2:
                System.out.println("| - Inserisci il nuovo ID:");
                Long idNuovo = Long.parseLong(scanner.nextLine());
                updateGiocoDaTavolo.setIdGioco(idNuovo);
                break;
            case 3:
                System.out.println("| - Modificare l'anno di publicazione:");
                int year = Integer.parseInt(scanner.nextLine());
                int month = Integer.parseInt(scanner.nextLine());
                int day = Integer.parseInt(scanner.nextLine());
                LocalDate annoNuovo = LocalDate.of(year, month, day);
                updateGiocoDaTavolo.setAnno_pubblicazione(annoNuovo);
                break;
            case 4:
                System.out.println("| - Inserire il nuovo prezzo:");
                double nuovoPrezzo = Double.parseDouble(scanner.nextLine());
                updateGiocoDaTavolo.setPrezzo(nuovoPrezzo);
                break;
            case 5:
                System.out.println("| - Inserire il nuovo numero di giocatori ( deve essere tra 2 e 10 ):");
                int nuoviGiocatori = Integer.parseInt(scanner.nextLine());
                updateGiocoDaTavolo.setNumeroGiocatori(nuoviGiocatori);
                break;
            case 6:
                System.out.println("| - Inserire la nuova durata della partita:");
                int nuovaDurata = Integer.parseInt(scanner.nextLine());
                updateGiocoDaTavolo.setDurataPartita(nuovaDurata);
                break;

            default:
                System.out.println("Errore, scelta inserita non valida");
        }
        return true;
    }

    public static void statisticheCollezioneGiochiDaTavolo(List<GiochiDaTavolo> giochiDaTavoloList) {
        int totaleGiochiDaTavolo = giochiDaTavoloList.size();
        OptionalDouble maxPrezzo = giochiDaTavoloList.stream().mapToDouble(GiochiDaTavolo::getPrezzo).max();
        OptionalDouble mediaPrezzo = giochiDaTavoloList.stream().mapToDouble(GiochiDaTavolo::getPrezzo).average();

        if (maxPrezzo.isPresent() && mediaPrezzo.isPresent()) {
            System.out.println("| Lista della collezione dei videogiochi | ------- | Totale dei videogiochi: " + totaleGiochiDaTavolo + " | Prezzo più alto: " + maxPrezzo + " | Media dei prezzi: " + mediaPrezzo);
            giochiDaTavoloList.forEach(System.out::println);
        } else {
            System.out.println("La lista dei giochi dei videogiochi è vuota");
        }
    }
}
