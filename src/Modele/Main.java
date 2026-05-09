package Modele;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Persoana p = new Persoana("Ion");
        p.afisare();

        Angajat a = new Angajat("Maria", 3000, "Receptioner");
        a.afisare();
        a.gestioneazaRezervare();
        a.getCardAcces().afisare();
        System.out.println(a.getCardAcces().oferaAcces());

        Administrator admin = new Administrator("Andrei", 5000, "Manager");
        admin.afisare();
        admin.adaugaCamera();

        Client c = new Client(1, "Elena", "elena@gmail.com", "0722000000");
        c.afisare();

        Rezervare r = new Rezervare(1, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 5));
        r.afisare();
        System.out.println("Pret total: " + r.calculeazaPret(200));
        r.efectueazaPlata(800, "card");
        r.afisare();
    }
}