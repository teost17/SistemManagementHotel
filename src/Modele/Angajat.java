package Modele;

public class Angajat extends Persoana {
    private double salariu;
    private String functie;

    public Angajat(String nume, double salariu, String functie) {
        super(nume);
        this.salariu = salariu;
        this.functie = functie;
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public void gestioneazaRezervare() {
        System.out.println("Angajatul " + nume + " gestioneaza o rezervare.");
    }

    @Override
    public void afisare() {
        System.out.println("Angajat: " + nume + ", Functie: " + functie + ", Salariu: " + salariu);
    }
}