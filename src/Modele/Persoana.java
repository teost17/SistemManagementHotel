package Modele;

public class Persoana {
    protected String nume;

    public Persoana(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void afisare() {
        System.out.println("Persoana: " + nume);
    }
}