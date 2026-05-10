package Modele;

/**
 * Angajat – extinde Persoana.
 *
 * Compozitie: Angajat "are" un CardAcces care nu poate exista independent.
 * CardAcces este creat odata cu Angajatul si distrus odata cu acesta.
 * Nu se ofera setter pentru CardAcces — nu poate fi inlocuit sau atribuit din exterior.
 */
public class Angajat extends Persoana {
    private double salariu;
    private String functie;

    // COMPOZITIE: CardAcces apartine exclusiv acestui Angajat.
    // Este creat in constructor si nu poate fi atribuit din exterior.
    private final CardAcces cardAcces;

    public Angajat(String nume, double salariu, String functie) {
        super(nume);
        this.salariu = salariu;
        this.functie = functie;
        // CardAcces este creat EXCLUSIV de Angajat — compozitie
        this.cardAcces = new CardAcces(nume.hashCode(), "CARD-" + nume.toUpperCase());
    }

    public double getSalariu() { return salariu; }
    public void setSalariu(double salariu) { this.salariu = salariu; }

    public String getFunctie() { return functie; }
    public void setFunctie(String functie) { this.functie = functie; }

    /**
     * Acces la CardAcces doar prin Angajat — nu exista independent.
     * Nu exista setter — CardAcces nu poate fi schimbat sau atribuit din exterior.
     */
    public CardAcces getCardAcces() { return cardAcces; }

    /**
     * Gestioneaza o rezervare — functionalitate specifica aplicatiei.
     */
    public void gestioneazaRezervare() {
        System.out.println("Angajatul " + nume + " gestioneaza o rezervare.");
    }

    /**
     * Demonstreaza distrugerea compozitiei: la afisarea angajatului,
     * CardAcces-ul este afisat ca parte integranta (nu independent).
     */
    @Override
    public void afisare() {
        System.out.println("Angajat: " + nume + " | Functie: " + functie + " | Salariu: " + salariu);
        System.out.print("  └─ ");
        cardAcces.afisare();
    }
}