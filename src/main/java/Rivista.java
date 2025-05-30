public class Rivista extends ElementoCatalogo{
    public enum Periodicita {SETTIMANALE, MENSILE, SEMESTRALE}

    private Periodicita periodicita;

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    @Override
    public String getDescrizione() {
        return "Rivista: " + titolo + " (" + periodicita + ")";
    }
}