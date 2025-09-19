package claudiopostiglione.entities;



import java.time.LocalDate;
import java.util.*;


public class Videogiochi extends CollezioniGiochi {

    //Attributi
    protected String piattaforma;
    protected int durataGioco;
    protected GenereVideoGioco genere;

    //Costruttori
    public Videogiochi(Long idGioco, String titolo, LocalDate anno_pubblicazione, double prezzo, String piattaforma, int durataGioco, GenereVideoGioco genere) {
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
        return "| - Videogiochi: " +
                " Genere: " + genere +
                ", Piattaforma:  " + piattaforma +
                ", Durata del Gioco: " + durataGioco +
                " ore , Titolo: " + titolo +
                ", Anno di pubblicazione : " + anno_pubblicazione +
                ", Prezzo: € " + prezzo +
                ", ID: " + idGioco +
                " - |";
    }

    public static boolean elementoAggiunto(List<Videogiochi> videogiochiList, Videogiochi nuovoVideoGioco) {
        if(videogiochiList.stream().filter(videogiochi -> videogiochi.getIdGioco().equals(nuovoVideoGioco.getIdGioco())).isParallel()) {
            return false;
        } else {
            videogiochiList.add(nuovoVideoGioco);
            return true;
        }
    }

    public static void ricercaId(List<Videogiochi> videogiochiList, Long idGiocoInserito) {
        Videogiochi searchById = (Videogiochi) videogiochiList.stream().filter(videogiochi -> videogiochi.getIdGioco().equals(idGiocoInserito));
        System.out.println("| - ID: " + idGiocoInserito + " / Videogioco: " + searchById);
    }

    public static void ricercaPerPrezzo(List<Videogiochi> videogiochiList, double prezzoInserito) {

        List<Videogiochi> searchByPrice = videogiochiList.stream().filter(videogiochi -> videogiochi.getPrezzo() < prezzoInserito).toList();
            System.out.println("| ---Lista dei videogiochi con prezzo inferiore a quello inserito--- |");
        searchByPrice.forEach(videogiochi -> {
            System.out.println("| - " + videogiochi);
        });
    }

    public static boolean rimozioneGioco(List<Videogiochi> videogiochiList, Long idGiocoInserito) {

        if(videogiochiList.stream().filter(videogiochi -> videogiochi.getIdGioco().equals(idGiocoInserito)).isParallel()) {
            videogiochiList.removeIf(videogiochi -> videogiochi.getIdGioco().equals(idGiocoInserito));
            return true;
        } else {
            return false;
        }
    }

    public static boolean updateGioco(List<Videogiochi> videogiochiList, Long idGioco) {
        Scanner scanner = new Scanner(System.in);

        Videogiochi updateVideogioco = (Videogiochi) videogiochiList.stream().filter(videogiochi -> videogiochi.getIdGioco().equals(idGioco));
        System.out.println("| Cosa desideri modificare ( o premere 0 per uscire dalla modifica ):");
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

        return true;
    }

    public static void statisticheCollezioneVideogiochi(List<Videogiochi> videogiochiList) {
        int totaleVideogiochi = videogiochiList.size();
        OptionalDouble maxPrezzo = videogiochiList.stream().mapToDouble(Videogiochi::getPrezzo).max();
        OptionalDouble mediaPrezzo = videogiochiList.stream().mapToDouble(Videogiochi::getPrezzo).average();

        if (maxPrezzo.isPresent() && mediaPrezzo.isPresent()) {
            System.out.println("| Lista della collezione dei videogiochi | ------- | Totale dei videogiochi: " + totaleVideogiochi + " | Prezzo più alto: " + maxPrezzo + " | Media dei prezzi: " + mediaPrezzo);
            videogiochiList.forEach(System.out::println);
        } else {
            System.out.println("La lista dei giochi dei videogiochi è vuota");
        }
    }
}
