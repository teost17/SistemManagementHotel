package Modele;

import java.time.LocalDate;

/**
 * Rezervare – contine prin COMPOZITIE un obiect Plata.
 *
 * Compozitie: Rezervare "are" o Plata care nu poate exista independent.
 * Plata este creata exclusiv de Rezervare si nu poate fi instantiata din exterior
 * (constructorul Plata este package-private).
 */
public class Rezervare {
    private int id;
    private LocalDate dataStart;
    private LocalDate dataFinala;
    private String status;

    // COMPOZITIE: Plata este parte integranta din Rezervare.
    // Nu exista fara Rezervare si este creata/distrusa de aceasta.
    private Plata plata;

    public Rezervare(int id, LocalDate dataStart, LocalDate dataFinala) {
        this.id = id;
        this.dataStart = dataStart;
        this.dataFinala = dataFinala;
        this.status = "activa";
        this.plata = null; // Plata nu exista pana la efectuarea platii
    }

    /**
     * Calculeaza pretul total al rezervarii in functie de pretul pe noapte.
     */
    public double calculeazaPret(double pretPeNoapte) {
        long nopti = dataStart.until(dataFinala).getDays();
        return nopti * pretPeNoapte;
    }

    /**
     * Creeaza si ataseaza o Plata acestei rezervari (compozitie).
     * Plata nu poate exista in afara Rezervarii.
     */
    public void efectueazaPlata(double suma, String metoda) {
        // Plata este creata EXCLUSIV de Rezervare — compozitie
        this.plata = new Plata(this.id, suma, metoda);
        this.plata.proceseazaPlata();
        this.status = "platita";
    }

    /**
     * Anuleaza rezervarea si distruge plata asociata (daca exista).
     * Demonstreaza ca Plata nu supravietuieste Rezervarii.
     */
    public void anuleaza() {
        this.plata = null; // Plata este distrusa odata cu anularea Rezervarii
        this.status = "anulata";
        System.out.println("Rezervarea #" + id + " a fost anulata. Plata asociata a fost distrusa.");
    }

    public int getId() { return id; }
    public LocalDate getDataStart() { return dataStart; }
    public LocalDate getDataFinala() { return dataFinala; }
    public String getStatus() { return status; }

    /**
     * Returneaza plata doar prin Rezervare — nu exista acces direct independent.
     */
    public Plata getPlata() { return plata; }

    public void afisare() {
        System.out.println("Rezervare #" + id + " | " + dataStart + " -> " + dataFinala
                + " | Status: " + status);
        if (plata != null) {
            System.out.print("  └─ ");
            plata.afisare();
        }
    }
}