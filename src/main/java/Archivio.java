import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Archivio {
    private static final Logger logger = LoggerFactory.getLogger(Archivio.class);
    private Map<String, ElementoCatalogo> catalogo = new HashMap<>();

    public void aggiuntaElemento(ElementoCatalogo elemento){
        if (catalogo.containsKey(elemento.getIsbn())){
            logger.warn("Elemento con ISBN già presente: "+ elemento.getIsbn());
            System.out.println("Errore: ISBN già presente");
        } else {
            catalogo.put(elemento.getIsbn(), elemento);
            logger.info("Elemento aggiunto con ISBN: " + elemento.getIsbn());
        }
    }

    public void rimozioneElemento(String isbn) {
        if (catalogo.remove(isbn) != null) {
            logger.info("Elemento rimosso: " + isbn);
        } else {
            System.out.println("Elemento con ISBN non trovato");
        }
    }

// Ricerca tramite anno
    public List<ElementoCatalogo> ricercaPerAnno(int anno) {
        return catalogo.values().stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

// RIcerca tramite autore
    public List<Libro> ricercaPerAutore(String autore) {
        return catalogo.values().stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(i -> i.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    public void aggiornaElemento(String isbn, ElementoCatalogo nuovoElemento){
        if (!catalogo.containsKey(isbn)) {
            System.out.println("Impossibile aggiornare: ISBN non trovato");
            return;
        } else {
            catalogo.put(isbn, nuovoElemento);
            logger.info("Elemento aggiornato con ISBN: " + isbn);
        }
    }

// Statistiche
    public void mostraStats() {
        long numLibri = catalogo.values().stream()
                .filter(e -> e instanceof Libro)
                .count();

        long numRiviste = catalogo.values().stream()
                .filter(e -> e instanceof Rivista)
                .count();

        Optional<ElementoCatalogo> maxPage = catalogo.values().stream()
                .max(Comparator.comparing(ElementoCatalogo::getNumeroPagine));

        double mediaPagine = catalogo.values().stream()
                .mapToInt(ElementoCatalogo::getNumeroPagine)
                .average()
                .orElse(0.0);

        System.out.println("Statistiche del catologo: ");
        System.out.println("Totale libri " + numLibri);
        System.out.println("Totale riviste " + numRiviste);
        System.out.println("Elemento con più pagine: " + (maxPage.map(ElementoCatalogo::getDescrizione).orElse("Nessun elemento")));
        System.out.println("Media pagine: " + mediaPagine);
    }

}