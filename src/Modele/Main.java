package Modele;

public class Main {
    public static void main(String[] args) {
        Persoana p = new Persoana("Ion");
        p.afisare();

        Angajat a = new Angajat("Maria", 3000, "Receptioner");
        a.afisare();
        a.gestioneazaRezervare();

        Administrator admin = new Administrator("Andrei", 5000, "Manager");
        admin.afisare();
        admin.adaugaCamera();

        Client c = new Client(1, "Elena", "elena@gmail.com", "0722000000");
        c.afisare();
    }
}