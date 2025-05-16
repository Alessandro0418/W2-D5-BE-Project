import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("""
                    \n📚 GESTIONE CATALOGO BIBLIOTECARIO
                    1. Aggiungi Libro
                    2. Aggiungi Rivista
                    3. Ricerca per ISBN
                    4. Rimuovi elemento
                    5. Ricerca per anno
                    6. Ricerca per autore
                    7. Aggiorna elemento
                    8. Statistiche catalogo
                    9. Esci
                    Scelta: """);

            int scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 1 -> aggiungiLibro(scanner, archivio);
                case 2 -> aggiungiRivista(scanner, archivio);
                case 4 -> rimuoviElemento(scanner, archivio);
                case 5 -> ricercaAnno(scanner, archivio);
                case 6 -> ricercaAutore(scanner, archivio);
                case 7 -> aggiornaElemento(scanner, archivio);
                case 8 -> archivio.mostraStats();
                case 9 -> {
                    System.out.println("Quit!");
                    running = false;
                }
                default -> System.out.println("Scelta non valida.");
            }
        }
        scanner.close();
    }

    private static void aggiungiLibro(Scanner scanner, Archivio archivio) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();

        System.out.print("Anno pubblicazione: ");
        int anno = Integer.parseInt(scanner.nextLine());

        System.out.print("Numero pagine: ");
        int pagine = Integer.parseInt(scanner.nextLine());

        System.out.print("Autore: ");
        String autore = scanner.nextLine();

        System.out.print("Genere: ");
        String genere = scanner.nextLine();

        Libro libro = new Libro(isbn, titolo, anno, pagine, autore, genere);
        archivio.aggiuntaElemento(libro);
    }

    private static void aggiungiRivista(Scanner scanner, Archivio archivio) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();

        System.out.print("Anno pubblicazione: ");
        int anno = Integer.parseInt(scanner.nextLine());

        System.out.print("Numero pagine: ");
        int pagine = Integer.parseInt(scanner.nextLine());

        System.out.print("Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
        String periodicita = scanner.nextLine().toUpperCase();

        Rivista rivista = new Rivista(isbn, titolo, anno, pagine, Rivista.Periodicita.valueOf(periodicita));
        archivio.aggiuntaElemento(rivista);
    }


    private static void rimuoviElemento(Scanner scanner, Archivio archivio) {
        System.out.print("Inserisci ISBN da rimuovere: ");
        String isbn = scanner.nextLine();
        archivio.rimozioneElemento(isbn);
    }

    private static void ricercaAnno(Scanner scanner, Archivio archivio) {
        System.out.print("Anno da cercare: ");
        int anno = Integer.parseInt(scanner.nextLine());

        archivio.ricercaPerAnno(anno).forEach(e -> System.out.println(e.getDescrizione()));
    }

    private static void ricercaAutore(Scanner scanner, Archivio archivio) {
        System.out.print("Autore da cercare: ");
        String autore = scanner.nextLine();

        archivio.ricercaPerAutore(autore).forEach(e -> System.out.println(e.getDescrizione()));
    }

    private static void aggiornaElemento(Scanner scanner, Archivio archivio) {
        System.out.print("ISBN da aggiornare: ");
        String isbn = scanner.nextLine();

        System.out.println("Che tipo di elemento è (libro/rivista)? ");
        String tipo = scanner.nextLine().toLowerCase();

        if (tipo.equals("libro")) {
            aggiungiLibro(scanner, archivio); // riusa inserimento
        } else if (tipo.equals("rivista")) {
            aggiungiRivista(scanner, archivio);
        } else {
            System.out.println("Tipo non valido.");
        }
    }
}