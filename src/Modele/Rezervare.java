package Modele;

import java.time.LocalDate;

public class Rezervare {
    private int id;
    private LocalDate dataStart;
    private LocalDate dataFinala;
    private String status;
    private Plata plata;

    public Rezervare(int id, LocalDate dataStart, LocalDate dataFinala) {
        this.id = id;
        this.dataStart = dataStart;
        this.dataFinala = dataFinala;
        this.status = "activa";
        this.plata = null;
    }

    public double calculeazaPret(double pretPeNoapte) {
        long nopti = dataStart.until(dataFinala).getDays();
        return nopti * pretPeNoapte;
    }

    public void efectueazaPlata(double suma, String metoda) {
        this.plata = new Plata(this.id, suma, metoda);
        this.plata.proceseazaPlata();
        this.status = "platita";
    }

    public int getId() { return id; }
    public LocalDate getDataStart() { return dataStart; }
    public LocalDate getDataFinala() { return dataFinala; }
    public String getStatus() { return status; }
    public Plata getPlata() { return plata; }

    public void afisare() {
        System.out.println("Rezervare #" + id + " | " + dataStart + " -> " + dataFinala + " | Status: " + status);
    }
}