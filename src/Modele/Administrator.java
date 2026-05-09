package Modele;

public class Administrator extends Angajat {

    public Administrator(String nume, double salariu, String functie) {
        super(nume, salariu, functie);
    }

    public void adaugaCamera() {
        System.out.println("Administratorul " + nume + " a adaugat o camera.");
    }

    public void stergeCamera() {
        System.out.println("Administratorul " + nume + " a sters o camera.");
    }

    public void adaugaOferta() {
        System.out.println("Administratorul " + nume + " a adaugat o oferta.");
    }

    @Override

    public void afisare() {
        System.out.println("Administrator: " + nume);
    }
}