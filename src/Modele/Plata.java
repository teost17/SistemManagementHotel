package Modele;

public class Plata {
    private int id;
    private double suma;
    private String metoda;

    public Plata(int id, double suma, String metoda) {
        this.id = id;
        this.suma = suma;
        this.metoda = metoda;
    }

    public void proceseazaPlata() {
        System.out.println("Plata de " + suma + " RON procesata prin " + metoda);
    }

    public double getSuma() { return suma; }
    public String getMetoda() { return metoda; }

    public void afisare() {
        System.out.println("Plata: " + suma + " RON, metoda: " + metoda);
    }
}