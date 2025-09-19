package claudiopostiglione.entities;

import claudiopostiglione.interfaces.RichiesteCollezioneVideoGames;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Videogiochi extends CollezioniGiochi implements RichiesteCollezioneVideoGames {

    //Attributi
    protected String piattaforma;
    protected int durataGioco;
    protected GenereVideoGioco genere;

    //Costruttori
    Videogiochi(Long idGioco, String titolo, LocalDate anno_pubblicazione, double prezzo, String piattaforma, int durataGioco, GenereVideoGioco genere) {
        super(idGioco, titolo, anno_pubblicazione, prezzo);
        this.piattaforma = piattaforma;
        this.durataGioco = durataGioco;
        this.genere = genere;
    }

    //Metodi
    public String getPiattaforma() {
        return piattaforma;
    }

    public void setPiattaforma(String piattaforma) {
        this.piattaforma = piattaforma;
    }

    public int getDurataGioco() {
        return durataGioco;
    }

    public void setDurataGioco(int durataGioco) {
        this.durataGioco = durataGioco;
    }

    public GenereVideoGioco getGenere() {
        return genere;
    }

    public void setGenere(GenereVideoGioco genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "| - Videogiochi :" +
                "Genere= " + genere +
                ", Piattaforma= " + piattaforma + '\'' +
                ", Durata del Gioco = " + durataGioco +
                ", Titolo = " + titolo + '\'' +
                ", Anno di pubblicazione = " + anno_pubblicazione +
                ", Prezzo=" + prezzo +
                " - |";
    }

    @Override
    public void elementoAggiunto(List<Videogiochi> videogiochiList, Videogiochi nuovoVideoGioco) {
        videogiochiList.add(nuovoVideoGioco);
    }

    @Override
    public void ricercaId(List<Videogiochi> videogiochiList, Long idGiocoInserito) {
        Videogiochi searchById = (Videogiochi) videogiochiList.stream().filter(videogiochi -> videogiochi.getIdGioco().equals(idGiocoInserito));
        System.out.println("| - ID: " + idGiocoInserito + " / Videogioco: " + searchById);
    }

    @Override
    public void ricercaPerPrezzo(List<Videogiochi> videogiochiList, double prezzoInserito) {

        List<Videogiochi> searchByPrice = videogiochiList.stream().filter(videogiochi -> videogiochi.getPrezzo() < prezzoInserito).toList();
        searchByPrice.forEach(videogiochi -> {
            System.out.println("| ---Lista dei videogiochi con prezzo inferiore a quello inserito--- |");
            System.out.println("| - " + videogiochi);
        });
    }

    @Override
    public boolean rimozioneGioco(List<Videogiochi> videogiochiList, Long idGiocoInserito) {
        videogiochiList.removeIf(videogiochi -> videogiochi.getIdGioco().equals(idGiocoInserito));
        return true;
    }

    @Override
    public void updateGioco(List<Videogiochi> videogiochiList, Long idGioco) {
        Scanner scanner = new Scanner(System.in);

        Videogiochi updateVideogioco = (Videogiochi) videogiochiList.stream().filter(videogiochi -> videogiochi.getIdGioco().equals(idGioco));
        System.out.println("Cosa desideri modificare ( o premere 0 per uscire dalla modifica ):");
        System.out.println("| - Titolo (1)");
        System.out.println("| - ID (2)");
        System.out.println("| - Anno di pubblicazione (3)");
        System.out.println("| - Prezzo (4)");
        System.out.println("| - Piattaforma (5)");
        System.out.println("| - Durata del gioco (6)");
        System.out.println("| - Genere (7)");

        int scelta = Integer.parseInt(scanner.nextLine());

        switch (scelta) {

            case 0:
                break;
            case 1:
                System.out.println("| - Inserisci il nuovo titolo:");
                String titolo = scanner.nextLine();
                updateVideogioco.setTitolo(titolo);
                break;
            case 2:
                System.out.println("| - Inserisci il nuovo ID:");
                Long idNuovo = Long.parseLong(scanner.nextLine());
                updateVideogioco.setIdGioco(idNuovo);
                break;
            case 3:
                System.out.println("| - Modificare l'anno di publicazione:");
                int year = Integer.parseInt(scanner.nextLine());
                int month = Integer.parseInt(scanner.nextLine());
                int day = Integer.parseInt(scanner.nextLine());
                LocalDate annoNuovo = LocalDate.of(year, month, day);
                updateVideogioco.setAnno_pubblicazione(annoNuovo);
                break;
            case 4:
                System.out.println("| - Inserire il nuovo prezzo:");
                double nuovoPrezzo = Double.parseDouble(scanner.nextLine());
                updateVideogioco.setPrezzo(nuovoPrezzo);
                break;
            case 5:
                System.out.println("| - Inserire la nuova piattaforma:");
                String nuovaPiattaforma = scanner.nextLine();
                updateVideogioco.setPiattaforma(nuovaPiattaforma);
                break;
            case 6:
                System.out.println("| - Inserire la nuova durata del gioco:");
                int nuovaDurata = Integer.parseInt(scanner.nextLine());
                updateVideogioco.setDurataGioco(nuovaDurata);
                break;
            case 7:
                System.out.println("| - Inserire il nuovo genere:");
                GenereVideoGioco nuovoGenere = GenereVideoGioco.valueOf(scanner.nextLine());
                updateVideogioco.setGenere(nuovoGenere);
                break;
            default:
                System.out.println("Errore, scelta inserita non valida");
        }

    }

    @Override
    public void statisticheCollezione(List<Videogiochi> videogiochiList) {

    }
}
