package Modele;

/**
 * Plata – componenta in relatia de COMPOZITIE cu Rezervare.
 *
 * Constructorul este package-private (fara modificator de acces),
 * ceea ce inseamna ca Plata poate fi instantiata DOAR din acelasi pachet (Modele),
 * respectiv exclusiv de catre clasa Rezervare.
 * Aceasta impune regula de compozitie: Plata nu exista independent de Rezervare.
 */
public class Plata {
    private int id;
    private double suma;
    private String metoda;

    /**
     * Constructor package-private — poate fi apelat DOAR din Rezervare (acelasi pachet).
     * Nu poate fi instantiat direct din Main sau alte clase externe pachetului.
     */
    Plata(int id, double suma, String metoda) {
        this.id = id;
        this.suma = suma;
        this.metoda = metoda;
    }

    /**
     * Proceseaza plata si afiseaza detaliile.
     */
    public void proceseazaPlata() {
        System.out.println("Plata de " + suma + " RON procesata prin " + metoda);
    }

    public double getSuma() { return suma; }
    public String getMetoda() { return metoda; }

    public void afisare() {
        System.out.println("Plata: " + suma + " RON, metoda: " + metoda);
    }
}