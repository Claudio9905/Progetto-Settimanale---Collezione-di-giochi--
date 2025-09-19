package claudiopostiglione;

import claudiopostiglione.entities.GenereVideoGioco;
import claudiopostiglione.entities.GiochiDaTavolo;
import claudiopostiglione.entities.Videogiochi;
import claudiopostiglione.exceptions.ErrorIdNotFound;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

import static claudiopostiglione.entities.Videogiochi.*;
import static claudiopostiglione.entities.GiochiDaTavolo.*;

public class Application {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        Supplier<Videogiochi> supplierVideoGiochi = () -> {

            Faker faker = new Faker();
            Random rdnNumber = new Random();

            Long id = rdnNumber.nextLong(0, 1000);
            LocalDate anno = LocalDate.of(2025, 10, 10);
            double price = (double) Math.round(rdnNumber.nextDouble(0, 90) * 100) / 100;
            String[] listaPiattaforme = {"PC", "Xbox", "Playstation"};
            String piattaforma = listaPiattaforme[rdnNumber.nextInt(0, 2)];
            int durata = rdnNumber.nextInt(0, 30);
            String[] listaGeneri = {"AZIONE", "AVVENTURA", "HORROR", "STRATEGIA", "FANTASY"};
            GenereVideoGioco genere = GenereVideoGioco.valueOf(listaGeneri[rdnNumber.nextInt(0, 5)]);

            return new Videogiochi(id, faker.book().title(), anno, price, piattaforma, durata, genere);
            // uso il faker.book per fare prima ad dare i nomi ai vari giochi
        };

        List<Videogiochi> listaVideogiochi = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            listaVideogiochi.add(supplierVideoGiochi.get());
        }

        Supplier<GiochiDaTavolo> supplierGiochiTavolo = () -> {

            Faker faker = new Faker();
            Random rdnNumber = new Random();

            Long id = rdnNumber.nextLong(1000);
            LocalDate anno = LocalDate.of(2025, 10, 10);
            double price = (double) Math.round(rdnNumber.nextDouble(90) * 100) / 100;
            int durata = rdnNumber.nextInt(30);
            int numGiocatori = rdnNumber.nextInt(2, 10 + 1);

            return new GiochiDaTavolo(id, faker.book().title(), anno, price, numGiocatori, durata);
        };

        List<GiochiDaTavolo> listaGiochiTavolo = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            listaGiochiTavolo.add(supplierGiochiTavolo.get());
        }

        System.out.println("|------------------------|");
        System.out.println("| BENVENUTO AD EPI-GAMES |");
        System.out.println("|------------------------|");
        System.out.println("\n");

        int scelta;

        do {
            System.out.println("| Cosa vuoi vedere?:");
            System.out.println("| - Collezione di videogiochi (1)");
            System.out.println("| - Collezione di giochi da tavolo (2)");
            System.out.println("| Se si vuole uscire, premere 0");
            scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 0:
                    break;
                case 1:
                    collezioneDiVideogiochi(listaVideogiochi);
                    break;
                case 2:
                    collezioneDiGiochiDaTavolo(listaGiochiTavolo);
                    break;
                default:
                    System.out.println("Attenzione, scelta errata, prego, riprovare");
            }


        } while (scelta != 0);


    }

    public static void collezioneDiVideogiochi(List<Videogiochi> listaVideogiochi) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("| ------ Lista dei Videogiochi ------ |");
        listaVideogiochi.forEach(System.out::println);
        System.out.println("\n");
        int scelta;

        do {
            System.out.println("\n");
            System.out.println("| Hai delle richieste ?");
            System.out.println("| - Exit (0)");
            System.out.println("| - Aggiungi un nuovo gioco (1)");
            System.out.println("| - Cerca il gioco in base all'id (2)");
            System.out.println("| - Cerca il gioco in base al prezzo che sia maggiore del prezzo inserito (3)");
            System.out.println("| - Rimuovere il gioco (4)");
            System.out.println("| - Modificare il gioco (5)");
            System.out.println("| - Statistiche del gioco con il suo catalogo (6)");
            scelta = Integer.parseInt(scanner.nextLine());
            switch (scelta) {
                case 0:
                    break;
                case 1:
                    Supplier<Videogiochi> supplierVideoGiochi = () -> {

                        System.out.println("| Inserisci il gioco:");
                        String titoloNuovo = scanner.nextLine();
                        Random rdnNumber = new Random();

                        Long id = rdnNumber.nextLong(0, 1000);
                        LocalDate anno = LocalDate.of(2025, 10, 10);
                        double price = (double) Math.round(rdnNumber.nextDouble(0, 90) * 100) / 100;
                        String[] listaPiattaforme = {"PC", "Xbox", "Playstation"};
                        String piattaforma = listaPiattaforme[rdnNumber.nextInt(0, 2)];
                        int durata = rdnNumber.nextInt(0, 30);
                        String[] listaGeneri = {"AZIONE", "AVVENTURA", "HORROR", "STRATEGIA", "FANTASY"};
                        GenereVideoGioco genere = GenereVideoGioco.valueOf(listaGeneri[rdnNumber.nextInt(0, 5)]);

                        return new Videogiochi(id, titoloNuovo, anno, price, piattaforma, durata, genere);
                    }; // ho creato un supplier che mi crea un oggetto in modo da essere più veloce nel eseguire la richiesta
                    boolean giocoAggiunto = elementoAggiunto(listaVideogiochi, supplierVideoGiochi.get());
                    if (giocoAggiunto) {
                        System.out.println("Videogioco aggiunto");
                    } else {
                        System.out.println("Attenzione, il gioco è già presente del catalogo");
                    }
                    break;
                case 2:
                    System.out.println("| - Inserire l'id da cercare:");
                    Long idInserito = Long.parseLong(scanner.nextLine());
                    ricercaId(listaVideogiochi, idInserito);
                    break;
                case 3:
                    System.out.println("| - Inserire il prezzo:");
                    double prezzoInserito = Double.parseDouble(scanner.nextLine());
                    ricercaPerPrezzo(listaVideogiochi, prezzoInserito);
                    break;
                case 4:
                    System.out.println("| - Inserire l'id del gioco:");
                    Long idGiocoInserito = Long.parseLong(scanner.nextLine());
                    boolean giocoRimosso = rimozioneGioco(listaVideogiochi, idGiocoInserito);
                    if (giocoRimosso) {
                        System.out.println("Videogioco rimosso");
                    } else {
                        System.out.println("Attenzione, gioco già assente nel catalogo");
                    }
                    break;
                case 5:
                    System.out.println("| - Inserire l'id del gioco da modificare:");
                    Long idUpdate = Long.parseLong(scanner.nextLine());
                    boolean updateGioco = updateGioco(listaVideogiochi, idUpdate);
                    if (updateGioco) {
                        System.out.println("Il gioco è stato modificato");
                    } else {
                        System.out.println("Attenzione, impossibile modificare il gioco");
                    }
                    break;
                case 6:
                    statisticheCollezioneVideogiochi(listaVideogiochi);
                    break;
                default:
                    System.out.println("Attenzione, scelta non valida");
            }
        } while (scelta != 0);
    }

    public static void collezioneDiGiochiDaTavolo(List<GiochiDaTavolo> listaGiochiDaTavolo) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("| ------ Lista dei giochi da tavolo ------ |");
        listaGiochiDaTavolo.forEach(System.out::println);
        System.out.println("\n");
        int scelta;

        do {
            System.out.println("\n");
            System.out.println("| Hai delle richieste ?");
            System.out.println("| - Exit (0)");
            System.out.println("| - Aggiungi un nuovo gioco (1)");
            System.out.println("| - Cerca il gioco in base all'id (2)");
            System.out.println("| - Cerca il gioco in base al prezzo che sia maggiore del prezzo inserito (3)");
            System.out.println("| - Rimuovere il gioco (4)");
            System.out.println("| - Modificare il gioco (5)");
            System.out.println("| - Lista dei giochi in base al numero di giocatori (6)");
            System.out.println("| - Statistiche del gioco con il suo catalogo (7)");
            scelta = Integer.parseInt(scanner.nextLine());
            switch (scelta) {
                case 0:
                    break;
                case 1:
                    Supplier<GiochiDaTavolo> supplierGiochiTavolo = () -> {

                        System.out.println("| Inserisci il gioco:");
                        String titoloNuovo = scanner.nextLine();
                        Random rdnNumber = new Random();

                        Long id = rdnNumber.nextLong(1000);
                        LocalDate anno = LocalDate.of(2025, 10, 10);
                        double price = (double) Math.round(rdnNumber.nextDouble(90) * 100) / 100;
                        int durata = rdnNumber.nextInt(30);
                        int numGiocatori = rdnNumber.nextInt(2, 10 + 1);

                        return new GiochiDaTavolo(id, titoloNuovo, anno, price, numGiocatori, durata);
                    }; // ho creato un supplier che mi crea un oggetto in modo da essere più veloce nel eseguire la richiesta
                    boolean giocoAggiunto = elementoAggiunto(listaGiochiDaTavolo, supplierGiochiTavolo.get());
                    if (giocoAggiunto) {
                        System.out.println("Videogioco aggiunto");
                    } else {
                        System.out.println("Attenzione, il gioco è già presente del catalogo");
                    }
                    break;
                case 2:
                    try {
                        System.out.println("| - Inserire l'id da cercare:");
                        Long idInserito = Long.parseLong(scanner.nextLine());
                        ricercaId(listaGiochiDaTavolo, idInserito);
                    } catch (ErrorIdNotFound er){
                        System.out.println("Id del gioco non trovato");
                    }
                    break;
                case 3:
                    System.out.println("| - Inserire il prezzo:");
                    double prezzoInserito = Double.parseDouble(scanner.nextLine());
                    ricercaPerPrezzo(listaGiochiDaTavolo, prezzoInserito);
                    break;
                case 4:
                    System.out.println("| - Inserire l'id del gioco:");
                    Long idGiocoInserito = Long.parseLong(scanner.nextLine());
                    boolean giocoRimosso = rimozioneGioco(listaGiochiDaTavolo, idGiocoInserito);
                    if (giocoRimosso) {
                        System.out.println("Videogioco rimosso");
                    } else {
                        System.out.println("Attenzione, gioco già assente nel catalogo");
                    }
                    break;
                case 5:
                    System.out.println("| - Inserire l'id del gioco da modificare:");
                    Long idUpdate = Long.parseLong(scanner.nextLine());
                    boolean updateGioco = updateGioco(listaGiochiDaTavolo, idUpdate);
                    if (updateGioco) {
                        System.out.println("Il gioco è stato modificato");
                    } else {
                        System.out.println("Attenzione, impossibile modificare il gioco");
                    }
                    break;
                case 6:
                    ricercaPerNumGiocatori(listaGiochiDaTavolo);
                    break;
                case 7:
                    statisticheCollezioneGiochiDaTavolo(listaGiochiDaTavolo);
                    break;
                default:
                    System.out.println("Attenzione, scelta non valida");
            }
        } while (scelta != 0);
    }
}
