public class Libro extends ElementoCatalogo{
    private String autore;
    private String genere;

    public Libro (String isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore(){
        return autore;
    }

    @Override
    public String getDescrizione() {
        return "Libro: " + titolo + "di " + autore + " (" + annoPubblicazione + ")";
    }
}
